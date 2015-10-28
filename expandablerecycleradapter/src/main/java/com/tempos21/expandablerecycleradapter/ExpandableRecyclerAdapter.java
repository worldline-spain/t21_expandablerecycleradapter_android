package com.tempos21.expandablerecycleradapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by carles on 27/08/2015.
 */
public abstract class ExpandableRecyclerAdapter <T extends RecyclerView.ViewHolder>
    extends RecyclerView.Adapter<T> {

    private static final int VIEW_TYPE_PARENT = 0;

    private static final int VIEW_TYPE_CHILDREN = 1;

    protected Context context;

    private List<BaseMenuItem> items = new ArrayList<>();

    public abstract int getChildLayout();

    public abstract void onExpandableItemClicked(BaseMenuItem item);

    public abstract void onChildItemClicked(BaseMenuItem item);

    public abstract void onBindViewHolderSpecific(T holder, int position);

    public abstract T getHolder(View view);

    public ExpandableRecyclerAdapter(final Context context) {
        this.context = context;
    }

    @Override
    public T onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_PARENT) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_recycle_parent, parent, false);
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
            holder.txtGroupName.setText(expandableMenuItem.getGroupName());
            if (expandableMenuItem.hasChildren()) {
                holder.imgExpandArrow.setVisibility(View.VISIBLE);
            } else {
                holder.imgExpandArrow.setVisibility(View.INVISIBLE);
            }
        } else {
            onBindViewHolderSpecific(tHolder, position);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
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

    public void addAll(List<BaseMenuItem> newItems) {
        items.clear();
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    /*- ********************************************************************************* */
    /*- ********************************************************************************* */
    /*- ********************************************************************************* */
    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtGroupName;

        private ImageView imgExpandArrow;

        public Holder(View itemView) {
            super(itemView);
            txtGroupName = (TextView) itemView.findViewById(R.id.txt_group_name);
            imgExpandArrow = (ImageView) itemView.findViewById(R.id.img_expand_arrow);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            BaseMenuItem item = items.get(position);
            if (item instanceof ExpandableMenuItem) {
                if (((ExpandableMenuItem) item).hasChildren()) {
                    expandOrCollapse();
                }
                onExpandableItemClicked(item);
            } else {
                onChildItemClicked(item);
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
                imgExpandArrow.startAnimation(
                    AnimationUtils.loadAnimation(context, R.anim.rotation_180_to_360));

            } else {
                // is collapsed -> expand
                items.addAll(position + 1, item.getChildren());
                notifyItemRangeInserted(position + 1, item.getChildren().size());
                imgExpandArrow.startAnimation(AnimationUtils.loadAnimation(context, R.anim.rotation_0_to_180));

            }
            item.setExpanded(!item.isExpanded());
        }
    }
}
