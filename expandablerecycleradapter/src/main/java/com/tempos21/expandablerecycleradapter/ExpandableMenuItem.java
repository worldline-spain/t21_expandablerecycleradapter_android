package com.tempos21.expandablerecycleradapter;

import java.util.List;

public class ExpandableMenuItem extends BaseMenuItem {

    private String groupName;

    private List<NormalMenuItem> children;

    private boolean expanded = false;

    public ExpandableMenuItem(String groupName) {
        this.groupName = groupName;
    }

    public ExpandableMenuItem(String groupName, List<NormalMenuItem> children) {
        this.groupName = groupName;
        this.children = children;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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

    public List<NormalMenuItem> getChildren() {
        return children;
    }

    public void setChildren(List<NormalMenuItem> children) {
        this.children = children;
    }
}
