package com.tempos21.expandablerecycleradapter;

import java.util.List;

public class ExpandableMenuItem extends BaseMenuItem {

    private List<ChildMenuItem> children;

    private boolean expanded = false;

    public ExpandableMenuItem() { }

    public ExpandableMenuItem(List<ChildMenuItem> children) {
        this.children = children;
    }

    public boolean hasChildren() {
        return children != null && children.size() > 0;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public List<ChildMenuItem> getChildren() {
        return children;
    }

    public void setChildren(List<ChildMenuItem> children) {
        this.children = children;
    }
}
