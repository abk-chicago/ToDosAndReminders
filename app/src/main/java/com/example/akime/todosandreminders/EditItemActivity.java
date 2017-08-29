package com.example.akime.todosandreminders;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
public class EditItemActivity extends AppCompatActivity {
    Button btnEditSubmit;
    String editable;
    Integer pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        EditText etMultiLine = (EditText) findViewById(R.id.etEditTextMultiline);
        btnEditSubmit = (Button) findViewById(R.id.btnSave);

        editable = getIntent().getStringExtra("listItem");
        pos = getIntent().getExtras().getInt("position");
        etMultiLine.setText(editable);
        etMultiLine.setSelection(etMultiLine.getText().length());

        btnEditSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = (EditText) findViewById(R.id.etEditTextMultiline);
                String str = et.getText().toString();
                Log.d("lihla", "onClick : "+str);
                Intent backToMainActivityIntent = new Intent(EditItemActivity.this, MainActivity.class);
                backToMainActivityIntent.putExtra("listItem", str);
                backToMainActivityIntent.putExtra("position", pos);

                setResult(RESULT_OK, backToMainActivityIntent);
                finish();
            }
        });

    }

}

