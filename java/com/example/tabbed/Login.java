package com.example.tabbed;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.tabbed.DBHelper;
import com.example.tabbed.Forgotpass;
import com.example.tabbed.Home;
import com.example.tabbed.Navigation_drawer;
import com.example.tabbed.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.Executor;

public class Login extends Fragment {
    private Activity context;
    private Button login;
    private TextView fnamel,lnamel,maill,passl,fpassclick;
    private DBHelper DB;
    private String fnamelTXT,lnamelTXT,passlTXT,maillTXT;
    private String name,email;
    private Uri image;
    GoogleSignInClient mGoogleSignInClient;
    private static int RC_SIGN_IN = 100;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login,container,false);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getActivity());
        SignInButton signInButton = root.findViewById(R.id.sign_in_button1);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    signIn();
            }
        });

        return root;
    }
    private void signIn() {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.w("YES","signInResult:"+account.getEmail());
            }
            catch (ApiException e)
            {
                Log.w( "TAG","signInResult:failed code=" + e.getStatusCode());
            }
            handleSignInResult(task);
        }
    }
    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(context, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(context, "New User Please Register", Toast.LENGTH_SHORT).show();
                        //Toast.makeText(Navigation_drawer.this,"SignOut", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
            try {
                GoogleSignInAccount account = completedTask.getResult(ApiException.class);
                GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount((getActivity()));

                if (acct != null) {
                    //String personName = acct.getDisplayName();
                    name = acct.getDisplayName();
                    String personGivenName = acct.getGivenName();
                    String personFamilyName = acct.getFamilyName();
                    //  String personEmail = acct.getEmail();
                    email = acct.getEmail();
                    String personId = acct.getId();
                    // Uri personPhoto = acct.getPhotoUrl();
                    image = acct.getPhotoUrl();
                    //maillTXT = email.getText().toString();
                }
                Cursor res = DB.check1(email);
                if (res.getCount() == 0) {
                   //Toast.makeText(context, "New User Please Register", Toast.LENGTH_SHORT).show();
                    signOut();
                   // Snackbar.make(getActivity(), "New User Please Register", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    return;
                } else {
                    Toast.makeText(context, "Sign In Successful", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getActivity(), Navigation_drawer.class);

                    i.putExtra("keyname", name);
                    i.putExtra("keymail", email);
                    i.putExtra("keyimage", image);
                    startActivity(i);
                    //startActivity(new Intent(MainActivity.this,MainActivity2.class));

                }
            }
            catch (ApiException e) {
                Log.d("message", e.toString());
            }
    }


    @Override
    public void onStart() {
        super.onStart();
        fnamel=context.findViewById(R.id.fnamel);
        lnamel=context.findViewById(R.id.lnamel);
        maill=context.findViewById(R.id.maill);
        passl=context.findViewById(R.id.passl);
        login=context.findViewById(R.id.login);
        fpassclick=context.findViewById(R.id.fpassclick);
        DB = new DBHelper(context);

        fpassclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii = new Intent(getActivity(), Forgotpass.class);
                startActivity(ii);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fnamelTXT = fnamel.getText().toString();
                lnamelTXT = lnamel.getText().toString();
                maillTXT = maill.getText().toString();
                passlTXT = passl.getText().toString();

                if(fnamelTXT.equals(""))
                {
                    Toast.makeText(context, "First Name is required", Toast.LENGTH_SHORT).show();
                    //   Snackbar.make(view, "Name is required", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
                else if(lnamelTXT.equals(""))
                {
                    Toast.makeText(context, "Last Name is required", Toast.LENGTH_SHORT).show();
                    //   Snackbar.make(view, "Password is required", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
                else if(maillTXT.equals(""))
                {
                    Toast.makeText(context, "Mail is required", Toast.LENGTH_SHORT).show();
                    //   Snackbar.make(view, "Password is required", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
                else if(passlTXT.equals(""))
                {
                    Toast.makeText(context, "Password is required", Toast.LENGTH_SHORT).show();
                    //   Snackbar.make(view, "Password is required", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
                else {
                    Cursor res = DB.check(maillTXT, passlTXT);
                    if (res.getCount() == 0) {
                        Toast.makeText(context, "New User Please Register", Toast.LENGTH_SHORT).show();
                        // Snackbar.make(view, "New User Please Register", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        return;
                    }
                    else {
                        home();

                    }
                }
            }
        });
    }
    public void home(){
        String lname = fnamelTXT+lnamelTXT;
        Toast.makeText(context, "Sign In Successful", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getActivity(), Navigation_drawer.class);

        i.putExtra("keyname",lname);
        i.putExtra("keymail",maillTXT);
       // i.putExtra("keyimage",image);
        startActivity(i);
        getActivity().finish();
    }

}