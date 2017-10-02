package com.example.yonael.yonael_countbook;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity {


    private TextView countText;
    private EditText editName;
    private EditText editDate;
    private EditText editCommment;
    private int counter = 0;
    String resetVal;

    private Button decrementBut;
    private Button resetBut;
    private Button incrementBut;


    /*
     * Handles the Increment, decrement and reset button
     */
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.incrementBut:
                    increment();
                    break;
                case R.id.decrementBut:
                    decrement();
                    break;
                case R.id.resetBut:
                    reset();
                    break;


            }

        }
    };


    /*
     * Basically runs the whole edit class, accepts data from MainActivity and parses through it
     * Then allows user to increment and decrement counters by calling the custom clickListener
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        Bundle bundle = getIntent().getExtras();

        String name = bundle.getString("nameData");
        editName = (EditText) findViewById(R.id.editName);
        editName.setText(name);

        String initVal = bundle.getString("initValue");
        String currVal = bundle.getString("currValue");
        String comment = bundle.getString("comment");
        editCommment = (EditText) findViewById(R.id.editComment);
        editCommment.setText(comment);
        resetVal = initVal;


        decrementBut = (Button) findViewById(R.id.decrementBut);
        decrementBut.setOnClickListener(clickListener);
        resetBut = (Button) findViewById(R.id.resetBut);
        resetBut.setOnClickListener(clickListener);
        incrementBut = (Button) findViewById(R.id.incrementBut);
        incrementBut.setOnClickListener(clickListener);
        countText = (TextView) findViewById(R.id.countText);

        // converts string into integer and initializes the counter
        initCounter(currVal);
        countText.setText(Integer.toString(counter));
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Intent edit = new Intent(EditActivity.this, MainActivity.class);
        //edit.putExtra("newName", editName.getText().toString());
        //edit.putExtra("newVal", countText.getText().toString());
    }

    /*
     * initializes the counter
     *
     */
    private void initCounter(String count) {
        if (count != null) {
            counter = Integer.parseInt(count);
        } else {
            counter = 0;
        }
    }

    /*
     * increments the counter by 1
     */
    private void increment() {
        counter++;
        countText.setText(Integer.toString(counter));
    }

    /*
     * decrements the counter by 1, cannot go lower than 0
     *
     */
    private void decrement() {
        if (counter != 0) {
            counter--;
        }
        countText.setText(Integer.toString(counter));
    }

    /*
     * resets counter to the initial value it had
     */
    private void reset() {
        counter = Integer.parseInt(resetVal);
        countText.setText(Integer.toString(counter));
    }
}

