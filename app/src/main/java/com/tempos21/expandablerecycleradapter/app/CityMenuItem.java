package com.tempos21.expandablerecycleradapter.app;

import com.tempos21.expandablerecycleradapter.NormalMenuItem;

public class CityMenuItem extends NormalMenuItem {

    private String name;
    private int degrees;

    public CityMenuItem(String name, int degrees) {
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
