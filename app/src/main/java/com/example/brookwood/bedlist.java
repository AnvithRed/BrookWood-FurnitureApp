package com.example.brookwood;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class bedlist extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<item> itemArrayList;
    RecyclerAdapter adapter;
    FirebaseFirestore db;
    ProgressDialog progressDialog;

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
        setContentView(R.layout.activity_bedlist);



        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("A Momemt Please");
        progressDialog.show();

        recyclerView= findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        itemArrayList = new ArrayList<item>();
        adapter = new RecyclerAdapter(bedlist.this,itemArrayList);
        recyclerView.setAdapter(adapter);

        EventChangeListener();




    }

    private void EventChangeListener() {

        db.collection("bed")  //folder for data
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                                         @Override
                                         public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                                             if (error != null){

                                                 if(progressDialog.isShowing())
                                                     progressDialog.dismiss();

                                                 Log.e("FireStone Error",error.getMessage());
                                                 return;
                                             }

                                             for(DocumentChange dc : value.getDocumentChanges()){

                                                 if (dc.getType() == DocumentChange.Type.ADDED){

                                                     itemArrayList.add(dc.getDocument().toObject(item.class));

                                                 }

                                                 adapter.notifyDataSetChanged();
                                                 if(progressDialog.isShowing())
                                                     progressDialog.dismiss();

                                             }

                                         }
                                     }
                );
    }
}