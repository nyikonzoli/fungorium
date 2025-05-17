package com.bithappens;

import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JPanel;

public class ActionSelectorPanel extends JPanel implements IFungoriumPanel{
    private FungoriumFrame fungoriumFrame;
    public ActionSelectorPanel(FungoriumFrame frame) {
        this.fungoriumFrame = frame;

        //debug color
        //setBackground(Color.RED);
    }
    @Override
    public void reset() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'reset'");
    }
    
    public void selectObject(MushroomBody mushroomBody) {
        Tekton currentTekton = mushroomBody.getLocation();
        ArrayList<Tekton> neighbors = currentTekton.getNeighbours();
        neighbors.add(currentTekton);
        ArrayList<Tekton> allNeighbors = new ArrayList<>();
        for(Tekton t : neighbors){
            allNeighbors.addAll(t.getNeighbours());
        }

        // Kulcsok keresése

        HashSet<String> tektonNames = new HashSet<>();
        for(Tekton t : allNeighbors){
            String key = fungoriumFrame.getPrototype().getKey(t);
            tektonNames.add(key);
        }

        JComboBox<String> tektonSelector = new JComboBox<>(tektonNames.toArray(new String[0]));
        this.add(tektonSelector);

    }
    public void selectObject(Insect insect) {
        
    }
    public void selectObject(Mycelium mycelium) {
        // TODO - kristófé
    }
}
