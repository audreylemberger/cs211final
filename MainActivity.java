package com.teambomb.pizzapal2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.buildings_array, android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.toppings_array, android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        final CheckBox vegBox = (CheckBox) findViewById(R.id.vegBox);
        final CheckBox veganBox = (CheckBox) findViewById(R.id.vegBox);
        final CheckBox gfBox = (CheckBox) findViewById(R.id.vegBox);
        final CheckBox kosherBox = (CheckBox) findViewById(R.id.vegBox);

        //edit room number
        final EditText edRmNumText = (EditText)findViewById(R.id.edRoomNumText);
        //edit vendor
        final EditText edVendorText = (EditText)findViewById(R.id.edVendorText);
        //addButton
        Button addButton = (Button)findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checkStatus1 = vegBox.isChecked();
                //boolean checkStatus2 = veganBox.isChecked();
                //boolean checkStatus3 = gfBox.isChecked();
                //boolean checkStatus4 = kosherBox.isChecked();
                Intent int1 = new Intent(MainActivity.this, Main2Activity.class);
                int rmnum =Integer.parseInt(edRmNumText.getText().toString());
                int1.putExtra("checkBoxStatus", checkStatus1);
                //int1.putExtra("checkBoxStatus2", checkStatus2);
                //int1.putExtra("checkBoxStatus3", checkStatus3);
               // int1.putExtra("checkBoxStatus4", checkStatus4);
                int1.putExtra("RoomNumber", rmnum);
                int1.putExtra("Vendor", edVendorText.getText().toString());
                startActivity(int1);
            }
        });




    }
}
