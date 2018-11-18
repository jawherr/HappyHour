package com.jawherkallell.happyhour;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

public class RestoAdapter extends BaseAdapter {
    private Activity context;
    private List<Restaurant> restoList;
    private static LayoutInflater inflater =null;

    public RestoAdapter(Activity context, List<Restaurant> restoList) {
        this.context = context;
        this.restoList = restoList;
        inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }



    @Override
    public int getCount() {
        return restoList.size();
    }

    @Override
    public Object getItem(int i) {
        return restoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemview= view;
        itemview= (itemview==null) ? inflater.inflate(R.layout.list_item, null):itemview;
        TextView name=(TextView) itemview.findViewById(R.id.textViewName);
        TextView Adress=(TextView) itemview.findViewById(R.id.textViewAdress);

        Restaurant r= (Restaurant)getItem(i);
        name.setText(((Restaurant) getItem(i)).getNom());
        Adress.setText(((Restaurant) getItem(i)).getAdresse());
        return itemview;
    }
}
