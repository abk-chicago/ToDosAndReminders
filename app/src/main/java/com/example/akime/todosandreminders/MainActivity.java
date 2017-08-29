package com.example.akime.todosandreminders;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> mTodoItems;
    ArrayAdapter<String> mTodoAdapter;
    ListView mLvItems;
    String mLvItem;
    EditText mEditText;
    Intent mIntent;
    private final int REQUEST_CODE = 20;
    String mEditable;
    Integer mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLvItems = (ListView) findViewById(R.id.lvItems);
        populateArrayItems();
        mLvItems.setAdapter(mTodoAdapter);

        mEditText = (EditText) findViewById(R.id.etEditText);
        mIntent = new Intent(this, EditItemActivity.class);

        mLvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mTodoItems.remove(position);
                mTodoAdapter.notifyDataSetChanged();
                writeItems();
                return true;
            }
        });

        mLvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mLvItem = mTodoItems.get(position);

                mIntent.putExtra("listItem", mLvItem);
                Log.i("lihla", "List Item = " + mLvItem);

                mIntent.putExtra("position", position);
                Log.i("lihla", "position = " + position);
                startActivityForResult(mIntent, REQUEST_CODE);
            }
        });
    }

    public void populateArrayItems() {
//        todoItems = new ArrayList<String>();
        readItems();
        mTodoAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mTodoItems);
    }

    private void readItems() {
        File filesDir = getFilesDir();
        File file = new File(filesDir, "todo.txt");
        try {
            mTodoItems = new ArrayList<String>(FileUtils.readLines(file));
        } catch (IOException e) {

        }
    }

    private void writeItems() {
        File filesDir = getFilesDir();
        File file = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(file, mTodoItems);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onAddItem(View view) {
        mTodoAdapter.add(mEditText.getText().toString());
        mEditText.setText("");
        writeItems();
    }

    @Override
    protected void onResume() {
        Log.i("lihla", "onResume called ***** ");
        super.onResume();


        mLvItems = (ListView) findViewById(R.id.lvItems);
        populateArrayItems();
        mLvItems.setAdapter(mTodoAdapter);

        mEditText = (EditText) findViewById(R.id.etEditText);
        mIntent = new Intent(this, EditItemActivity.class);

        mLvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mTodoItems.remove(position);
                mTodoAdapter.notifyDataSetChanged();
                writeItems();
                return true;
            }
        });

        mLvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mLvItem = mTodoItems.get(position);

                mIntent.putExtra("listItem", mLvItem);
                Log.i("lihla", "ResumeO-listItem = " + mLvItem);

                mIntent.putExtra("position", position);
                Log.i("lihla", "Resume-position = " + position);
                startActivityForResult(mIntent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("lihla", "onActivityResult method called: "+requestCode+", "+resultCode);


        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            Log.i("lihla", "onActivityResult 1");
            mLvItem = data.getExtras().getString("listItem");
            mPosition = data.getExtras().getInt("position");
            Log.i("lihla", "onActivityResult 2 : **** item brought back is "+ mLvItem + " at position "+ mPosition+ "************");
            mTodoAdapter.add(mLvItem);
            writeItems();
            // Toast the name to display temporarily on screen
            Toast.makeText(this, mLvItem, Toast.LENGTH_SHORT).show();
        }


    }
}
