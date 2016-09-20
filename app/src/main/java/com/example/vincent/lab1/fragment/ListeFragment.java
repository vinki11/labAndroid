package com.example.vincent.lab1.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
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

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by Vincent on 2016-09-19.
 */
public class ListeFragment extends Fragment {

    private ExpandListAdapter expAdapter;
    private ArrayList<Categorie> expListItems;
    private ExpandableListView expandList;
    private String jsonData = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Lire le fichier json
        InputStream is = getResources().openRawResource(R.raw.listedata);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];

        //C'est dégeu les 2 try catch mais Android studio chialait si je faisais pas ca comme ca...
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

       jsonData = writer.toString();



       // Log.i("json string jsonData : ", jsonData);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.liste_fragment_layout, container, false);

    }

    @Override
    public void onResume() {
        super.onResume();

        expandList = (ExpandableListView) getView().findViewById(R.id.listView_data);
        expListItems = SetStandardGroups();
        expAdapter = new ExpandListAdapter(getActivity(), expListItems);
        expandList.setAdapter(expAdapter);

        expandList.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                String group_name = expListItems.get(groupPosition).getNom();

                ArrayList<Poi> ch_list = expListItems.get(
                        groupPosition).getItems();

                String child_name = ch_list.get(childPosition).getNom();

                showToastMsg(group_name + "\n" + child_name);

                return false;
            }
        });

        expandList.setOnGroupExpandListener(new OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                String group_name = expListItems.get(groupPosition).getNom();
                showToastMsg(group_name + "\n Expanded");

            }
        });

        expandList.setOnGroupCollapseListener(new OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                String group_name = expListItems.get(groupPosition).getNom();
                showToastMsg(group_name + "\n Expanded");

            }
        });
    }

    //C'est hardcodé et c'est laid mais une chose à la fois on loadera le JSON après
    public ArrayList<Categorie> SetStandardGroups() {
        JSONArray jsonArray = null;

        ArrayList<String> test = new ArrayList<String>();
        ArrayList<Categorie> group_list = new ArrayList<Categorie>();

        try {
            jsonArray = new JSONArray(jsonData);

            if (jsonArray != null) {
                for (int i=0;i<jsonArray.length();i++){
                    Log.i("SUP dude", i + " tortue " + jsonArray.get(i).toString());
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }



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
