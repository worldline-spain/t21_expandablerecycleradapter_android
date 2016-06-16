[![](https://jitpack.io/v/worldline-spain/t21_expandablerecycleradapter_android.svg)](https://jitpack.io/#worldline-spain/t21_expandablerecycleradapter_android)

# README
**Adapter to allow expandable menus**

Each RecyclerView has 'parent' elements that expand/collapse to show 'child' subelements. 

![alt text](https://github.com/worldline-spain/t21_expandablerecycleradapter_android/blob/master/ExpandableRecyclerAdapter-capture-smaller.png "")

###How to use it?
- Add these dependencies to your project:
```
compile 'com.github.worldline-spain:t21_expandablerecycleradapter_android:1.0.12'
compile 'com.android.support:appcompat-v7:23.1.0'
compile 'com.android.support:recyclerview-v7:23.1.0'
```
- Add this to your root build.gradle
```
allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
    }
}

```
- Create an adapter that extends ExpandableRecyclerAdapter and implements its abstract methods:
```java
public abstract int getParentLayout();
public abstract int getChildLayout();
public abstract void onExpandableItemClicked(ExpandableMenuItem item);
public abstract void onChildItemClicked(ChildMenuItem item);
public abstract void onBindViewHolderSpecific(T holder, int position);
public abstract T getHolder(View view);
```
- Create two types of elements: one extends ExpandableMenuItem, the other extends ChildMenuItem. Each expandable item can contain zero or more ChildMenuItems
- Populate the adapter with a list of your expandable items
- In your "parent item" layout, you can add this 'include' to have an arrow that animates when an item is expanded/collapsed. However, you can use a custom arrow (set its id to img_default_expand_arrow)
```xml
<include layout="@layout/img_default_expand_arrow" />
```

# License

Copyright 2016 Worldline Iberia

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
