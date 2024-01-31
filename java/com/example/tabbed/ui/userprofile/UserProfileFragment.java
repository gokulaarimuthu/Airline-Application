package com.example.tabbed.ui.userprofile;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.tabbed.DBHelper;
import com.example.tabbed.R;
import com.example.tabbed.databinding.FragmentUserprofileBinding;

import java.util.Calendar;

public class UserProfileFragment extends Fragment {
    private Button insert,sd;
    private TextView fname,lname,pass,dob,contact,mail;
    private Activity context;
    private String fnameTXT,lnameTXT,passTXT,dobTXT,contactTXT,mailTXT;
    private String radioTXT="";
    private RadioButton male,female,other;
    private DBHelper DB;
    private int d,m,y;
    private RadioGroup gender;
    private ImageView insta;
    private ImageButton youtube;

    private FragmentUserprofileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding =  FragmentUserprofileBinding.inflate(inflater, container, false);
        context = getActivity();
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        insert = context.findViewById(R.id.updateuser);
        fname = context.findViewById(R.id.fnameupdate);
        lname = context.findViewById(R.id.lnameupdate);
        pass = context.findViewById(R.id.passupdate);
        contact = context.findViewById(R.id.contactupdate);
        dob = context.findViewById(R.id.dobupdate);
        mail = context.findViewById(R.id.mailupdate);
        male = context.findViewById(R.id.mupdate);
        female = context.findViewById(R.id.fupdate);
        other = context.findViewById(R.id.oupdate);
        sd = context.findViewById(R.id.sdupdate);
        DB = new DBHelper(context);
        gender=context.findViewById(R.id.genderupdate);



       // String namedata="ezhil";
        String namedata="";
        mailTXT = mail.getText().toString();
        Cursor res = DB.getdataprofile(mailTXT);
        while (res.moveToNext()) {
            namedata = res.getString(0);
        }
        fname.setText(namedata);


        insta = context.findViewById(R.id.instaupdate);
        youtube = context.findViewById(R.id.utubeupdate);

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


        final Calendar c = Calendar.getInstance();
        sd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                y = c.get(Calendar.YEAR);
                m = c.get(Calendar.MONTH);
                d = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog g = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        int i3 = i1 + 1;
                        dob.setText(i2 + "/" + i3 + "/" + i);
                    }
                }, y, m, d);
                g.show();
            }
        });
        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.m)
                {
                    radioTXT="Male";
                }
                if(i == R.id.f)
                {
                    radioTXT="Female";
                }
                if(i == R.id.o)
                {
                    radioTXT="Other";
                }
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fnameTXT = fname.getText().toString();
                lnameTXT = lname.getText().toString();
                passTXT = pass.getText().toString();
                dobTXT = dob.getText().toString();
                contactTXT = contact.getText().toString();
                mailTXT = mail.getText().toString();


                if (fnameTXT.equals("")) {
                    Toast.makeText(context, "First Name is required", Toast.LENGTH_SHORT).show();
                    //   Snackbar.make(view, "Name is required", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                } else if (lnameTXT.equals("")) {
                    Toast.makeText(context, "Last Name is required", Toast.LENGTH_SHORT).show();
                    //   Snackbar.make(view, "Name is required", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                } else if (mailTXT.equals("")) {
                    Toast.makeText(context, "Mail is required", Toast.LENGTH_SHORT).show();
                    //   Snackbar.make(view, "Name is required", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                } else if (passTXT.equals("")) {
                    Toast.makeText(context, "Password is required", Toast.LENGTH_SHORT).show();
                    //   Snackbar.make(view, "Name is required", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                } else if ((male.isChecked() == false) && (female.isChecked() == false) && (other.isChecked() == false)) {
                    Toast.makeText(context, "Gender is required", Toast.LENGTH_SHORT).show();

                } else if (dobTXT.equals("")) {
                    Toast.makeText(context, "DOB is required", Toast.LENGTH_SHORT).show();
                    //   Snackbar.make(view, "Name is required", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                } else if (contactTXT.equals("")) {
                    Toast.makeText(context, "Contact is required", Toast.LENGTH_SHORT).show();
                    //   Snackbar.make(view, "Name is required", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                } else {
                    Boolean checkinsertdata = DB.updateuserprofiledata(fnameTXT, lnameTXT, mailTXT, passTXT, contactTXT, dobTXT, radioTXT);
                    if (checkinsertdata == true) {
                        Toast.makeText(getActivity(), "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
                        // Snackbar.make(view, "Registered Successfully", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                    /*    fname.setText("");
                        lname.setText("");
                        mail.setText("");
                        pass.setText("");
                        contact.setText("");
                        dob.setText("");
                        male.setChecked(false);
                        female.setChecked(false);
                        other.setChecked(false);

                     */
                    } else {
                        Toast.makeText(getActivity(), "Registration Failed (Mail cannot be upated)", Toast.LENGTH_SHORT).show();
                        //   Snackbar.8make(view, "Registration Failed", Snackbar.LENGTH_LONG).setAction("Action", null).show();
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