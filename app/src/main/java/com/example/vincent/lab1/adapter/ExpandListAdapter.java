package com.example.vincent.lab1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.vincent.lab1.R;
import com.example.vincent.lab1.model.Categorie;
import com.example.vincent.lab1.model.Poi;

import java.util.ArrayList;

/**
 * Created by Vincent on 2016-09-20.
 */
public class ExpandListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<Categorie> categories;

    public ExpandListAdapter(Context context, ArrayList<Categorie> pCategories) {
        this.context = context;
        this.categories = pCategories;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<Poi> chList = categories.get(groupPosition)
                .getItems();
        return chList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        Poi child = (Poi) getChild(groupPosition,
                childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.child_item, null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.poi_nom);
        tv.setText(child.getNom().toString());

        return convertView;

    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<Poi> chList = categories.get(groupPosition)
                .getItems();

        return chList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return categories.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return categories.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        Categorie categorie = (Categorie) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.group_item, null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.nom_categorie);
        tv.setText(categorie.getNom());
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
