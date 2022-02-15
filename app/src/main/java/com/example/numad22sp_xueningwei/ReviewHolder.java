package com.example.numad22sp_xueningwei;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

public class ReviewHolder extends RecyclerView.ViewHolder {
    public TextView itemName;
    public TextView itemURL;
    public ReviewHolder(Context context, View itemView, final ItemClickListener listener){
        super(itemView);
        itemURL = itemView.findViewById(R.id.URL);
        itemName = itemView.findViewById(R.id.itemName);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null){
                    int position = getLayoutPosition();
                    if(position != RecyclerView.NO_POSITION){
                        listener.onItemClick(position);
                    }
                }
            }
        });
        itemURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(URLUtil.isValidUrl(itemURL.getText().toString())){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(itemURL.getText().toString()));
                    context.startActivity(intent);
                } else {
                    Snackbar snackbar = Snackbar.make(
                            view, "Invalid URL, plz change", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });
    }
}
