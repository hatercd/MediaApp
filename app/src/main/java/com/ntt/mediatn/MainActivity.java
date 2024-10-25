package com.ntt.mediatn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.ntt.mediatn.Fragment.Fragment_Activity;
import com.ntt.mediatn.Fragment.Fragment_Home;
import com.ntt.mediatn.Fragment.Fragment_Profile;
import com.ntt.mediatn.Fragment.Fragment_Search;
import com.ntt.mediatn.Fragment.Fragment_Upload;
import com.ntt.mediatn.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    ActivityMainBinding binding;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        MainActivity.this.setTitle("Trang cá nhân");

        binding.toolbar.setVisibility(View.GONE);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        frameLayout = findViewById(R.id.fragment_container);

        loadFragment(new Fragment_Home(), true);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();
                if (itemId == R.id.nav_home){
                    binding.toolbar.setVisibility(View.GONE);
                    loadFragment(new Fragment_Home(), false);

                } else if (itemId == R.id.nav_search){
                    binding.toolbar.setVisibility(View.GONE);
                    loadFragment(new Fragment_Search(), false);

                } else if (itemId == R.id.nav_upload) {
                    binding.toolbar.setVisibility(View.GONE);
                    loadFragment(new Fragment_Upload(), false);

                } else if (itemId == R.id.nav_activity) {
                    binding.toolbar.setVisibility(View.GONE);
                    loadFragment(new Fragment_Activity(), false);

                } else {
                    binding.toolbar.setVisibility(View.VISIBLE);
                    MainActivity.this.setTitle("Trang cá nhân");
                    loadFragment(new Fragment_Profile(), false);
                }
                return true;
            }
        });

    }
   private void loadFragment(Fragment fragment, boolean isAppInit){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (isAppInit){
            fragmentTransaction.add(R.id.fragment_container, fragment);
       }else {
            fragmentTransaction.replace(R.id.fragment_container, fragment);
        }
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.setting:
                auth.signOut();
                Intent intent = new Intent( MainActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}