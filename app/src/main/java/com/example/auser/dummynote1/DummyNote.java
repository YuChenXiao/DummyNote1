package com.example.auser.dummynote1;

import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.sql.SQLException;


public class DummyNote extends AppCompatActivity {
    ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy_note);
        listView = (ListView) findViewById(R.id.listView);
        listView.setEmptyView(findViewById(R.id.empty));
        setAdapter();

    }

    private DB mDbHelper;
    private Cursor mNotesCursor;

    private void setAdapter() {
        mDbHelper = new DB(this);
        try {
            mDbHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mNotesCursor = mDbHelper.getAll();
        startManagingCursor(mNotesCursor);
        String[] from = new String[]{"note","created"};
        int[] to = new int[]{R.id.text1,R.id.text2};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
        R.layout.simple_list_item_1, mNotesCursor, from, to,SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listView.setAdapter(adapter);
    }
}