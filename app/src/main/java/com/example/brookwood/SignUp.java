package com.example.brookwood;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    Context context;

    TextInputLayout regname,reguser,regphone,regemail,regpass;
    Button btngo ,btnback;
    ImageView reglogo;
    TextView reghead,regsubs;

    private Boolean validateName() {
        String val = regname.getEditText().getText().toString();
        if (val.isEmpty()) {
            regname.setError("Field cannot be empty");
            return false;
        }
        else {
            regname.setError(null);
            regname.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateUsername() {
        String val = reguser.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        if (val.isEmpty()) {
            reguser.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            reguser.setError("Username too long");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            reguser.setError("White Spaces are not allowed");
            return false;
        } else {
            reguser.setError(null);
            reguser.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val = regemail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()) {
            regemail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            regemail.setError("Invalid email address");
            return false;
        } else {
            regemail.setError(null);
            regemail.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePhoneNo() {
        String val = regphone.getEditText().getText().toString();
        if (val.isEmpty()) {
            regphone.setError("Field cannot be empty");
            return false;
        } else {
            regphone.setError(null);
            regphone.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = regpass.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";
        if (val.isEmpty()) {
            regpass.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            regpass.setError("Password is too weak");
            return false;
        } else {
            regpass.setError(null);
            regpass.setErrorEnabled(false);
            return true;
        }
    }
    //This function will execute when user click on Register Button


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
        setContentView(R.layout.activity_sign_up);



        regname =findViewById(R.id.reg_name);
        regemail =findViewById(R.id.reg_email);
        reguser =findViewById(R.id.reg_user);
        regphone =findViewById(R.id.reg_phone);
        regpass =findViewById(R.id.reg_pass);
        btnback =findViewById(R.id.reg_back);
        btngo = findViewById(R.id.reg_go);
        reglogo = findViewById(R.id.reg_logo);
        reghead = findViewById(R.id.reg_head);
        regsubs = findViewById(R.id.reg_subs);



        btngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");
                if(!validateName() | !validatePassword() | !validatePhoneNo() | !validateEmail() | !validateUsername()){
                    return;
                }


                String name = regname.getEditText().getText().toString();
                String username = reguser.getEditText().getText().toString();
                String email = regemail.getEditText().getText().toString();
                String phoneNo = regphone.getEditText().getText().toString();
                String password = regpass.getEditText().getText().toString();


                UserHelperClass helperClass = new UserHelperClass(name, username, email, phoneNo, password);


                reference.child(username).setValue(helperClass);

                Toast.makeText(context, "Sucessfully Created" , Toast.LENGTH_SHORT).show();
            }
        });


        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this,loginpage.class);

                startActivity(intent);

            }
        });

    }
}