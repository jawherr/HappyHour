package com.jawherkallell.happyhour;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class EventDetails extends AppCompatActivity {
    private ImageView img;
    private TextView add,date,descrip;
    private CollapsingToolbarLayout collapsingToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        Intent intent = getIntent();
        ImageView img=(ImageView)findViewById(R.id.backdrop2);
        add=(TextView) findViewById(R.id.textViewAdress);
        date=(TextView) findViewById(R.id.textViewDate);
        descrip=(TextView) findViewById(R.id.textViewDescription);
        int imgId=Integer.parseInt(intent.getStringExtra("Img"));
        // add.setText(intent.getStringExtra("add"));
        descrip.setText(intent.getStringExtra("descrip"));
        date.setText(intent.getStringExtra("date"));
        img.setImageDrawable(this.getResources().getDrawable(imgId));
        collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(intent.getStringExtra("name"));

    }
}
