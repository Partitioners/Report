package com.example.dell.report;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static com.example.dell.report.MainActivity.cols;
import static com.example.dell.report.add_useritem.rows;

public class ReportActivity extends AppCompatActivity {public static ArrayList<String> users;


    public Button additem;
    public FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    private DatabaseReference root=firebaseDatabase.getReference();
    int pos;
    ReportCustomAdapter adapter;

    public static int itemid=1;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        users=new ArrayList<>();
        pos=getIntent().getIntExtra("",0);
        adapter=new ReportCustomAdapter(this,cols,rows.get(pos));
        listView = (ListView) findViewById(R.id.report_list_view);

        listView.setAdapter(adapter);

    }
}
