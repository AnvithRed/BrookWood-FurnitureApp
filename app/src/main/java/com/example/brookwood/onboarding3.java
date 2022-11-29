package com.example.brookwood;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class onboarding3 extends AppCompatActivity {

    private static int SPLASH_SCREEN=5000;

    ImageView ob1,s1;
    TextView head,subs;
    ImageButton nextbtn;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        try {
            this.getSupportActionBar().hide();
        }
        // catch block to handle NullPointerException
        catch (NullPointerException e) {
        }
        setContentView(R.layout.activity_onboarding3);
        ob1 =findViewById(R.id.imageView2);
        s1 =findViewById(R.id.imageView3);
        head =findViewById(R.id.textView3);
        subs =findViewById(R.id.textView4);
        nextbtn=findViewById(R.id.next);

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(onboarding3.this,loginpage.class);

                Pair[] pairs = new Pair[4];

                pairs[0]= new Pair<View,String>(ob1,"logo_image");
                pairs[1]= new Pair<View,String>(head,"logo_text");
                pairs[2]= new Pair<View,String>(subs,"subs_trans");
                pairs[3]= new Pair<View,String>(s1,"scroll_trans");

                ActivityOptions options= ActivityOptions.makeSceneTransitionAnimation(onboarding3.this,pairs);
                startActivity(intent, options.toBundle());

            }
        });





    }
}