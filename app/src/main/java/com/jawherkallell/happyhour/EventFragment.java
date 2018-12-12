package com.jawherkallell.happyhour;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jawherkallell.happyhour.Details.card.EventAdapter;
import com.jawherkallell.happyhour.Json.model.Event;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jawherkallell.happyhour.Remote.IMyAPI;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventFragment extends Fragment {
    IMyAPI mService;
    Event mEvent;
    RecyclerView recyclerView;



    public EventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        mService=IMyAPI.retrofit.create(IMyAPI.class);
        recyclerView=(RecyclerView) view.findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Call<List<Event>> call =  mService.getEvents("/mysuperapi/list.php");

        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {

                // Toast.makeText(getContext(),""+response.body().size(),Toast.LENGTH_LONG).show();                    //creating recyclerview adapter
                EventAdapter adapter = new EventAdapter(getActivity(), response.body());
                //setting adapter to recyclerview
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                Toast.makeText(getActivity(),"fail",Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

}
