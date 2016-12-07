package com.teambomb.pizzapal2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView tvRecievedRoom = (TextView)findViewById(R.id.tvRecievedRoom);

        Bundle bundle =getIntent().getExtras();

        if(bundle !=null)
        {
            boolean checkBoxStatus = getIntent().getBooleanExtra("checkBoxStatus", true);
            int roomNum = bundle.getInt("RoomNumber", 0);
            String vendor = bundle.getString("Vendor");
            tvRecievedRoom.setText(roomNum + " " + vendor + " " + checkBoxStatus);
        }


        Button button3 = (Button)findViewById(R.id.button3);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int2 = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(int2);
            }
        });
    }
}
