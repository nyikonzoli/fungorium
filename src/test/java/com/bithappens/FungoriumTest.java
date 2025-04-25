package com.bithappens;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.*;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

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
        // Example assertions — replace these with your actual test logic
        System.out.println("Running test case: " + testCase.name);
        assertNotNull(testCase.startContent);
        assertNotNull(testCase.expectedContent);
        assertNotNull(testCase.commandsContent);

        // TODO: implement test logic
    }
}
