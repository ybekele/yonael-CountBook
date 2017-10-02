package com.example.yonael.yonael_countbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.yonael.yonael_countbook.R;

/**
 * Created by yonaelbekele on 2017-09-28.
 */

public class AddActivity extends AppCompatActivity {
    // initializing variable names to store user inputs

    String name, comment;
    String counter;
    EditText nameField;
    EditText initialValueField;
    EditText commentField;
    Button saveButton;

    Intent save;


    /*
     * Handles the adding of new counters and passing the data to the MainActivity to add in the arrays
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        nameField = (EditText) findViewById(R.id.nameField);
        initialValueField = (EditText) findViewById(R.id.initialValueField);
        commentField = (EditText) findViewById(R.id.commentField);
        saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = nameField.getText().toString();
                counter = initialValueField.getText().toString();
                comment = commentField.getText().toString();

                /* WORKING CODE
                Intent save = new Intent(AddActivity.this, MainActivity.class);
                save.putExtra("nameData", name);
                save.putExtra("counterData", counter);
                save.putExtra("commentData", comment);
                if (name != null) {
                    setResult(RESULT_OK, save);
                    finish();
                }
                */
                save = new Intent();
                save.putExtra("nameData", name);
                save.putExtra("counterData", counter);
                save.putExtra("commentData", comment);
                if (name != null) {
                    setResult(RESULT_OK, save);
                    finish();
                }






            }
        });


    }
}