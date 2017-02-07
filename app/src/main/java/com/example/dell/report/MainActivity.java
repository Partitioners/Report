package com.example.dell.report;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public String output;
    public static ArrayList<String> cols;
    public static ArrayList<String> cols1;
    public EditText t1;
    private ImageView btnSpeak;
    long count=0;
    public static final int REQ_CODE_SPEECH_INPUT = 100;
    public int index=0;
    public static int is=0;
    public static int is1=0;

    public static int rootid = 0;
    public static FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();

    public static DatabaseReference root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Report Generata");
        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_field);
        cols=new ArrayList<>();
        cols1=new ArrayList<>();

        store();
        btnSpeak = (ImageView) findViewById(R.id.btnSpeak);
        t1 = (EditText) findViewById(R.id.editText);
        Button b1 = (Button) findViewById(R.id.addfield);
        Button b2 = (Button) findViewById(R.id.additem_button);

        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });



        b1.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                index++;
                cols1.add(t1.getText().toString());
                //Toast.makeText(MainActivity.this,t1.getText().toString(),Toast.LENGTH_LONG).show();
                t1.setText("");

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.e("value at",""+cols1.size());

                    //Log.e("value at",""+cols1.size());
                    root = firebaseDatabase.getReference("fields");
                    root.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            //root.child(is1+"").setValue(cols1);
                            //is1++;

                            count = dataSnapshot.getChildrenCount();
                           // Log.e("value at",""+count);

                            for (String colName:cols1) {
                                root.child(String.valueOf(count++)).setValue(colName);


                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }

                    });

                //root.setValue(cols1);

                store();
                startActivity(new Intent(MainActivity.this, add_useritem.class));
                //Toast.makeText(AddFields.this,cols.size()+"",Toast.LENGTH_LONG).show();

            }
        });
    }

    private void store()
    { FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();

        final DatabaseReference root1=firebaseDatabase.getReference().child("fields");
        root1.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<List<String>> t = new GenericTypeIndicator<List<String>>() {};

                cols=(ArrayList<String>) dataSnapshot.getValue(t);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }});


    }


    private void promptSpeechInput() {
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

    /**
     * Receiving speech input
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    t1.setText(result.get(0));

                }
                break;
            }

        }
    }
}









    /*public String output;
    public static ArrayList<String> cols=new ArrayList<>();
    public EditText t1;
    private Button btnSpeak;
    public static final int REQ_CODE_SPEECH_INPUT = 100;
    public int index=0;

    public static FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    public static DatabaseReference root=firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_field);
        btnSpeak = (Button) findViewById(R.id.btnSpeak);
        t1 = (EditText) findViewById(R.id.editText);
        Button b1 = (Button) findViewById(R.id.addfield);
        Button b2 = (Button) findViewById(R.id.additem_button);

        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                cols.add(t1.getText().toString());
                root.child(index+"").setValue(t1.getText().toString());
                index++;
                t1.setText("");


            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, add_useritem.class));
            }
        });

    }
    private void promptSpeechInput() {
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

    /**
     * Receiving speech input
     * */
    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    t1.setText(result.get(0));

                }
                break;
            }

        }
    }
}*/
