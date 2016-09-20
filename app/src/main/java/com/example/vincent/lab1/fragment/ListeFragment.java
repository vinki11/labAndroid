package com.example.vincent.lab1.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

import com.example.vincent.lab1.R;
import com.example.vincent.lab1.adapter.ExpandListAdapter;
import com.example.vincent.lab1.model.Categorie;
import com.example.vincent.lab1.model.Poi;

import java.util.ArrayList;

/**
 * Created by Vincent on 2016-09-19.
 */
public class ListeFragment extends Fragment {

    private ExpandListAdapter ExpAdapter;
    private ArrayList<Categorie> ExpListItems;
    private ExpandableListView ExpandList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.liste_fragment_layout, container, false);

    }

    @Override
    public void onResume() {
        super.onResume();

        ExpandList = (ExpandableListView) getView().findViewById(R.id.listView_data);
        ExpListItems = SetStandardGroups();
        ExpAdapter = new ExpandListAdapter(getActivity(), ExpListItems);
        ExpandList.setAdapter(ExpAdapter);

        ExpandList.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                String group_name = ExpListItems.get(groupPosition).getNom();

                ArrayList<Poi> ch_list = ExpListItems.get(
                        groupPosition).getItems();

                String child_name = ch_list.get(childPosition).getNom();

                showToastMsg(group_name + "\n" + child_name);

                return false;
            }
        });

        ExpandList.setOnGroupExpandListener(new OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                String group_name = ExpListItems.get(groupPosition).getNom();
                showToastMsg(group_name + "\n Expanded");

            }
        });

        ExpandList.setOnGroupCollapseListener(new OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                String group_name = ExpListItems.get(groupPosition).getNom();
                showToastMsg(group_name + "\n Expanded");

            }
        });
    }

    //C'est hardcodé et c'est laid mais une chose à la fois on loadera le JSON après
    public ArrayList<Categorie> SetStandardGroups() {

        ArrayList<Categorie> group_list = new ArrayList<Categorie>();
        ArrayList<Poi> child_list;

        // Setting Group 1
        child_list = new ArrayList<Poi>();
        Categorie gru1 = new Categorie();
        gru1.setNom("Apple");

        Poi ch1_1 = new Poi();
        ch1_1.setNom("Iphone");
        child_list.add(ch1_1);

        Poi ch1_2 = new Poi();
        ch1_2.setNom("ipad");
        child_list.add(ch1_2);

        Poi ch1_3 = new Poi();
        ch1_3.setNom("ipod");
        child_list.add(ch1_3);

        gru1.setItems(child_list);

        // Setting Categorie 2
        child_list = new ArrayList<Poi>();
        Categorie gru2 = new Categorie();
        gru2.setNom("SAMSUNG");

        Poi ch2_1 = new Poi();
        ch2_1.setNom("Galaxy Grand");
        child_list.add(ch2_1);

        Poi ch2_2 = new Poi();
        ch2_2.setNom("Galaxy Note");
        child_list.add(ch2_2);

        Poi ch2_3 = new Poi();
        ch2_3.setNom("Galaxy Mega");
        child_list.add(ch2_3);

        Poi ch2_4 = new Poi();
        ch2_4.setNom("Galaxy Neo");
        child_list.add(ch2_4);

        gru2.setItems(child_list);

        //listing all groups
        group_list.add(gru1);
        group_list.add(gru2);

        return group_list;
    }

    public void showToastMsg(String Msg) {
        Toast.makeText(getActivity(), Msg, Toast.LENGTH_SHORT).show();
    }
}
