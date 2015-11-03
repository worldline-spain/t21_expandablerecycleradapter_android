package com.tempos21.expandablerecycleradapter.app;

import com.tempos21.expandablerecycleradapter.ChildMenuItem;
import com.tempos21.expandablerecycleradapter.ExpandableMenuItem;

import java.util.List;

public class CityParentMenuItem extends ExpandableMenuItem {

    private String groupName;

    public CityParentMenuItem(String groupName) {
        super();
        this.groupName = groupName;
    }

    public CityParentMenuItem(String groupName, List<ChildMenuItem> children) {
        super(children);
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
