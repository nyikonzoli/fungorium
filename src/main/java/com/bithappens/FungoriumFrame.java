package com.bithappens;

import java.util.ArrayList;

import javax.swing.JFrame;

public class FungoriumFrame extends JFrame {
    Prototype prototype;
    ArrayList<IFungoriumPanel> panels;
    public FungoriumFrame(Prototype p) {
        prototype = p;
        panels = new ArrayList<>();
    }
}
