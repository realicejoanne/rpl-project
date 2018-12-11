package com.pret.lalala.pret;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pret.lalala.pret.Model.User;


public class FragmentProfile extends Fragment {

    MaterialButton keluarButton, barangkuButton, pinjamankuButton;
    String currentUserEmail;
    TextView uname;
    TextView email;
    ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_profile, null);

        // Get a reference to your user
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReferenceFromUrl("https://pret-app-35b58.firebaseio.com/users");

        currentUserEmail = getActivity().getSharedPreferences("PREFERENCE_CURRENT_USER",
                getActivity().MODE_PRIVATE).getString("currentUser", "lala");

        uname = rootView.findViewById(R.id.tvName);
        email = rootView.findViewById(R.id.tvUname);

        email.setText(currentUserEmail);

        final String currentUserId = getActivity().
                getSharedPreferences("PREFERENCE_CURRENT_USER_ID", getActivity().MODE_PRIVATE)
                .getString("currentUserId", "lala");

        ref.child(currentUserId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                uname.setText(user.getUname());
                Log.d("tesuto", user.getUname());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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

        barangkuButton = rootView.findViewById(R.id.barangku_button);
        barangkuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BarangkuActivity.class);
                startActivity(intent);
            }
        });

        pinjamankuButton = rootView.findViewById(R.id.pinjamanku_button);
        pinjamankuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PinjamankuActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
