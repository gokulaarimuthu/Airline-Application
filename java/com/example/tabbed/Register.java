package com.example.tabbed;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tabbed.DBHelper;
import com.example.tabbed.R;

import java.util.Calendar;

public class Register extends Fragment {
    private Button insert,sd;
    private TextView fname,lname,pass,dob,contact,mail;
    private Activity context;
    private String fnameTXT,lnameTXT,passTXT,dobTXT,contactTXT,mailTXT;
    private String radioTXT="";
    private CheckBox agree;
    private RadioButton male,female,other;
    private DBHelper DB;
    private int d,m,y;
    private RadioGroup gender;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.register,container,false);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        insert = context.findViewById(R.id.register);
        fname = context.findViewById(R.id.fname);
        lname = context.findViewById(R.id.lname);
        pass = context.findViewById(R.id.pass);
        contact = context.findViewById(R.id.contact);
        dob = context.findViewById(R.id.dob);
        mail = context.findViewById(R.id.mail);
        agree = context.findViewById(R.id.agree);
        male = context.findViewById(R.id.m);
        female = context.findViewById(R.id.f);
        other = context.findViewById(R.id.o);
        sd = context.findViewById(R.id.sd);
        DB = new DBHelper(context);
        gender=context.findViewById(R.id.gender);

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
                } else if (agree.isChecked() == false) {
                    Toast.makeText(context, "Privacy Policy is required", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkinsertdata = DB.insertuserdata(fnameTXT, lnameTXT, mailTXT, passTXT, contactTXT, dobTXT, radioTXT);

                    if (checkinsertdata == true) {
                        Toast.makeText(getActivity(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                     // Snackbar.make(view, "Registered Successfully", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        fname.setText("");
                        lname.setText("");
                        mail.setText("");
                        pass.setText("");
                        contact.setText("");
                        dob.setText("");
                        agree.setChecked(false);
                        male.setChecked(false);
                        female.setChecked(false);
                        other.setChecked(false);
                    } else {
                        Toast.makeText(getActivity(), "Registration Failed", Toast.LENGTH_SHORT).show();
                        //   Snackbar.8make(view, "Registration Failed", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }
                }
            }
        });
    }
}

