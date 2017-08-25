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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        EditText etMultiLine = (EditText) findViewById(R.id.etEditTextMultiline);
        btnEditSubmit = (Button) findViewById(R.id.btnSave);

        String editable = getIntent().getStringExtra("editable");
        int position = getIntent().getIntExtra("position", 0);


        btnEditSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //code here
                Intent backToMainActivityIntent = new Intent(EditItemActivity.this, MainActivity.class);
                backToMainActivityIntent.putExtra("position", 0);
                startActivity(backToMainActivityIntent);

//  need to make this work

            }
        });




    }

    public void onSubmit(View v) {
        this.finish();
    }



}
