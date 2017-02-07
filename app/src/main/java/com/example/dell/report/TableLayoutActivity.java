package com.example.dell.report;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class TableLayoutActivity extends AppCompatActivity {
    ArrayList<String> head;
    ArrayList<ArrayList<String>> rows;
    ArrayList<String> r1;
    ArrayList<String> r2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_layout);
        head=new ArrayList<>();
        rows=new ArrayList<>();
        r1=new ArrayList<>();
        r2=new ArrayList<>();
        r1.add("Aniket");
        r1.add("0");
        r1.add("1");
        r1.add("1");
        r1.add("1");
        r1.add("1");



        r2.add("Keshav");
        r2.add("100");
        r2.add("100");
        r2.add("100");
        r2.add("100");
        r2.add("100");

        rows.add(r1);
        rows.add(r2);
        head.add("Name");
        head.add("Maths");
        head.add("Physics");
        head.add("Chem");
        head.add("sd");
        head.add("fdvd");

        init();

    }
    public void init() {
        android.widget.TableLayout stk = (android.widget.TableLayout) findViewById(R.id.table_main);

        TableRow tbrow0 = new TableRow(this);
        for(int i=0;i<head.size();i++){
            TextView tv0 = new TextView(this);
            tv0.setText(head.get(i));
            tv0.setTextColor(Color.WHITE);
            tbrow0.addView(tv0);}
        stk.addView(tbrow0);
        for (int j = 0; j < rows.size(); j++) {
            TableRow tbrow = new TableRow(this);
            for(int k=0;k<head.size();k++){
                TextView t1v = new TextView(this);
                t1v.setBackgroundColor(0);



                if(rows.get(j).get(k)!= null){
                    t1v.setText(rows.get(j).get(k));}

                t1v.setTextColor(Color.WHITE);
                //t1v.setGravity(Gravity.CENTER);
                tbrow.addView(t1v);
                }
            stk.addView(tbrow);
        }
        stk.setStretchAllColumns(true);
    }
}
