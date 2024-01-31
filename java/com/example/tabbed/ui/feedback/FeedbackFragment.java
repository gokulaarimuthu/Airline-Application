package com.example.tabbed.ui.feedback;

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
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.tabbed.DBHelper;
import com.example.tabbed.R;
import com.example.tabbed.databinding.FragmentFeedbackBinding;
import com.example.tabbed.databinding.FragmentVideoBinding;

public class FeedbackFragment extends Fragment {

    private FragmentFeedbackBinding binding;
    private EditText name,email,feedback,name1,email1;
    private Button submitfeed,submitsub;
    private Activity context;
    private DBHelper DB;
    private String nameTXT,emailTXT,feedbackTXT,name1TXT,email1TXT,ratingTXT;
    private RatingBar ratingBar;
    private Float ratingvalue;
    private ImageView insta;
    private ImageButton youtube;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFeedbackBinding.inflate(inflater, container, false);
        context = getActivity();
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        name = context.findViewById(R.id.editTextTextPersonNamefff);
        email = context.findViewById(R.id.editTextTextEmailAddressfff);
        feedback = context.findViewById(R.id.editTextTextMultiLinef);
        ratingBar=context.findViewById(R.id.ratingBar);
        insta = context.findViewById(R.id.insta1ff);
        youtube = context.findViewById(R.id.utube1ff);

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


        name1 = context.findViewById(R.id.editTextTextPersonNameff);
        email1 = context.findViewById(R.id.editTextTextEmailAddressff);
        DB = new DBHelper(context);

        submitfeed = context.findViewById(R.id.button8fff);
        submitfeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameTXT = name.getText().toString();
                emailTXT = email.getText().toString();
                feedbackTXT = feedback.getText().toString();

                ratingvalue = ratingBar.getRating();
                ratingTXT = ratingvalue.toString();

                if(ratingTXT.equals("0.0")){
                    Toast.makeText(context, "RatingBar is required", Toast.LENGTH_SHORT).show();

                }
                else if(nameTXT.equals(""))
                {
                    Toast.makeText(context, "Name is required", Toast.LENGTH_SHORT).show();
                }
                else if(emailTXT.equals(""))
                {
                    Toast.makeText(context, "Mail is required", Toast.LENGTH_SHORT).show();
                }
                else if(feedbackTXT.equals(""))
                {
                    Toast.makeText(context, "Feedback is required", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean checkinsertdata1 = DB.insertfeedbackdata(nameTXT,emailTXT,feedbackTXT,ratingTXT);
                    if (checkinsertdata1 == true) {
                        Toast.makeText(getActivity(), "Feedback Sended", Toast.LENGTH_SHORT).show();
                        // Snackbar.make(view, "Registered Successfully", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        name.setText("");
                        email.setText("");
                        feedback.setText("");
                        ratingBar.setRating(0);

                    } else {
                        Toast.makeText(getActivity(), "Sending Failed", Toast.LENGTH_SHORT).show();
                        //   Snackbar.make(view, "Registration Failed", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }
                }
            }
        });

        submitsub = context.findViewById(R.id.button8ff);
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