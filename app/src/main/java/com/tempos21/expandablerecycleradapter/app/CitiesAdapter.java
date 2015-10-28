package com.tempos21.expandablerecycleradapter.app;

import com.tempos21.expandablerecycleradapter.BaseMenuItem;
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
    public int getChildLayout() {
        return R.layout.list_item_recycle_child;
    }

    @Override
    public void onExpandableItemClicked(BaseMenuItem item) {
        ExpandableMenuItem expandableMenuItem = (ExpandableMenuItem) item;
        if (!expandableMenuItem.hasChildren()) {
            Toast.makeText(context, expandableMenuItem.getGroupName(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onChildItemClicked(BaseMenuItem item) {
            CityMenuItem cityMenuItem = (CityMenuItem) item;
            String message = new StringBuilder().append(cityMenuItem.getName()).append("-")
                .append(cityMenuItem.getDegrees()).toString();
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBindViewHolderSpecific(Holder holder, int position) {
        final BaseMenuItem item = getItem(position);
        if (item instanceof CityMenuItem) {
            CityMenuItem cityMenuItem = (CityMenuItem) item;
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

        public Holder(View itemView) {
            super(itemView);
            txtCityName = (TextView) itemView.findViewById(R.id.txt_city_name);
            txtCityDegrees = (TextView) itemView.findViewById(R.id.txt_city_degrees);
        }
    }
}
