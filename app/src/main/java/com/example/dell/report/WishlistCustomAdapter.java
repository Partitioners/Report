package com.example.dell.report;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;

import static com.example.dell.report.MainActivity.cols;

/**
 * Created by dell on 12/19/2016.
 */

public class WishlistCustomAdapter extends ArrayAdapter<String> {

    ArrayList<String> gotList;
    add_useritem context;
    EditText name;

    public WishlistCustomAdapter(add_useritem context, ArrayList<String> words) {
        super(context, 0, words);
        this.context = context;
        gotList=words;
    }

    @Override
    public int getCount() {
        return gotList.size();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.movie_list_row, parent, false);
        }
        String currentWord=gotList.get(position);
        name = (EditText) listItemView.findViewById(R.id.edit);
        name.setHint(currentWord);
        final ImageButton btn=(ImageButton)listItemView.findViewById(R.id.sound);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.promptSpeechInput();

            }
        });
        if(name.getText()!=null){
            add_useritem.users.add(position,name.getText().toString());

        }



        return listItemView;
    }
    public void setEdittext(String s){
        name.setText(s);

    }


    }
