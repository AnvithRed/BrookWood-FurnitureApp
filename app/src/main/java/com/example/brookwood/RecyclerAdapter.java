package com.example.brookwood;

import android.animation.Animator;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.auth.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import adapter.recyclerViewAdapter;
import p32929.androideasysql_library.Column;
import p32929.androideasysql_library.EasyDB;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    Context context;
    ArrayList<item> itemArrayList;
    FirebaseDatabase rootNode;
    DatabaseReference reference;


    public RecyclerAdapter(Context context, ArrayList<item> itemArrayList ) {
        this.context = context;
        this.itemArrayList = itemArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View V = LayoutInflater.from(context).inflate(R.layout.row,parent,false);//layout change

        return new MyViewHolder(V);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        item item = itemArrayList.get(position);

        String head= item.getHead();
        String cost = item.getCost();

        holder.headview.setText(item.head);
        holder.costview.setText(item.cost);

        String imageurl=null;
        imageurl=item.getImage();
        Picasso.get().load(imageurl).into(holder.imageview);

        String finalImageurl = imageurl;
        holder.cartbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addtocart(head,cost, finalImageurl);


            }
        });

    }
    public int itemid=1;
     public void addtocart(String head,String cost, String imageurl){

         Random random = new Random();


         itemid= random.nextInt(200000);
         EasyDB easyDB = EasyDB.init(context,"ITEM_DB")
                 .setTableName("ITEM_TABLE")
                 .addColumn(new Column("item_id",new String[]{"text","unique"}))
                 .addColumn(new Column("item_name",new String[]{"text","not null"}))
                 .addColumn(new Column("item_cost",new String[]{"text","not null"}))
                 .addColumn(new Column("item_image",new String[]{"text","not null"}))
                 .doneTableColumn();

         Boolean b= easyDB.addData("item_id",itemid)
                 .addData("item_name",head)
                 .addData("item_cost",cost)
                 .addData("item_image",imageurl)
                 .doneDataAdding();
         Toast.makeText(context, "Added to Cart" , Toast.LENGTH_SHORT).show();




     }



    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{


        TextView headview,costview;
        ImageView imageview;
        Button cartbutton;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            headview = itemView.findViewById(R.id.textView8);
            costview = itemView.findViewById(R.id.textView9);
            imageview = itemView.findViewById(R.id.imageView8);
            cartbutton = itemView.findViewById(R.id.button);
        }
    }

}