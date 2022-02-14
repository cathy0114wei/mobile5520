package com.example.numad22sp_xueningwei;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewHolder> {
    private final ArrayList<ItemCard> itemList;
    private ItemClickListener listener;
    Context context;

    //Constructor
    public ReviewAdapter(ArrayList<ItemCard> list, Context context){
        this.itemList = list;
        this.context = context;
    }
    public void setOnItemClickListener(ItemClickListener listener){
        this.listener = listener;
    }
    @Override
    public ReviewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new ReviewHolder(context, view, listener);
    }
    @Override
    public int getItemCount(){return itemList.size();}

    @Override
    public void onBindViewHolder(ReviewHolder holder, int position){
        ItemCard curIntem = itemList.get(position);
        holder.itemName.setText(curIntem.getName());
        holder.itemURL.setText(curIntem.getURL());
    }
}
