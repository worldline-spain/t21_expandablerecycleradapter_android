package com.tempos21.expandablerecycleradapter.app;

import com.tempos21.expandablerecycleradapter.ChildMenuItem;

public class CityChildMenuItem extends ChildMenuItem {

    private String name;
    private int degrees;

    public CityChildMenuItem(String name, int degrees) {
        this.name = name;
        this.degrees = degrees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDegrees() {
        return degrees;
    }

    public void setDegrees(int degrees) {
        this.degrees = degrees;
    }
}
