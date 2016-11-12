package com.tempos21.expandablerecycleradapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by carles on 27/08/2015.
 * Adapter to expand and collapse
 */
public abstract class ExpandableRecyclerAdapter<T extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<T> {

    protected static final int INVALID_POSITION = -1;

    private static final int VIEW_TYPE_PARENT = 0;

    private static final int VIEW_TYPE_CHILDREN = 1;

    protected Context context;

    private List<ExpandableMenuItem> parentItems = new ArrayList<>();

    private List<BaseMenuItem> items = new ArrayList<>();

    public abstract int getParentLayout();

    public abstract int getChildLayout();

    public abstract void onExpandableItemClicked(ExpandableMenuItem item);

    public abstract void onChildItemClicked(ChildMenuItem item);

    public abstract void onBindViewHolderSpecific(T holder, int position);

    public abstract T getHolder(View view);

    private boolean oneExpandedMode;

    public ExpandableRecyclerAdapter(final Context context) {
        this.context = context;
    }

    @Override
    public T onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_PARENT) {
            view = LayoutInflater.from(parent.getContext()).inflate(getParentLayout(), parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(getChildLayout(), parent, false);
        }
        return getHolder(view);
    }

    @Override
    public void onBindViewHolder(T tHolder, int position) {
        final BaseMenuItem item = getItem(position);

        if (item instanceof ExpandableMenuItem) {
            ExpandableMenuItem expandableMenuItem = (ExpandableMenuItem) item;
            Holder holder = (Holder) tHolder;
            if (holder.imgDefaultExpandArrow != null) {
                if (expandableMenuItem.hasChildren()) {
                    holder.imgDefaultExpandArrow.setVisibility(View.VISIBLE);
                } else {
                    holder.imgDefaultExpandArrow.setVisibility(View.INVISIBLE);
                }
            }
            expandableMenuItem.setHolder(holder);
        }
        onBindViewHolderSpecific(tHolder, position);
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position) instanceof ExpandableMenuItem) {
            return VIEW_TYPE_PARENT;
        } else {
            return VIEW_TYPE_CHILDREN;
        }
    }

    protected BaseMenuItem getItem(int position) {
        return items.get(position);
    }

    protected int getItemPosition(BaseMenuItem item) {
        if (items != null) {
            int size = getItemCount();
            BaseMenuItem currentItem;
            for (int position = 0; position < size; position++) {
                currentItem = getItem(position);
                if (currentItem == item) {
                    return position;
                }
            }
        }
        return INVALID_POSITION;
    }

    protected int getParentPosition(ChildMenuItem item) {
        if (items != null) {
            int size = getItemCount();
            ExpandableMenuItem currentItem;
            for (int position = 0; position < size; position++) {
                if (getItem(position) instanceof ExpandableMenuItem) {
                    currentItem = (ExpandableMenuItem) getItem(position);
                    if (currentItem.hasChildren() && currentItem.getChildren().contains(item)) {
                        return position;
                    }
                }
            }
        }
        return INVALID_POSITION;
    }


    public void addAll(List<ExpandableMenuItem> newItems) {
        parentItems.clear();
        parentItems.addAll(newItems);
        items.clear();
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    public void add(ExpandableMenuItem newItem) {
        parentItems.add(newItem);
        items.add(newItem);
    }

    public void addChildToParent(ChildMenuItem childMenuItem, ExpandableMenuItem expandableMenuItem) {
        expandableMenuItem.getChildren().add(0, childMenuItem);
        int position = getItemPosition(expandableMenuItem) + 1;
        if (expandableMenuItem.isExpanded()) {
            items.add(position, childMenuItem);
            notifyItemInserted(position);
        } else {
            expandableMenuItem.setExpanded(true);
            items.addAll(position, expandableMenuItem.getChildren());
            int positionEnd = expandableMenuItem.getChildren().size();
            notifyItemChanged(getItemPosition(expandableMenuItem));
            notifyItemRangeInserted(position, positionEnd);
        }

    }

    public List<ExpandableMenuItem> getItems() {
        return parentItems;
    }

    /**
     * Indicate if one expanded mode is active
     *
     * @return boolean true of one expanded mode activated
     */
    public boolean isOneExpandedMode() {
        return oneExpandedMode;
    }

    /**
     * Activate one expanted parent mode
     *
     * @param oneExpandedMode boolean true if mode active
     */
    public void setOneExpandedMode(boolean oneExpandedMode) {
        this.oneExpandedMode = oneExpandedMode;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /*- ********************************************************************************* */
    /*- ********************************************************************************* */
    /*- ********************************************************************************* */
    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imgDefaultExpandArrow;

        public Holder(View itemView) {
            super(itemView);
            imgDefaultExpandArrow = (ImageView) itemView.findViewById(R.id.img_default_expand_arrow);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position == INVALID_POSITION || position >= items.size()) {
                return;
            }
            BaseMenuItem item = items.get(position);
            if (item instanceof ExpandableMenuItem) {
                ExpandableMenuItem expandableMenuItem = (ExpandableMenuItem) item;
                if (expandableMenuItem.hasChildren()) {
                    if (oneExpandedMode) {
                        collapseOthers(position);
                    }
                    expandOrCollapse();
                }
                onExpandableItemClicked(expandableMenuItem);
            }

            if (item instanceof ChildMenuItem) {
                onChildItemClicked((ChildMenuItem) item);
            }
        }

        private void expandOrCollapse() {
            int position = getAdapterPosition();
            ExpandableMenuItem item = (ExpandableMenuItem) items.get(position);

            if (item.isExpanded()) {
                // is expanded -> collapse
                for (int i = 0; i < item.getChildren().size(); i++) {
                    items.remove(position + 1);
                }
                // notifyItemRangeXXX performs an automatic animation :)
                notifyItemRangeRemoved(position + 1, item.getChildren().size());

                if (imgDefaultExpandArrow != null) {
                    imgDefaultExpandArrow.startAnimation(
                            AnimationUtils.loadAnimation(context, R.anim.rotation_180_to_360));
                }

            } else {
                // is collapsed -> expand
                items.addAll(position + 1, item.getChildren());
                notifyItemRangeInserted(position + 1, item.getChildren().size());

                if (imgDefaultExpandArrow != null) {
                    imgDefaultExpandArrow.startAnimation(AnimationUtils.loadAnimation(context, R.anim.rotation_0_to_180));
                }

            }
            item.setExpanded(!item.isExpanded());
        }

        private void collapseOthers(int currentPosition) {
            for (int n = 0; n < items.size(); n++) {
                BaseMenuItem baseMenuItem = items.get(n);
                if (baseMenuItem instanceof ExpandableMenuItem && currentPosition != n) {
                    ExpandableMenuItem expandableMenuItem = (ExpandableMenuItem) baseMenuItem;
                    if (expandableMenuItem.isExpanded()) {
                        for (int i = 0; i < expandableMenuItem.getChildren().size(); i++) {
                            items.remove(n + 1);
                        }
                        // notifyItemRangeXXX performs an automatic animation :)
                        notifyItemRangeRemoved(n + 1, expandableMenuItem.getChildren().size());
                        expandableMenuItem.setExpanded(!expandableMenuItem.isExpanded());
                        if (expandableMenuItem.getHolder() != null) {
                            ImageView ivItem = expandableMenuItem.getHolder().imgDefaultExpandArrow;
                            if (ivItem != null) {
                                ivItem.startAnimation(
                                        AnimationUtils.loadAnimation(context, R.anim.rotation_180_to_360));
                            }
                        }
                        return;
                    }
                }
            }
        }
    }
}
