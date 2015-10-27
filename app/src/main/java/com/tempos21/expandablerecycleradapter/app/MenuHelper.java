package com.tempos21.expandablerecycleradapter.app;

import com.tempos21.expandablerecycleradapter.BaseMenuItem;
import com.tempos21.expandablerecycleradapter.ExpandableMenuItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuHelper {

    public static List<BaseMenuItem> setupItems() {
        List<BaseMenuItem> list = new ArrayList<>();

        CityMenuItem vic = new CityMenuItem("Vic", 13);
        CityMenuItem manresa = new CityMenuItem("Manresa", 9);
        CityMenuItem badalona = new CityMenuItem("Badalona", 18);
        CityMenuItem cornella = new CityMenuItem("Cornellà", 17);
        CityMenuItem olot = new CityMenuItem("Olot", 6);
        CityMenuItem figueres = new CityMenuItem("Figueres", 15);
        CityMenuItem roses = new CityMenuItem("Roses", 18);
        CityMenuItem dublin = new CityMenuItem("Dublin", 11);

        list.add(new ExpandableMenuItem("Barcelona",
            new ArrayList<BaseMenuItem>(Arrays.asList(vic, manresa, badalona, cornella))));
        list.add(new ExpandableMenuItem("Tarragona"));
        list.add(new ExpandableMenuItem("Girona", new ArrayList<BaseMenuItem>(Arrays.asList(olot, figueres, roses))));
        list.add(new ExpandableMenuItem("Lleida"));
        list.add(new ExpandableMenuItem("Irlanda", new ArrayList<BaseMenuItem>(Arrays.asList(dublin))));
        return list;
    }
}
