package com.tempos21.expandablerecycleradapter.app;

import com.tempos21.expandablerecycleradapter.ChildMenuItem;
import com.tempos21.expandablerecycleradapter.ExpandableMenuItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuHelper {

    public static List<ExpandableMenuItem> setupItems() {
        List<ExpandableMenuItem> list = new ArrayList<>();

        CityChildMenuItem vic = new CityChildMenuItem("Vic", 13);
        CityChildMenuItem manresa = new CityChildMenuItem("Manresa", 9);
        CityChildMenuItem badalona = new CityChildMenuItem("Badalona", 18);
        CityChildMenuItem cornella = new CityChildMenuItem("Cornellà", 17);
        CityChildMenuItem olot = new CityChildMenuItem("Olot", 6);
        CityChildMenuItem figueres = new CityChildMenuItem("Figueres", 15);
        CityChildMenuItem roses = new CityChildMenuItem("Roses", 18);
        CityChildMenuItem dublin = new CityChildMenuItem("Dublin", 11);

        List<ChildMenuItem> barcelonaChildren = new ArrayList<ChildMenuItem>(Arrays.asList(vic, manresa, badalona, cornella));
        List<ChildMenuItem> gironaChildren = new ArrayList<ChildMenuItem>(Arrays.asList(olot, figueres, roses));
        List<ChildMenuItem> irlandaChildren = new ArrayList<ChildMenuItem>(Arrays.asList(dublin));

        list.add(new CityParentMenuItem("Barcelona", barcelonaChildren));
        list.add(new CityParentMenuItem("Tarragona"));
        list.add(new CityParentMenuItem("Girona", gironaChildren));
        list.add(new CityParentMenuItem("Lleida"));
        list.add(new CityParentMenuItem("Irlanda", irlandaChildren));
        return list;
    }
}
