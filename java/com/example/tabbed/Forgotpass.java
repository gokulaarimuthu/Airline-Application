package com.example.tabbed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Forgotpass extends AppCompatActivity {

    private EditText fnamep,lnamep,mailp,passp,confpassp;
    private TextView relogin;
    private Button update;
    DBHelper DB;

    @Override
    public void onBackPressed() {
       //finishAffinity();
       // super.onBackPressed();
       moveTaskToBack(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpass);

        fnamep = findViewById(R.id.fnamep);
        lnamep = findViewById(R.id.lnamep);
        mailp = findViewById(R.id.mailp);
        passp = findViewById(R.id.passp);
        confpassp = findViewById(R.id.confpassp);
        update = findViewById(R.id.update);
        relogin = findViewById(R.id.relogin);
        DB = new DBHelper(this);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fnamepTXT = fnamep.getText().toString();
                String lnamepTXT = lnamep.getText().toString();
                String mailpTXT = mailp.getText().toString();
                String passpTXT = passp.getText().toString();
                String confpasspTXT = confpassp.getText().toString();

                if(fnamepTXT.equals(""))
                {
                    Toast.makeText(Forgotpass.this, "First Name is required", Toast.LENGTH_SHORT).show();
                }
                else if(lnamepTXT.equals(""))
                {
                    Toast.makeText(Forgotpass.this, "Last Name is required", Toast.LENGTH_SHORT).show();
                }
                else if(mailpTXT.equals(""))
                {
                    Toast.makeText(Forgotpass.this, "Mail is required", Toast.LENGTH_SHORT).show();
                }
                else if(passpTXT.equals(""))
                {
                    Toast.makeText(Forgotpass.this, "Password is required", Toast.LENGTH_SHORT).show();
                }
                else if(confpasspTXT.equals(""))
                {
                    Toast.makeText(Forgotpass.this, "Confirm Password is required", Toast.LENGTH_SHORT).show();
                }
                else if(!passpTXT.equals(confpasspTXT))
                {
                    Toast.makeText(Forgotpass.this, "Enter Same Password", Toast.LENGTH_SHORT).show();
                }

                else {
                    Boolean checkupdateuserdata = DB.updateuserdata(fnamepTXT,lnamepTXT,mailpTXT,passpTXT);

                    if (checkupdateuserdata== true) {
                        Toast.makeText(Forgotpass.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                        // Snackbar.make(view, "Registered Successfully", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        fnamep.setText("");
                        lnamep.setText("");
                        mailp.setText("");
                        passp.setText("");
                        confpassp.setText("");
                    } else {
                        Toast.makeText(Forgotpass.this, "Update Failed", Toast.LENGTH_SHORT).show();
                        //   Snackbar.make(view, "Registration Failed", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }
                    }
            }
        });
        relogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Forgotpass.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
