package com.pret.lalala.pret;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class FragmentProfile extends Fragment {

    MaterialButton keluarButton;
    String currentUserEmail;
    TextView uname;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_profile, null);

        // Get a reference to your user
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReferenceFromUrl("https://pret-app-35b58.firebaseio.com/users");

//        ref.child()

        currentUserEmail = getActivity().getSharedPreferences("PREFERENCE_CURRENT_USER",
                getActivity().MODE_PRIVATE).getString("currentUser", "lala");

        uname = rootView.findViewById(R.id.tvUname);

        uname.setText(currentUserEmail);

        keluarButton = rootView.findViewById(R.id.keluar_button);
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
