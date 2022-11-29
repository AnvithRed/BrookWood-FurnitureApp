package com.example.brookwood;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class examplelist extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<item> itemArrayList;
    RecyclerAdapter adapter;
    FirebaseFirestore db;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examplelist);

         progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("A Momemt Please");
        progressDialog.show();

        recyclerView= findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        itemArrayList = new ArrayList<item>();
        adapter = new RecyclerAdapter(examplelist.this,itemArrayList);
        recyclerView.setAdapter(adapter);
        
        EventChangeListener();




    }

    private void EventChangeListener() {

        db.collection("sofa")  //folder for data
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