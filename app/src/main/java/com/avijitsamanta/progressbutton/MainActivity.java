package com.avijitsamanta.progressbutton;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.avijitsamanta.custom_progress.CustomProgressButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = findViewById(R.id.tv);
        tv.setTextColor(Color.parseColor("#ABCDEF"));

        final CustomProgressButton button = findViewById(R.id.btn);
        button.setText("Demo");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.show("Please Wait");
                button.setProgressBarColor(Color.RED);
                button.setBackgroundColor(Color.YELLOW);
                button.setTextColor(Color.BLACK);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // button.error();
                        button.hide();
                    }
                }, 5000);
            }
        });

    }
}