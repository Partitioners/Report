package com.example.dell.report;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.example.dell.report.MainActivity.REQ_CODE_SPEECH_INPUT;
import static com.example.dell.report.MainActivity.cols;
import static com.example.dell.report.MainActivity.is;
import static com.example.dell.report.MainActivity.root;
import static com.example.dell.report.MainActivity.rootid;

/**
 * Created by DELL on 04-02-2017.
 */

public class add_useritem extends AppCompatActivity {


   // public ArrayList<EditText> et;
    public static ArrayList<String> users;
    //public static ArrayList<String> users1;
    public EditText tv;

    public Button additem;
    public static ArrayList<ArrayList> rows;
    public FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference root;

    //int pressed=0;
    //public static int itemid = 1;
    //public static int ind = 0;
    WishlistCustomAdapter adapter;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);
        users=new ArrayList<>();
        rows=new ArrayList<>();
        adapter=new WishlistCustomAdapter(this,cols);
        listView = (ListView) findViewById(R.id.list);
        additem=(Button) findViewById(R.id.add);
        listView.setAdapter(adapter);

        additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("value","size of users is "+users.size());



/*if(is>1){
        //Log.e("check size", "itemid at" + itemid + " cols at " + cols.size());
                root = firebaseDatabase.getReference("rows");
                    root.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            long count = 0;
                            for (Arraylist colName:rows) {
                                root.child(String.valueOf(count++)).setValue(colName);
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });}*/


                root = firebaseDatabase.getReference("rows");
                root.child(rootid + "").setValue(users);
                rootid++;
                retrieve();
                startActivity(new Intent(add_useritem.this,TableLayoutActivity.class));

            }



    });
    }

    public void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }

private void retrieve()
    { FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();

        final DatabaseReference root1=firebaseDatabase.getReference().child("rows");
        root1.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> getChildren=dataSnapshot.getChildren();
                GenericTypeIndicator<List<String>> t = new GenericTypeIndicator<List<String>>() {};
                for(DataSnapshot child:getChildren)
                {rows.add((ArrayList<String>)child.getValue(t));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }});


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    adapter.setEdittext(result.get(0));
                break;
            }

        }
    }
}}



