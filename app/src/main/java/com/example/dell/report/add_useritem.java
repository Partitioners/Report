package com.example.dell.report;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
    public ArrayList<EditText> et;
    public static ArrayList<String> users;
    public EditText tv;

    public Button spk,additem;
    public static ArrayList<ArrayList> rows;
    public FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    private DatabaseReference root=firebaseDatabase.getReference();
    int rootid;

    public static int itemid=1;
    int ind=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);
LinearLayout.LayoutParams buttonlayout=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        buttonlayout.setMargins(5,5,5,5);
        et=new ArrayList<>();
        users=new ArrayList<>();
        rows=new ArrayList<>();
        LinearLayout ll = (LinearLayout)findViewById(R.id.activity_add_item);
       spk=new Button(this);

        additem=new Button(this);
        //spk.setId(R.id.speaks);
        spk.setText("Speak");
        additem.setText("Add to database");
        ll.addView(additem);
        ll.addView(spk);

        for(int i=0;i<cols.size();i++){
            tv=new EditText(this);
            tv.setHint(cols.get(i));
            tv.setHintTextColor(getResources().getColor(R.color.gray));
            tv.setLayoutParams(buttonlayout);
            et.add(tv);
            ll.addView(et.get(i));}

        spk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });
    additem.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            for(rootid=0;rootid<cols.size();rootid++) {
                root.child(rootid+"").child(rootid +cols.size()*itemid+"").setValue(users.get(rootid +cols.size()*(itemid-1)));

            }
        itemid++;}
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    et.get(ind).setText(result.get(0));
                    users.add(et.get(ind).getText().toString());
                    ind++;
                    if(ind==cols.size()) {
                        rows.add(users);
                    ind=0;}
                    }
                break;
            }

        }
    }
}



