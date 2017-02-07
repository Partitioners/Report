package com.example.dell.report;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.dell.report.MainActivity.cols;
import static com.example.dell.report.add_useritem.rows;

public class TableLayoutActivity extends AppCompatActivity {

    Intent report_intent;
    int i,j,k;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_layout);
        init();
        report_intent=new Intent(this,ReportActivity.class);


    }
    public void init() {
        android.widget.TableLayout stk = (android.widget.TableLayout) findViewById(R.id.table_main);

        TableRow tbrow0 = new TableRow(this);
        for(i=0;i<cols.size();i++){
            TextView tv0 = new TextView(this);
            tv0.setText(cols.get(i));
            tv0.setTextColor(Color.WHITE);
            tbrow0.addView(tv0);}
        stk.addView(tbrow0);
        for (j = 0; j < rows.size(); j++) {
            TableRow tbrow = new TableRow(this);
            for(k=0;k<cols.size();k++){
                TextView t1v = new TextView(this);
                t1v.setBackgroundColor(0);



                if(rows.get(j).get(k)!= null){
                    t1v.setText(rows.get(j).get(k).toString());}

                t1v.setTextColor(Color.WHITE);
                t1v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                //t1v.setGravity(Gravity.CENTER);
                tbrow.addView(t1v);
                t1v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        report_intent.putExtra("",j);
                        startActivity(report_intent);
                    }
                });

                }
            stk.addView(tbrow);
        }
        stk.setStretchAllColumns(true);
    }
}
