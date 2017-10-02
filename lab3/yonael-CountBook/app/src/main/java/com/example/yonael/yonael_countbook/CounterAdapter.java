package com.yonael.yonael_countbook;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;

/**
 * Created by yonaelbekele on 2017-09-28.
 */

/*
 * Adapter which SHOULD act as handling the methods of counters
 */
public class CounterAdapter extends BaseAdapter implements ListAdapter {
    private Context countContext;
    List<String> myListCountBook;


    // initializing variable names to store user inputs
    TextView nameField;
    //TextView initialValueField;
    //TextView commentField;


    /*
     * Creates the initialization of the counter
     *
     */
    public CounterAdapter(Context context, List<String> list) {
        countContext = context;
        myListCountBook = list;
    }


    /*
     * gets size of the list
     */
    @Override
    public int getCount() {
        return myListCountBook.size();
    }


    /*
     * gets specific item in the list

     */
    @Override
    public Object getItem(int pos) {
        return myListCountBook.get(pos);
    }


    /*
     * gets specific id of the item in the list
     */
    @Override
    public long getItemId(int i) {
        return 0;
    }

    /*
     * integrates the view to connect with the methods

     */

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}