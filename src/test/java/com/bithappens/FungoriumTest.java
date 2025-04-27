package com.bithappens;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.bithappens.Prototype;
import com.bithappens.SaveGame;

public class FungoriumTest {
    // Class holding the content of one test case
    static class TestCaseData {
        String name;
        String startContent;
        String expectedContent;
        String commandsContent;

        TestCaseData(String name, String start, String expected, String commands) {
            this.name = name;
            this.startContent = start;
            this.expectedContent = expected;
            this.commandsContent = commands;
        }
    }

    // Method source to provide all test case directories
    static Stream<TestCaseData> testCasesProvider() throws Exception {
        Path resourcesDir = Path.of("src/test/resources");

        // Find all directories inside resourcesDir
        return Files.list(resourcesDir)
                .filter(Files::isDirectory)
                .map(dir -> {
                    try {
                        String start = Files.readString(dir.resolve("start.txt"));
                        String expected = Files.readString(dir.resolve("expected.txt"));
                        String commands = Files.readString(dir.resolve("commands.txt"));
                        return new TestCaseData(dir.getFileName().toString(), start, expected, commands);
                    } catch (Exception e) {
                        throw new RuntimeException("Error reading files in " + dir.getFileName(), e);
                    }
                });
    }

    @ParameterizedTest
    @MethodSource("testCasesProvider")
    void testCases(TestCaseData testCase) {
        Prototype p = new Prototype();
        SaveGame.loadSaveToPrototype(testCase.startContent, p);
        ArrayList<String> commands = new ArrayList<>(Arrays.asList(testCase.commandsContent.split("\\r?\\n")));
        StringBuilder debugOutputBuilder = new StringBuilder();
        for (String s : commands) {
            debugOutputBuilder.append(p.handleInput(s) + "\n");
        }
        String actualContent = SaveGame.objectStateToString(p);
        String debugOutput = debugOutputBuilder.toString();
        try {
            Files.write(Paths.get("src/test/resources/" + testCase.name + "/actual.txt"), 
                Collections.singletonList(actualContent), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
                Files.write(Paths.get("src/test/resources/" + testCase.name + "/debug-output.txt"), 
                Collections.singletonList(debugOutput), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.out.println("File creation unsuccessful");
        }
        assertTrue(SaveGame.compareSaveFileText(testCase.expectedContent, actualContent));
    }
}
