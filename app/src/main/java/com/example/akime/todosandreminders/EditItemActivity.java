package com.example.akime.todosandreminders;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        final Intent backToMainActivityIntent;
        Button btnEditSubmit;
        EditText etMultiLine = (EditText) findViewById(R.id.etEditTextMultiline);
        btnEditSubmit = (Button) findViewById(R.id.btnEditSubmit);
        backToMainActivityIntent = new Intent(this, MainActivity.class);

//  need to make this work

        Intent i = getIntent();
        final String editedItem = getIntent().getStringExtra("editPosition");
        final int position = i.getIntExtra("position", 0);

        btnEditSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //code here
                backToMainActivityIntent.putExtra("position",editedItem);
                startActivity(backToMainActivityIntent);

//  need to make this work

            }
        });




    }

    public void onSubmit(View v) {
        this.finish();
    }



}
