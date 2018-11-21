package com.jawherkallell.happyhour;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SearchView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment{

ImageButton btRes,btCafe,btBar;
    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        btRes=(ImageButton)view.findViewById(R.id.btnRestaurent);
        btRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), RestaurentActivity.class);
                view.getContext().startActivity(intent);
            }
        });
        btCafe=(ImageButton)view.findViewById(R.id.btnCafe);
        btCafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), CafeActivity.class);
                view.getContext().startActivity(intent);
            }
        });
        btBar=(ImageButton)view.findViewById(R.id.btnBar);
        btBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), BarActivity.class);
                view.getContext().startActivity(intent);
            }
        });
        return view;
    }


}
