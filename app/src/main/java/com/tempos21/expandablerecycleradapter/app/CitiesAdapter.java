package com.tempos21.expandablerecycleradapter.app;

import com.tempos21.expandablerecycleradapter.BaseMenuItem;
import com.tempos21.expandablerecycleradapter.ChildMenuItem;
import com.tempos21.expandablerecycleradapter.ExpandableMenuItem;
import com.tempos21.expandablerecycleradapter.ExpandableRecyclerAdapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CitiesAdapter extends ExpandableRecyclerAdapter<CitiesAdapter.Holder> {

    public CitiesAdapter(Context context) {
        super(context);
    }

    @Override
    public int getParentLayout() {
        return R.layout.list_item_recycle_parent;
    }

    @Override
    public int getChildLayout() {
        return R.layout.list_item_recycle_child;
    }

    @Override
    public void onExpandableItemClicked(ExpandableMenuItem item) {
        CityParentMenuItem cityMenuItem = (CityParentMenuItem) item;
        if (item.hasChildren()) {
            Toast.makeText(context, cityMenuItem.getGroupName() + " expands", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, cityMenuItem.getGroupName() + " doesn't expand", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onChildItemClicked(ChildMenuItem item) {
        CityChildMenuItem cityMenuItem = (CityChildMenuItem) item;
        String message = new StringBuilder()
            .append(getItemPosition(item)).append("-")
            .append(cityMenuItem.getName()).append("-")
            .append(cityMenuItem.getDegrees()).toString();
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        // notifyItemChanged(getItemPosition(item));
    }

    @Override
    public void onBindViewHolderSpecific(Holder holder, int position) {
        final BaseMenuItem item = getItem(position);
        if (item instanceof CityParentMenuItem) {
            CityParentMenuItem cityParentMenuItem = (CityParentMenuItem) item;
            holder.txtGroupName.setText(cityParentMenuItem.getGroupName());
        }
        if (item instanceof CityChildMenuItem) {
            CityChildMenuItem cityMenuItem = (CityChildMenuItem) item;
            holder.txtCityName.setText(cityMenuItem.getName());
            holder.txtCityDegrees.setText(String.valueOf(cityMenuItem.getDegrees()).concat("º"));
        }
    }

    @Override
    public Holder getHolder(View view) {
        return new Holder(view);
    }

    /*- ********************************************************************************* */
    /*- ********************************************************************************* */
    /*- ********************************************************************************* */
    public class Holder extends ExpandableRecyclerAdapter.Holder {

        private TextView txtCityName;

        private TextView txtCityDegrees;

        private TextView txtGroupName;

        public Holder(View itemView) {
            super(itemView);
            // CityChildMenuItem views
            txtCityName = (TextView) itemView.findViewById(R.id.txt_city_name);
            txtCityDegrees = (TextView) itemView.findViewById(R.id.txt_city_degrees);
            // CityParentMenuItem views
            txtGroupName = (TextView) itemView.findViewById(R.id.txt_group_name);

        }
    }
}
