package com.example.tabbed;

import android.os.Bundle;

import com.example.tabbed.ui.main.SectionsPagerAdapter2;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.tabbed.ui.main.SectionsPagerAdapter;
import com.example.tabbed.databinding.ActivityWalkBinding;

public class Walk extends AppCompatActivity {

    private ActivityWalkBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityWalkBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter2 sectionsPagerAdapter2 = new SectionsPagerAdapter2(this, getSupportFragmentManager());
        ViewPager viewPager1 = binding.viewPager;
        viewPager1.setAdapter(sectionsPagerAdapter2);

    }
    @Override
    public void onBackPressed() {
        // finish();
        moveTaskToBack(true);
        // super.onBackPressed();
    }

}