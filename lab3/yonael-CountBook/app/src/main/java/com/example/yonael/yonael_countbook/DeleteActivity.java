package com.example.yonael.yonael_countbook;

/**
 * Created by yonael on 10/2/17.
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DeleteActivity extends AppCompatActivity {

    Button deleteBut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        deleteBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent delete = new Intent();
                EditText deleteText = (EditText) findViewById(R.id.deleteText);
                String deleteCounter = deleteText.getText().toString();
                delete.putExtra("deleteText", deleteCounter);
                setResult(RESULT_OK, delete);
                finish();
            }
        });
    }
}