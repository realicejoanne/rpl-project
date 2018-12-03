package com.pret.lalala.pret;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FragmentProfile extends Fragment {

    MaterialButton keluarButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_profile, null);
        //keluarButton = rootView.findViewById(R.id.keluar_button);
        keluarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSharedPreferences("PREFERENCE_LOGGEDIN",
                        getActivity().MODE_PRIVATE).edit().putBoolean("isLoggedIn", false).apply();
                Intent intent = new Intent(getActivity(), WelcomeActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return rootView;
    }
}
