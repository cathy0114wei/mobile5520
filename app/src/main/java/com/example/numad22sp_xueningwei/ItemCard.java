package com.example.numad22sp_xueningwei;

import android.content.Context;

public class ItemCard implements ItemClickListener{
    private String name;
    private String URL;
    public ItemCard(String name, String URL){
        this.name = name;
        this.URL = URL;
    }
    @Override
    public void onItemClick(int position){

    }

    public String getName() {
        return name;
    }

    public String getURL() {
        return URL;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
