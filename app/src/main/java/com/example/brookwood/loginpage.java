package com.example.brookwood;

import androidx.annotation.NonNull;
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

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class loginpage extends AppCompatActivity {

    Button callSignUp,setlogin,fpbtn;
    ImageView image;
    TextView logoText, sloganText;
    TextInputLayout username,password;

    private Boolean validateUsername() {
        String val = username.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        if (val.isEmpty()) {
            username.setError("Field cannot be empty");
            return false;
        }  else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = password.getEditText().getText().toString();

          if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;

        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    public void loginUser(View view)
    {
        if (!validateUsername() | !validatePassword())
        {
            return;
        }
        else{
            isUser();
        }
    }

    public void isUser()
    {

        String userEnteredUsername = username.getEditText().getText().toString().trim();
        String userEnteredPassword = password.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {

                if(datasnapshot.exists()) {

                    username.setError((null));
                    username.setErrorEnabled(false);

                    String passwordFromDB = datasnapshot.child(userEnteredUsername).child("password").getValue(String.class);

                    if(passwordFromDB.equals(userEnteredPassword)){

                        username.setError((null));
                        username.setErrorEnabled(false);

                        String nameFromDB = datasnapshot.child(userEnteredUsername).child("name").getValue(String.class);
                        String usernameFromDB = datasnapshot.child(userEnteredUsername).child("username").getValue(String.class);
                        String phoneNoFromDB = datasnapshot.child(userEnteredUsername).child("phoneNo").getValue(String.class);
                        String emailFromDB = datasnapshot.child(userEnteredUsername).child("email").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(),HomeScreen.class);

                        intent.putExtra("name",nameFromDB);
                        intent.putExtra("username",usernameFromDB);

                        startActivity(intent);




                    }
                    else{
                        password.setError("Wrong Password");
                        password.requestFocus();
                    }
                }
                else{
                    username.setError("No Such User Exist");
                    username.requestFocus();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }





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
        setContentView(R.layout.activity_loginpage);

        callSignUp =findViewById(R.id.signup);
        setlogin =findViewById(R.id.signin);
        image =findViewById(R.id.logo);
        logoText =findViewById(R.id.logo_text);
        sloganText =findViewById(R.id.subs);
        username =findViewById(R.id.username);
        password =findViewById(R.id.password);
        fpbtn =findViewById(R.id.fp);

        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(loginpage.this,SignUp.class);

                Pair[] pairs = new Pair[2];

                pairs[0]= new Pair<View,String>(image,"logo_image");

                pairs[1]= new Pair<View,String>(setlogin,"signin_trans");

                ActivityOptions options= ActivityOptions.makeSceneTransitionAnimation(loginpage.this,pairs);
                startActivity(intent, options.toBundle());

            }
        });
    }
}