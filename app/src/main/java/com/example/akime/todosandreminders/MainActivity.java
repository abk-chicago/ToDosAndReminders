package com.example.akime.todosandreminders;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> todoItems;
    ArrayAdapter<String> aTodoAdapter;
    ListView lvItems;
    EditText etEditText;
    Intent editTextIntent;
    private final int REQUEST_CODE = 20;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvItems = (ListView) findViewById(R.id.lvItems);
        populateArrayItems();
        lvItems.setAdapter(aTodoAdapter);
        etEditText = (EditText) findViewById(R.id.etEditText);
        editTextIntent = new Intent(this, EditItemActivity.class);
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                todoItems.remove(position);
                aTodoAdapter.notifyDataSetChanged();
                writeItems();
                return true;
            }
        });

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String listItem = todoItems.get(position);
                editTextIntent.putExtra("listItem",listItem);
                editTextIntent.putExtra("position",position);
                startActivityForResult(editTextIntent, REQUEST_CODE);
            }

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {

            String editedItem = data.getExtras().getString("editedItem");
//            int code = data.getExtras().getInt("code", 0);

        }
    }



    public void launchEditActivity() {
        Intent editTextIntent = new Intent(MainActivity.this, EditItemActivity.class);
        editTextIntent.putExtra("toEdit", "listItem");
        startActivity(editTextIntent);
    }


    public void populateArrayItems() {
        todoItems = new ArrayList<String>();
        readItems();
        aTodoAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,todoItems);
    }

    private void readItems() {
        File filesDir = getFilesDir();
        File file = new File(filesDir, "todo.txt");
        try {
            todoItems = new ArrayList<String>(FileUtils.readLines(file));
        } catch (IOException e) {

        }
    }

    private void writeItems() {
        File filesDir = getFilesDir();
        File file = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(file, todoItems);
        } catch (IOException e) {

        }
    }

    public void onAddItem(View view) {
        aTodoAdapter.add(etEditText.getText().toString());
        etEditText.setText("");
        writeItems();
    }
}
