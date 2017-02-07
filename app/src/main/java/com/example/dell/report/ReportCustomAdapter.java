package com.example.dell.report;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.attr.name;

/**
 * Created by dell on 12/19/2016.
 */

public class ReportCustomAdapter extends ArrayAdapter {

    ArrayList<String> report_cols;
    ArrayList<String> report_rows;
    TextView report_field_name,report_item_name;


    public ReportCustomAdapter(ReportActivity context, ArrayList<String> report_cols,ArrayList<String> report_rows) {
        super(context,0);
        this.report_cols=report_cols;
        this.report_rows=report_rows;
    }

    @Override
    public int getCount() {
        return report_rows.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.report_adapter_item, parent, false);
        }
        report_field_name=(TextView)listItemView.findViewById(R.id.report_field_textview);
        report_item_name=(TextView)listItemView.findViewById(R.id.report_item_textview);
        report_field_name.setText(report_cols.get(position));
        report_item_name.setText(report_rows.get(position));

        return listItemView;
    }


    }
