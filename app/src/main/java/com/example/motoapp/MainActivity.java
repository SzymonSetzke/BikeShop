package com.example.motoapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.motoapp.bottom_nav.CategoryFragment;
import com.example.motoapp.bottom_nav.HomeFragment;
import com.example.motoapp.bottom_nav.ProfileFragment;
import com.example.motoapp.category_nav.BmxFragment;
import com.example.motoapp.category_nav.CityFragment;
import com.example.motoapp.category_nav.CrossFragment;
import com.example.motoapp.category_nav.ChildFragment;
import com.example.motoapp.category_nav.CyclingFragment;
import com.example.motoapp.category_nav.ElectricFragment;
import com.example.motoapp.category_nav.MtbFragment;
import com.example.motoapp.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    ActivityMainBinding binding;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item ->{


            int itemId = item.getItemId();

            if (itemId == R.id.home) {
                replaceFragment(new HomeFragment());
            } else if (itemId == R.id.category) {
                replaceFragment(new CategoryFragment());
            } else if (itemId == R.id.profile) {
                replaceFragment(new ProfileFragment());
            }

            return true;
        });

        Toolbar toolbar = findViewById(R.id.toolbar); //Ignore red line errors
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_bmx);
        }
    }

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.nav_bmx) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BmxFragment()).commit();
        } else if (itemId == R.id.nav_child) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ChildFragment()).commit();
        } else if (itemId == R.id.nav_city) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CityFragment()).commit();
        } else if (itemId == R.id.nav_cross) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CrossFragment()).commit();
        } else if (itemId == R.id.nav_cycling) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CyclingFragment()).commit();
        } else if (itemId == R.id.nav_electric) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ElectricFragment()).commit();
        } else if (itemId == R.id.nav_mtb) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MtbFragment()).commit();
        } else if (itemId == R.id.nav_logout) {
            Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}