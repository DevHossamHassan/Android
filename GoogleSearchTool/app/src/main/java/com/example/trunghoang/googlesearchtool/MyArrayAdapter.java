package com.example.trunghoang.googlesearchtool;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.trunghoang.model.Result;

import java.util.List;

/**
 * Created by trunghoang on 7/9/15.
 */
public class MyArrayAdapter extends ArrayAdapter<Result> {
    Activity context;
    int resource;
    List<Result> objects;

    public MyArrayAdapter(Activity context, int resource, List<Result> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View customRow = inflater.inflate(resource, null);
        TextView txtTitle = (TextView)customRow.findViewById(R.id.txtTitle);
        TextView txtLink = (TextView)customRow.findViewById(R.id.txtLink);
        Result result = objects.get(position);
        txtTitle.setText(result.getTitleNoFormatting());
        txtLink.setText(result.getUrl());

        if(position%2==0)
            txtTitle.setTextColor(this.context.getResources().getColor(android.R.color.holo_blue_dark));
        else
            txtTitle.setTextColor(this.context.getResources().getColor(android.R.color.holo_red_dark));
        return customRow;

    }
}
