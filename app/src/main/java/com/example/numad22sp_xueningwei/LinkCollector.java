package com.example.numad22sp_xueningwei;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class LinkCollector extends AppCompatActivity implements Dialog.DialogListener{
    private ArrayList<ItemCard> itemList = new ArrayList<>();

    //    private TextView textView;
    private RecyclerView recyclerView;
    private ReviewAdapter reviewAdapter;
    private RecyclerView.LayoutManager rLayoutManger;
    private FloatingActionButton addButton;
    private String tips = "Tap URL to go to the website, tap website name to edit, swipe to delete";


    private static final String KEY_OF_INSTANCE = "KEY_OF_INSTANCE";
    private static final String NUMBER_OF_ITEMS = "NUMBER_OF_ITEMS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_collector);
        init(savedInstanceState);
//        textView = findViewById(R.id.textView);
        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(null, -1);
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Snackbar snackbar = Snackbar.make(recyclerView, "delete Item", Snackbar.LENGTH_LONG);
                snackbar.show();
                int position = viewHolder.getLayoutPosition();
                itemList.remove(position);

                reviewAdapter.notifyItemRemoved(position);

            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void openDialog(ItemCard item, int position){
        Dialog dialog = new Dialog(item, position);
        dialog.show(getSupportFragmentManager(), "the first dialog!");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {


        int size = itemList == null ? 0 : itemList.size();
        outState.putInt(NUMBER_OF_ITEMS, size);

        for (int i = 0; i < size; i++) {
            outState.putString(KEY_OF_INSTANCE + i + "0", itemList.get(i).getName());
            outState.putString(KEY_OF_INSTANCE + i + "1", itemList.get(i).getURL());

        }
        outState.putString("text", tips);
        super.onSaveInstanceState(outState);

    }

    private void init(Bundle savedInstanceState) {
        initialItemData(savedInstanceState);
        createRecyclerView();
    }

    private void initialItemData(Bundle savedInstanceState) {

        if (savedInstanceState != null && savedInstanceState.containsKey(NUMBER_OF_ITEMS)) {
            if (itemList == null || itemList.size() == 0) {

                int size = savedInstanceState.getInt(NUMBER_OF_ITEMS);

                for (int i = 0; i < size; i++) {
                    String itemName = savedInstanceState.getString(KEY_OF_INSTANCE + i + "0");
                    String itemURL = savedInstanceState.getString(KEY_OF_INSTANCE + i + "1");
                    ItemCard itemCard = new ItemCard(itemName, itemURL);

                    itemList.add(itemCard);
                }
            }
        }

    }

    private void createRecyclerView() {


        rLayoutManger = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        reviewAdapter = new ReviewAdapter(itemList, this);
        ItemClickListener itemClickListener = new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //attributions bond to the item has been changed
                itemList.get(position).onItemClick(position);
//                openDialog(itemList.get(position), position);
                reviewAdapter.notifyItemChanged(position);
            }



        };
        reviewAdapter.setOnItemClickListener(itemClickListener);

        recyclerView.setAdapter(reviewAdapter);
        recyclerView.setLayoutManager(rLayoutManger);


    }



    private void addItem(String website, String URL) {
        if(!URLUtil.isValidUrl(URL)){
            Snackbar snackbar = Snackbar.make(
                    recyclerView, "Invalid URL, plz change", Snackbar.LENGTH_LONG);
            snackbar.show();
        } else {
            itemList.add(0, new ItemCard(website, URL));
            Snackbar snackbar = Snackbar.make(recyclerView, "add Item", Snackbar.LENGTH_SHORT);
            snackbar.show();
            reviewAdapter.notifyItemInserted(0);
        }

    }

    //some functions which I would like to implement but assignment didn't mention
//    private void editItem(int position, String website, String url) {
//        itemList.get(position).setName(website);
//        itemList.get(position).setURL(url);
//        reviewAdapter.notifyItemChanged(position);
//        Snackbar snackbar = Snackbar.make(recyclerView, "edit Item successfully", Snackbar.LENGTH_LONG);
//        snackbar.show();
//    }

    @Override
    public void transferInfo(int position, String webName, String URL){
//        if(position == -1){
            addItem(webName, URL);
//        } else {
//            editItem(position, webName, URL);
//        }
    }

}