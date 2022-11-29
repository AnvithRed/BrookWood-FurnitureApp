package com.example.brookwood;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeScreen extends AppCompatActivity {

    ImageView sofa,bed,table,recliner,chair,decor;
    TextView sofaname,bedname,tablename,reclinername,chairname,decorname,dispname;
    FloatingActionButton floatingActionButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        try {
            this.getSupportActionBar().hide();
        }
        // catch block to handle NullPointerException
        catch (NullPointerException e) {
        }

        setContentView(R.layout.activity_home_screen);

        sofa= findViewById(R.id.imageView1);
        bed= findViewById(R.id.imageView2);
        table= findViewById(R.id.imageView3);
        recliner= findViewById(R.id.imageView4);
        chair= findViewById(R.id.imageView5);
        decor= findViewById(R.id.imageView6);
        sofaname = findViewById(R.id.textView1);
        bedname = findViewById(R.id.textView2);
        tablename = findViewById(R.id.textView3);
        reclinername = findViewById(R.id.textView4);
        chairname = findViewById(R.id.textView5);
        decorname = findViewById(R.id.textView6);
        dispname = findViewById(R.id.textView11);
        floatingActionButton = findViewById(R.id.butcart);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomeScreen.this, cart.class);
                startActivity(intent);



            }
        });


        showalldata();




        sofa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Pair[] pairs = new Pair[2];

                pairs[0]= new Pair<View,String>(sofa,"trans_sofa");
                pairs[1]= new Pair<View,String>(sofaname,"trans_sofaname");


                Intent intent = new Intent(HomeScreen.this, sofalist.class);
                ActivityOptions options= ActivityOptions.makeSceneTransitionAnimation(HomeScreen.this,pairs);
                startActivity(intent, options.toBundle());
            }
        });

        bed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Pair[] pairs = new Pair[2];

                pairs[0]= new Pair<View,String>(bed,"trans_bed");
                pairs[1]= new Pair<View,String>(bedname,"trans_bedname");


                Intent intent = new Intent(HomeScreen.this, bedlist.class);
                ActivityOptions options= ActivityOptions.makeSceneTransitionAnimation(HomeScreen.this,pairs);
                startActivity(intent, options.toBundle());
            }
        });

        table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Pair[] pairs = new Pair[2];

                pairs[0]= new Pair<View,String>(table,"trans_table");
                pairs[1]= new Pair<View,String>(tablename,"trans_tablename");


                Intent intent = new Intent(HomeScreen.this, tablelist.class);
                ActivityOptions options= ActivityOptions.makeSceneTransitionAnimation(HomeScreen.this,pairs);
                startActivity(intent, options.toBundle());
            }
        });

        recliner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Pair[] pairs = new Pair[2];

                pairs[0]= new Pair<View,String>(recliner,"trans_rec");
                pairs[1]= new Pair<View,String>(reclinername,"trans_recname");


                Intent intent = new Intent(HomeScreen.this, reclinerList.class);
                ActivityOptions options= ActivityOptions.makeSceneTransitionAnimation(HomeScreen.this,pairs);
                startActivity(intent, options.toBundle());
            }
        });
        chair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Pair[] pairs = new Pair[2];

                pairs[0]= new Pair<View,String>(chair,"trans_chair");
                pairs[1]= new Pair<View,String>(chairname,"trans_chairname");


                Intent intent = new Intent(HomeScreen.this, chairlist.class);
                ActivityOptions options= ActivityOptions.makeSceneTransitionAnimation(HomeScreen.this,pairs);
                startActivity(intent, options.toBundle());
            }
        });

        decor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Pair[] pairs = new Pair[2];

                pairs[0]= new Pair<View,String>(decor,"trans_decor");
                pairs[1]= new Pair<View,String>(decorname,"trans_decorname");


                Intent intent = new Intent(HomeScreen.this, decorlist.class);
                ActivityOptions options= ActivityOptions.makeSceneTransitionAnimation(HomeScreen.this,pairs);
                startActivity(intent, options.toBundle());
            }
        });





    }

    private void showalldata() {
        Intent intent= getIntent();
        String user_username= intent.getStringExtra("username");

        dispname.setText(user_username);
    }
}