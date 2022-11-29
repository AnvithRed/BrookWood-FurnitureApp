package com.example.brookwood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterCart extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    public AdapterCart(Context context, ArrayList<ModelCart> modelCarts) {
        this.context = context;
        this.modelCarts = modelCarts;
    }

    Context context;
    ArrayList<ModelCart> modelCarts;



    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(context).inflate(R.layout.cart_rows,parent,false);

        return new RecyclerAdapter.MyViewHolder(V);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {



        String image = modelCarts.get(position).getImage();
        String name = modelCarts.get(position).getName();
        String cost = modelCarts.get(position).getCost();

        holder.costview.setText(cost);
        holder.headview.setText(name);

        try{

            Picasso.get().load(image).into(holder.imageview);

        }
        catch(Exception e){

        }


    }



    @Override
    public int getItemCount() {
        return modelCarts.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView cartimage;
        TextView cartname,cartcost;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cartimage = itemView.findViewById(R.id.imageView8);
            cartname= itemView.findViewById(R.id.textView8);
            cartcost = itemView.findViewById(R.id.textView9);
        }
    }
}
