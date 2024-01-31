package com.example.tabbed.ui.booking;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.tabbed.DBHelper;
import com.example.tabbed.R;
import com.example.tabbed.databinding.FragmentBookingBinding;
import com.example.tabbed.databinding.FragmentVideoBinding;

import java.util.Calendar;

public class BookingFragment extends Fragment {

    private FragmentBookingBinding binding;
    private Activity context;
    private TextView fname,lname,email,contact,departure,arrival,departured,arrivald,noofpass,from,to;
    private Button departuredate,arrivaldate,book,search;
    private String fnameTXT,lnameTXT,mailTXT,contactTXT,departTXT,arrivalTXT,departdTXT,arrivaldTXT,noofpassTXT,spinnertripTXT,
    spinnerpassTXT,spinnerpayTXT,spinnerclassTXT,fromTXT,toTXT;
    private DBHelper DB;
    private int d,m,y;
    private Spinner passengers,payment,economy,trip;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentBookingBinding.inflate(inflater, container, false);
        context = getActivity();
        View root = binding.getRoot();
        return root;
    }
    @Override
    public void onStart() {
        super.onStart();
        DB = new DBHelper(context);
        fname = context.findViewById(R.id.fnameb);
        lname = context.findViewById(R.id.lnameb);
        email = context.findViewById(R.id.mailb);
        contact = context.findViewById(R.id.contactb);
        departure = context.findViewById(R.id.departure);
        arrival = context.findViewById(R.id.arrival);
        departured = context.findViewById(R.id.departureb);
        arrivald = context.findViewById(R.id.arrivalb);
        noofpass = context.findViewById(R.id.noofpassenger);
        book = context.findViewById(R.id.bookticket);
        search=context.findViewById(R.id.search);
        from=context.findViewById(R.id.from);
        to=context.findViewById(R.id.to);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fromTXT = from.getText().toString();
                toTXT = to.getText().toString();
                if (fromTXT.equals("")) {
                    Toast.makeText(context, "From is required to Search Flight", Toast.LENGTH_SHORT).show();
                    //   Snackbar.make(view, "Name is required", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                } else if (toTXT.equals("")) {
                    Toast.makeText(context, "To is required to Search Flight", Toast.LENGTH_SHORT).show();
                    //   Snackbar.make(view, "Name is required", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                } else {
                    gotourl("https://aviation-edge.com/flight-schedule-and-timetable-of-airlines-and-airports/?gclid=CjwKCAiAo4OQBhBBEiwA5KWu_7ZJv7pDBh7U_ZH7mHLHJ5QAvGyxuF5DSYdgzpPpcXEn_nBBn2euuBoCCbIQAvD_BwE");
                }
            }
        });


        passengers=context.findViewById(R.id.passenger);
        economy=context.findViewById(R.id.economy);
        payment=context.findViewById(R.id.payment);
        trip = context.findViewById(R.id.triptype);

        String[] passenger={"Choose Passengers Type","Adult Only","Adult and Children","Adult, Children and Infants"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(context,R.layout.support_simple_spinner_dropdown_item,passenger);
        passengers.setAdapter(arrayAdapter);
        passengers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        String[] cabinclass={"Choose Cabin Class","Economy","Premium Economy","Business Class","First Class"};
        ArrayAdapter arrayAdapter1 = new ArrayAdapter(context,R.layout.support_simple_spinner_dropdown_item,cabinclass);
        economy.setAdapter(arrayAdapter1);
        economy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        String[] paymenttype={"Choose Payment Method","Master Credit","Master Debit","Visa Credit","Visa Debit","American Express","Alipay","UPI"};
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(context,R.layout.support_simple_spinner_dropdown_item,paymenttype);
        payment.setAdapter(arrayAdapter2);
        payment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        String[] triptype ={"Choose Trip Type","One Way","Round Trip"};
        ArrayAdapter arrayAdapter3 = new ArrayAdapter(context,R.layout.support_simple_spinner_dropdown_item,triptype);
        trip.setAdapter(arrayAdapter3);
        trip.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        departuredate = context.findViewById(R.id.selectd);
        final Calendar c = Calendar.getInstance();
        departuredate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                y = c.get(Calendar.YEAR);
                m = c.get(Calendar.MONTH);
                d = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog g = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        int i3 = i1 + 1;
                        departured.setText(i2 + "/" + i3 + "/" + i);
                    }
                }, y, m, d);
                g.show();
            }
        });

        arrivaldate = context.findViewById(R.id.selecta);
        final Calendar cc = Calendar.getInstance();
        arrivaldate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                y = cc.get(Calendar.YEAR);
                m = cc.get(Calendar.MONTH);
                d = cc.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog g = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        int i3 = i1 + 1;
                        arrivald.setText(i2 + "/" + i3 + "/" + i);
                    }
                }, y, m, d);
                g.show();
            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fnameTXT = fname.getText().toString();
                lnameTXT = lname.getText().toString();
                mailTXT = email.getText().toString();
                contactTXT = contact.getText().toString();
                departTXT = departure.getText().toString();
                arrivalTXT= arrival.getText().toString();
                departdTXT = departured.getText().toString();
                arrivaldTXT = arrivald.getText().toString();
                noofpassTXT = noofpass.getText().toString();
                spinnertripTXT= trip.getSelectedItem().toString();
                spinnerpassTXT= passengers.getSelectedItem().toString();
                spinnerclassTXT= economy.getSelectedItem().toString();
                spinnerpayTXT= payment.getSelectedItem().toString();

               if (fnameTXT.equals("")) {
                    Toast.makeText(context, "First Name is required", Toast.LENGTH_SHORT).show();
                    //   Snackbar.make(view, "Name is required", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
               else if (lnameTXT.equals("")) {
                    Toast.makeText(context, "Last Name is required", Toast.LENGTH_SHORT).show();
                    //   Snackbar.make(view, "Name is required", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                } else if (mailTXT.equals("")) {
                    Toast.makeText(context, "Mail is required", Toast.LENGTH_SHORT).show();
                    //   Snackbar.make(view, "Name is required", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }else if (contactTXT.equals("")) {
                    Toast.makeText(context, "Mobile number is required", Toast.LENGTH_SHORT).show();
                    //   Snackbar.make(view, "Name is required", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                } else if (departTXT.equals("")) {
                    Toast.makeText(context, "Departure Location is required", Toast.LENGTH_SHORT).show();
                    //   Snackbar.make(view, "Name is required", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                } else if (arrivalTXT.equals("")) {
                    Toast.makeText(context, "Arrival Loaction is required", Toast.LENGTH_SHORT).show();
                    //   Snackbar.make(view, "Name is required", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
                else if (departdTXT.equals("")) {
                    Toast.makeText(context, "Departure date is required", Toast.LENGTH_SHORT).show();
                    //   Snackbar.make(view, "Name is required", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                } else if (arrivaldTXT.equals("")) {
                    Toast.makeText(context, "Arrival date is required", Toast.LENGTH_SHORT).show();
                    //   Snackbar.make(view, "Name is required", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                } else if (noofpassTXT.equals("")) {
                    Toast.makeText(context, "Passenger count is required", Toast.LENGTH_SHORT).show();
                    //   Snackbar.make(view, "Name is required", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
               else if (spinnertripTXT.equals("Choose Trip Type")) {
                   Toast.makeText(context, "Please Select Trip Type", Toast.LENGTH_SHORT).show();
                   //   Snackbar.make(view, "Name is required", Snackbar.LENGTH_LONG).setAction("Action", null).show();
               }
               else if (spinnerpassTXT.equals("Choose Passengers Type")) {
                   Toast.makeText(context, "Please Select Passenger Type", Toast.LENGTH_SHORT).show();
                   //   Snackbar.make(view, "Name is required", Snackbar.LENGTH_LONG).setAction("Action", null).show();
               }
               else if (spinnerclassTXT.equals("Choose Cabin Class")) {
                   Toast.makeText(context, "Please Select Cabin Class", Toast.LENGTH_SHORT).show();
                   //   Snackbar.make(view, "Name is required", Snackbar.LENGTH_LONG).setAction("Action", null).show();
               }
               else if (spinnerpayTXT.equals("Choose Payment Method")) {
                   Toast.makeText(context, "Please Select Payment Method", Toast.LENGTH_SHORT).show();
                   //   Snackbar.make(view, "Name is required", Snackbar.LENGTH_LONG).setAction("Action", null).show();
               }
                else {
                    Boolean checkinsertdata = DB.insertbookingdata(fnameTXT, lnameTXT, mailTXT,contactTXT,departTXT,arrivalTXT,departdTXT,arrivaldTXT,noofpassTXT,spinnertripTXT,spinnerpassTXT,spinnerclassTXT,spinnerpayTXT);

                    if (checkinsertdata == true) {
                        Toast.makeText(getActivity(), "Ticket Booked Successfully", Toast.LENGTH_SHORT).show();
                        // Snackbar.make(view, "Registered Successfully", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        fname.setText("");
                        lname.setText("");
                        email.setText("");
                        contact.setText("");
                        departure.setText("");
                        arrival.setText("");
                        departured.setText("");
                        arrivald.setText("");
                        noofpass.setText("");
                        trip.setSelection(0);
                        passengers.setSelection(0);
                        economy.setSelection(0);
                        payment.setSelection(0);
                    } else {
                        Toast.makeText(getActivity(), "Booking Failed", Toast.LENGTH_SHORT).show();
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