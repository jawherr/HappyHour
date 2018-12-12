package com.jawherkallell.happyhour;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationFragment extends Fragment {
    private EditText Name,UserName,UserPassword;
    private Button BtnRegister;

    public RegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        Name = view.findViewById(R.id.txtname);
        UserName = view.findViewById(R.id.txt_user_name);
        UserPassword = view.findViewById(R.id.txt_user_pass);
        BtnRegister = view.findViewById(R.id.btnRegister);
        BtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performRegistration();
            }
        });
        return view;
    }
    public void performRegistration()
    {
        String name = Name.getText().toString();
        String username = UserName.getText().toString();
        String password = UserPassword.getText().toString();
        Call<User> call = MainActivity.apiInterface.performRegistration(name,username,password);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if(response.body().getResponse().equals("ok"))
                {
                    //MainActivity.prefConfig.displayToast("Registration success ...");
                    Toast.makeText(getContext(), "Regster sucess", Toast.LENGTH_SHORT).show();
                }
                else if(response.body().getResponse().equals("exist"))
                {
                    //MainActivity.prefConfig.displayToast("User already exist...");
                    Toast.makeText(getContext(), "User already exist...", Toast.LENGTH_SHORT).show();
                }
                else if(response.body().getResponse().equals("error"))
                {
                    //MainActivity.prefConfig.displayToast("Something went wrong...");
                    Toast.makeText(getContext(), "User already exist...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        Name.setText("");
        UserName.setText("");
        UserPassword.setText("");
    }


}
