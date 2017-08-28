package com.example.akime.todosandreminders;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {
    Button btnEditSubmit;
    String itemToEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        EditText etMultiLine = (EditText) findViewById(R.id.etEditTextMultiline);
        btnEditSubmit = (Button) findViewById(R.id.btnSave);

        itemToEdit = getIntent().getStringExtra("listItem");
        etMultiLine.setText(itemToEdit);
        final int position = getIntent().getIntExtra("position", 0);


        btnEditSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubmit(btnEditSubmit);
            }
        });
    }

    public void onSubmit(View v) {
        Intent data = new Intent(EditItemActivity.this, MainActivity.class);
        data.putExtra("editedItem", 0);
        setResult(RESULT_OK, data);
        startActivity(data);
        this.finish();
    }



}
