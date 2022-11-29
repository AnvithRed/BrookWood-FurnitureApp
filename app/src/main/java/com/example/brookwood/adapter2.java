package com.example.brookwood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adapter2 extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{

    Context context;
    ArrayList<item> itemArrayList;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    public adapter2(Context context, ArrayList<item> itemArrayList) {
        this.context = context;
        this.itemArrayList = itemArrayList;
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(context).inflate(R.layout.cart_rows,parent,false);//layout change

        return new RecyclerAdapter.MyViewHolder(V);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {

        item item = itemArrayList.get(position);

        String head= item.getHead();
        String cost = item.getCost();

        holder.headview.setText(item.head);
        holder.costview.setText(item.cost);

        String imageurl=null;
        imageurl=item.getImage();
        Picasso.get().load(imageurl).into(holder.imageview);


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
