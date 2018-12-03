package com.pret.lalala.pret;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Boolean isLoggedIn;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;

            switch (item.getItemId()) {
                case R.id.action_home:
                    fragment = new FragmentHome();
                    break;

                case R.id.action_add:
                    if (isLoggedIn) {
                        fragment = new FragmentAdd();
                    } else {
                        Toast.makeText(MainActivity.this,
                                "Anda belum masuk ke Aplikasi", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.action_user:
                    if (isLoggedIn) {
                        fragment = new FragmentProfile();
                    } else {
                        Toast.makeText(MainActivity.this,
                                "Anda belum masuk ke Aplikasi", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }

            return loadFragment(fragment);
        }

    };

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isLoggedIn = getSharedPreferences("PREFERENCE_LOGGEDIN", MODE_PRIVATE)
                .getBoolean("isLoggedIn", false);

        loadFragment(new FragmentHome());

//        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
