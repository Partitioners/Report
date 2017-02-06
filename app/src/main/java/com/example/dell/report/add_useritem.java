package com.example.dell.report;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Locale;

import static com.example.dell.report.MainActivity.REQ_CODE_SPEECH_INPUT;
import static com.example.dell.report.MainActivity.cols;
import static com.example.dell.report.MainActivity.root;

/**
 * Created by DELL on 04-02-2017.
 */

public class add_useritem extends AppCompatActivity {

    public static ArrayList<String> users;


    public Button additem;
    public static ArrayList<ArrayList> rows;
    public FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    private DatabaseReference root=firebaseDatabase.getReference();
    int rootid;
    private EditText e;

    public static int itemid=1;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);
        e=(EditText) findViewById(R.id.edit);
        users=new ArrayList<>();
        rows=new ArrayList<>();
        WishlistCustomAdapter adapter=new WishlistCustomAdapter(this,cols);
        listView = (ListView) findViewById(R.id.list);
        additem=(Button) findViewById(R.id.add);

        listView.setAdapter(adapter);




    additem.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(users !=null){rows.add(users);}
            for(rootid=0;rootid<cols.size();rootid++) {
                root.child(rootid+"").child(rootid +cols.size()*itemid+"").setValue(users.get(rootid +cols.size()*(itemid-1)));

            }
        itemid++;}
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    e.setText(result.get(0).toCharArray().toString());
                break;
            }

        }
    }
}}



