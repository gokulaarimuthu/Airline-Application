package com.example.tabbed.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tabbed.DBHelper;
import com.example.tabbed.R;
import com.example.tabbed.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    //private Activity context;
private FragmentHomeBinding binding;
private EditText name,email;
private Button submitsub;
private Activity context;
private DBHelper DB;
private String nameTXT,emailTXT;
private ImageView insta;
private ImageButton youtube;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       // context = getActivity();
   // binding = FragmentHomeBinding.inflate(inflater, container, false);
  //  View root = binding.getRoot();
        context = getActivity();
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_home,container,false);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        name = context.findViewById(R.id.editTextTextPersonName);
        email = context.findViewById(R.id.editTextTextEmailAddress);
        insta = context.findViewById(R.id.insta1);
        youtube = context.findViewById(R.id.utube1);
        DB = new DBHelper(context);

        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotourl("https://www.instagram.com/ej._65");
            }
        });

        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotourl("https://m.youtube.com/channel/UCL7uuhz9g9TzxD9htm8HMzg/featured");
            }
        });

        submitsub = context.findViewById(R.id.button8);
        submitsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 nameTXT = name.getText().toString();
                 emailTXT = email.getText().toString();

                if(nameTXT.equals(""))
                {
                    Toast.makeText(context, "Name is required", Toast.LENGTH_SHORT).show();
                }
                else if(emailTXT.equals(""))
                {
                    Toast.makeText(context, "Mail is required", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean checkinsertdata = DB.insertsubscribedata(nameTXT,emailTXT);
                    if (checkinsertdata == true) {
                        Toast.makeText(getActivity(), "Subscription Successful", Toast.LENGTH_SHORT).show();
                        // Snackbar.make(view, "Registered Successfully", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        name.setText("");
                        email.setText("");

                    } else {
                        Toast.makeText(getActivity(), "Subscription Failed", Toast.LENGTH_SHORT).show();
                        //   Snackbar.make(view, "Registration Failed", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }

                }
            }
        });
    }

    private void gotourl(String url)
    {
        Uri uri = Uri.parse(url);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}

