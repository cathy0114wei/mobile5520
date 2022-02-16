package com.example.numad22sp_xueningwei;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.android.material.snackbar.Snackbar;

public class Dialog extends AppCompatDialogFragment {
    public interface DialogListener {
        void transferInfo(int position, String webName, String URL);
    }

    private EditText editTextWebsite;
    private EditText editTextURL;
    private DialogListener listener;

    private ItemCard item;
    private int position;

    public Dialog(ItemCard item, int position){
        this.item = item;
        this.position = position;
    }

    @Override
    public AlertDialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        editTextWebsite = view.findViewById(R.id.editWebsite);
        editTextURL = view.findViewById(R.id.editURL);

        String addOrEdit;
        if (item == null) {
            addOrEdit = "Add a item";
        } else {
            addOrEdit ="Edit this item";
            editTextWebsite.setText(item.getName());
            editTextURL.setText(item.getURL());

        }

        builder.setView(view)
                .setTitle(addOrEdit)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String webSite = editTextWebsite.getText().toString();
                        String URL = editTextURL.getText().toString();

                        listener.transferInfo(position, webSite, URL);

                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try {
            listener = (DialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement DialogListener");
        }
    }
}
