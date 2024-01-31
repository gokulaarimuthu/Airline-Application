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
import androidx.fragment.app.FragmentTransaction;

import com.example.tabbed.ui.main.Fragment1;


public class Frag1 extends Fragment1 {
    private Activity context;
    private TextView skip;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.frag1,container,false);

        return root;
    }
    @Override
    public void onStart() {
        super.onStart();

        skip =context.findViewById(R.id.textViewsf1);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),MainActivity.class);
                startActivity(i);
            }
        });

    }


}
