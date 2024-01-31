package com.example.tabbed;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tabbed.databinding.ActivityNavigationDrawerBinding;

public class Navigation_drawer extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
private ActivityNavigationDrawerBinding binding;

    private TextView username,usermail;
    private View v;
    private ImageView pic;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
      //   super.onBackPressed();

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case  R.id.signout:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("");
                builder.setMessage("Are you sure want to exit?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        signOut();

                        Toast.makeText(Navigation_drawer.this, "Sign Out Successful", Toast.LENGTH_SHORT).show();
                        Intent ii = new Intent(Navigation_drawer.this, MainActivity.class);
                        startActivity(ii);
                        finish();
                    }
                });


                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        //Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     binding = ActivityNavigationDrawerBinding.inflate(getLayoutInflater());
     setContentView(binding.getRoot());

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        setSupportActionBar(binding.appBarNavigationDrawer.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_contact, R.id.nav_video,R.id.nav_booking,R.id.nav_feedback,R.id.nav_userprofile)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation_drawer);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        NavigationView nav = findViewById(R.id.nav_view) ;
        v = nav.getHeaderView(0);
        username = v.findViewById(R.id.username);
        usermail = v.findViewById(R.id.usermail);
        pic= v.findViewById(R.id.pic);

        String usernamee1 = getIntent().getStringExtra("keyname");
        String usermaill1 = getIntent().getStringExtra("keymail");
        Uri image = getIntent().getParcelableExtra("keyimage");

       username.setText(usernamee1);
       usermail.setText(usermaill1);
       Glide.with(this).load(String.valueOf(image)).into(pic);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation_drawer);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //Toast.makeText(context, "New User Please Register", Toast.LENGTH_SHORT).show();
                        //Toast.makeText(Navigation_drawer.this,"SignOut", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}