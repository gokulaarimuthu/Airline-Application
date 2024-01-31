package com.example.tabbed;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tabbed.ui.main.Fragment1;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class Frag3 extends Fragment1 {
    private Activity context;
    private FloatingActionButton flo;
    private TextView skip;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.frag3,container,false);
        return root;
    }
    @Override
    public void onStart() {
        super.onStart();

        flo = context.findViewById(R.id.flo);
        flo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii = new Intent(getActivity(),MainActivity.class);
                startActivity(ii);
            }
        });


        skip =context.findViewById(R.id.textViewsf3);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii = new Intent(getActivity(),MainActivity.class);
                startActivity(ii);
            }
        });
    }
}