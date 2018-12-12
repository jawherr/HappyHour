package com.jawherkallell.happyhour;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomeFragment extends Fragment {

    private TextView textView;
    private Button BtnLogOut;
    OnLogoutListener logoutListener;
    public  interface OnLogoutListener
    {
        public void logoutPerformed();
    }

    public WelcomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_welcome, container, false);
        textView = view.findViewById(R.id.txt_name_info);
        textView.setText("Welcome "+MainActivity.prefConfig.readName());
        BtnLogOut = view.findViewById(R.id.btn_logout);

        BtnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity mainActivity=(MainActivity)getActivity();
                mainActivity.getSession().unset();
                //mainActivity.getSharedPreferences("username",0).edit().clear().commit();
                mainActivity.setSession(null);
                Profile profile_fragment = new Profile();
                getActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.container, profile_fragment).commit();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity= (Activity) context;
        logoutListener = (OnLogoutListener) activity;
    }
}
