package com.example.yonael.yonael_countbook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.reflect.Array.getLength;
import static java.security.AccessController.getContext;


/**
 * MainActivity
 * - Handles calling the other activities to add, delete and edit
 * - The thinking being there is mulitiple arrays holding the comments, names, initial values,
 * current values and linking them all by using their indices
 */
public class MainActivity extends AppCompatActivity {
    private static final String FILENAME = "file.sav";
    private static final int ADD_CONST = 1;
    private static final int DELETE_CONST = 2;
    private ListView list;
    public Button addButton;
    public Button deleteBut;

    private ArrayList<String> namesList; //= new ArrayList<String>();
    private ArrayList<String> initList;
    private ArrayList<String> currList;
    private ArrayList<String> commentList;
    private ArrayAdapter<String> adapter;


    /*
     * Handles the addButton being clicked to start the addition of a new counter
     */
    public void addSequence() {
        addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(add, ADD_CONST);
            }
        });
    }

    /*
     * SHOULD handle the deletion of current counters. Currently there is a bug which
     * causes the add button to stop working and crash the app when attempting to delete
     */
    public void deleteSequence() {
        deleteBut = (Button) findViewById(R.id.deleteBut);
        deleteBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent delete = new Intent(MainActivity.this, DeleteActivity.class);
                startActivityForResult(delete, DELETE_CONST);
            }
        });
    }


    /*
     * SHOULD save to internal storage using SharedPreferences but currently will cause crashes when returning to the app
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFromFile();


        list = (ListView) findViewById(R.id.listCount);
        namesList = new ArrayList<String>();


        /*
        SharedPreferences settings = getSharedPreferences("PREFS", 0);
        String namesString = settings.getString("words", "");
        if ((namesString.equals(""))) {
            String[] nameItems = namesString.split(",");
            for (int i = 0; i < nameItems.length; i++) {
                namesList.add(nameItems[i]);

            }

        }

*/
        addSequence();
        //deleteSequence();

        /*
        for (int i = 0; i < nameItems.length; i++) {
            namesList.add(nameItems[i]);
        }
        */

        initList = new ArrayList<String>();
        currList = new ArrayList<String>();
        commentList = new ArrayList<String>();


        // sets adapter to handle the listing of the elements in the namesList
        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_dropdown_item_1line, namesList);

        list.setAdapter(adapter);
        list = (ListView) findViewById(R.id.listCount);


        /*
         * Handles the click of individual items and calls the EditActivity
         */
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent edit = new Intent(MainActivity.this, EditActivity.class);
                edit.putExtra("nameData", list.getItemAtPosition(i).toString());
                edit.putExtra("initValue", initList.get(i));
                edit.putExtra("currValue", currList.get(i));
                edit.putExtra("comment", commentList.get(i));
                //saveInFile(list.getItemAtPosition(i).toString());
                startActivity(edit);
                /*
                Bundle bundle = getIntent().getExtras();
                String name = bundle.getString("newName");
                String val = bundle.getString("newVal");

                if ((name.equals(list.getItemAtPosition(i).toString())) == FALSE) {
                    namesList.set(i, name);
                    adapter.notifyDataSetChanged();
                }

                if ((val.equals(currList.get(i)) == FALSE)) {
                    currList.set(i, val);
                    adapter.notifyDataSetChanged();
                }*/
            }

        });
    }


    /*
     * handles the return of data after adding a counter
     * SHOULD handle the deletion however there is a bug
     *
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ADD_CONST:
                if (resultCode == RESULT_OK) {
                    String name = data.getExtras().getString("nameData");
                    String counter = data.getExtras().getString("counterData");
                    String comment = data.getExtras().getString("commentData");
                    namesList.add(name);
                    initList.add(counter);
                    currList.add(counter);
                    commentList.add(comment);
                    adapter.notifyDataSetChanged();
                    saveInFile();
                }
/*
            case DELETE_CONST:
                if (resultCode == RESULT_OK) {
                    String deleteName = data.getExtras().getString("deleteText");
                    for (int j = 0; j <= ((getLength(namesList)) - 1); j++) {
                        if (namesList.get(j).equals(deleteName)) {
                            namesList.remove(j);
                            adapter.notifyDataSetChanged();
                        }
                        break;
                        }

                    }
                    */
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        saveInFile();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //loadFromFile();
    }

    private void loadFromFile() {

        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            namesList = gson.fromJson(in, listType);
            //https://github.com/google/gson/blob/master/UserGuide.md#
        } catch (FileNotFoundException e) {
            //TODO Auto-generated catch block
            namesList = new ArrayList<String>();
        } catch (IOException e) {
            throw new RuntimeException();
        }

    }

    /**
     * Method which saves information to file
     */
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(namesList, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }


}
