package com.example.tabbed.ui.contact;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.tabbed.DBHelper;
import com.example.tabbed.R;
import com.example.tabbed.databinding.FragmentContactBinding;

public class ContactFragment extends Fragment {
    private FragmentContactBinding binding;

    private EditText name,email,message,name1,email1;
    private Button submitcontact,submitsub;
    private Activity context;
    private DBHelper DB;
    private String nameTXT,emailTXT,messageTXT,name1TXT,email1TXT;
    private ImageView insta;
    private ImageButton youtube;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

    binding = FragmentContactBinding.inflate(inflater, container, false);
    context = getActivity();
    View root = binding.getRoot();
    return root;

    }

    @Override
    public void onStart() {
        super.onStart();
        name = context.findViewById(R.id.editTextTextPersonNamefcc);
        email = context.findViewById(R.id.editTextTextEmailAddressfcc);
        message = context.findViewById(R.id.editTextTextMultiLine);
        insta = context.findViewById(R.id.insta1fc);
        youtube = context.findViewById(R.id.utube1fc);

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

        name1 = context.findViewById(R.id.editTextTextPersonNamefc);
        email1 = context.findViewById(R.id.editTextTextEmailAddressfc);
        DB = new DBHelper(context);

        submitcontact = context.findViewById(R.id.button8fcc);
        submitcontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameTXT = name.getText().toString();
                emailTXT = email.getText().toString();
                messageTXT = message.getText().toString();

                if(nameTXT.equals(""))
                {
                    Toast.makeText(context, "Name is required", Toast.LENGTH_SHORT).show();
                }
                else if(emailTXT.equals(""))
                {
                    Toast.makeText(context, "Mail is required", Toast.LENGTH_SHORT).show();
                }
                else if(messageTXT.equals(""))
                {
                    Toast.makeText(context, "Message is required", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean checkinsertdata1 = DB.insertcontactdata(nameTXT,emailTXT,messageTXT);
                    if (checkinsertdata1 == true) {
                        Toast.makeText(getActivity(), "Message Sended", Toast.LENGTH_SHORT).show();
                        // Snackbar.make(view, "Registered Successfully", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        name.setText("");
                        email.setText("");
                        message.setText("");

                    } else {
                        Toast.makeText(getActivity(), "Sending Failed", Toast.LENGTH_SHORT).show();
                        //   Snackbar.make(view, "Registration Failed", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }

                }
            }
        });

        submitsub = context.findViewById(R.id.button8fc);
        submitsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name1TXT = name1.getText().toString();
                email1TXT = email1.getText().toString();

                if(name1TXT.equals(""))
                {
                    Toast.makeText(context, "Name is required", Toast.LENGTH_SHORT).show();
                }
                else if(email1TXT.equals(""))
                {
                    Toast.makeText(context, "Mail is required", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean checkinsertdata = DB.insertsubscribedata(name1TXT,email1TXT);
                    if (checkinsertdata == true) {
                        Toast.makeText(getActivity(), "Subscription Successful", Toast.LENGTH_SHORT).show();
                        // Snackbar.make(view, "Registered Successfully", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        name1.setText("");
                        email1.setText("");

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