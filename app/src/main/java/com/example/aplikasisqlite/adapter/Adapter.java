package com.example.aplikasisqlite.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.aplikasisqlite.R;
import com.example.aplikasisqlite.model.Data;

import java.util.List;
import java.util.Objects;

public class Adapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Data> items;

    public Adapter(Activity activity, List<Data> items){
        this.activity= activity;
        this.items = items;
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int location) {
        return items.get(location);
    }

    public long getItemId(int position){
        return  position;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        if(inflater == null){
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_row, null);
        }
        TextView id = (TextView) convertView.findViewById(R.id.id);
        TextView name = (TextView) convertView.findViewById(R.id.name) ;
        TextView tanggal = (TextView) convertView.findViewById(R.id.tanggal) ;
        TextView jenis = (TextView) convertView.findViewById(R.id.jenis) ;
        TextView address = (TextView) convertView.findViewById(R.id.address) ;

        Data data = items.get (position) ;

        id.setText(data.getId());
        name.setText(data.getNama());
        tanggal.setText(data.getTgl());
        jenis.setText(data.getJenis());
        address.setText(data.getAlamat());
        return convertView;
    }
}
