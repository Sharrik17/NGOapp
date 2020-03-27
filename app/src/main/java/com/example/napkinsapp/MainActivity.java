package com.example.napkinsapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity {

    static final int googleSign=123;
    FirebaseAuth mAuth;
    Button btnlogin,signIn;
    TextView text;
    String adminMail;
    GoogleSignInClient mGoogleSignInCient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnlogin=findViewById(R.id.login);

        text = findViewById(R.id.text);
        adminMail = "deepfoundation13@gmail.com";

        mAuth= FirebaseAuth.getInstance();

        GoogleSignInOptions googleSignInOptions=new GoogleSignInOptions
                .Builder()
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInCient = GoogleSignIn.getClient(this,googleSignInOptions);
        btnlogin.setOnClickListener(v -> SignInGoogle());

        if (mAuth.getCurrentUser() != null) {
            FirebaseUser user = mAuth.getCurrentUser();
            updateUI(user);
        }
    }


    public void SignInGoogle() {
        Intent signInIntent = mGoogleSignInCient.getSignInIntent();
        startActivityForResult(signInIntent, googleSign);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == googleSign) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null) firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Log.w("TAG", "Google sign in failed", e);
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d("TAG", "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d("TAG", "signInWithCredential:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        Log.w("TAG", "signInWithCredential:failure", task.getException());

                        Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            if(user.getEmail().equals(adminMail))
            {
                Toast.makeText(this, "Logged in as Admin", Toast.LENGTH_SHORT).show();
                Intent it = new Intent(MainActivity.this,adminOrders.class);
                startActivity(it);
            }
            else {
                Toast.makeText(this, "Logged in as " + user.getEmail(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, getDetails.class);
                startActivity(i);
            }
        }
    }

    private void Logout() {
        FirebaseAuth.getInstance().signOut();
        mGoogleSignInCient.signOut().addOnCompleteListener(this,
                task -> updateUI(null));
    }
}
