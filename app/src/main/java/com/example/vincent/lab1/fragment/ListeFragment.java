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
    ///fix #2
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
        expListItems = SetListe();
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


    public ArrayList<Categorie> SetListe() {
        JSONArray jsonArray = null;


        ArrayList<Categorie> group_list = new ArrayList<Categorie>();

        try {
            jsonArray = new JSONArray(jsonData);

            // On va chercher le nom des catégorie
            if (jsonArray != null) {
                for (int i=0;i<jsonArray.length();i++){
                    // On va chercher le nom des catégorie
                    String category_nom = jsonArray.getJSONObject(i).getString("category");

                    // On créer la catégorie
                    Categorie categorie = new Categorie();
                    categorie.setNom(category_nom);

                    //On assigne les enfants (poi)
                    ArrayList<Poi> child_list;
                    child_list = new ArrayList<Poi>();

                    JSONArray jsonChildArray = jsonArray.getJSONObject(i).getJSONArray("list");
                    for (int j = 0; j <jsonChildArray.length();j++)
                    {
                        String child_nom = jsonChildArray.getJSONObject(j).getString("name");
                        Poi poi = new Poi();
                        poi.setNom(child_nom);
                        child_list.add(poi);
                    }

                    categorie.setItems(child_list);

                    //On ajoute la catégorie à la liste de catégorie
                    group_list.add(categorie);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return group_list;
    }

    public void showToastMsg(String Msg) {
        Toast.makeText(getActivity(), Msg, Toast.LENGTH_SHORT).show();
    }
}
