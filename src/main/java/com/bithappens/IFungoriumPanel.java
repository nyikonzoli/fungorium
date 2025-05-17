package com.bithappens;

public interface IFungoriumPanel {
    /**
     * Implemented method of interface IFungoriumPanel <p>
     * Resets the panel to it's base state
     */
    public void reset();
    /**
     * Implemented method of interface IFungoriumPanel <p>
     * Redraws the component but doesn't reset it's content
     */
    public void redraw();
}
