package com.example.tabbed;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Splashmain extends AppCompatActivity {
    Button get;
    TextView t1;
    ImageView i1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashmain);

        t1=findViewById(R.id.ej);
        i1=findViewById(R.id.gifImageView);
        get=findViewById(R.id.get);

        /*
        get.animate().translationY(-1900).setDuration(2000).setStartDelay(3000);
        t1.animate().translationY(-1900).setDuration(2000).setStartDelay(3000);
        i1.animate().translationY(-1900).setDuration(2000).setStartDelay(3000);
*/

        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Splashmain.this,Walk.class);
                startActivity(i);
            }
        });
    }
    @Override
    public void onBackPressed() {
        // finish();
        moveTaskToBack(true);
        // super.onBackPressed();
    }

}