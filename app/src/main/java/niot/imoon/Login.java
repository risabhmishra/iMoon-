package niot.imoon;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dd.processbutton.iml.ActionProcessButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
EditText email,pass;
Button signup;
private ActionProcessButton signin;
    private FirebaseAuth mAuth;
    final String TAG = "NIOT";
    private FirebaseAuth.AuthStateListener mAuthstatelistener;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        mAuthstatelistener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                 user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    startActivity(new Intent(Login.this, MainActivity.class));
                }
            }
        };

        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);

        signup = (Button)findViewById(R.id.signup_login);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Register.class));
            }
        });

        signin = (ActionProcessButton) findViewById(R.id.signin_but);
        signin.setMode(ActionProcessButton.Mode.ENDLESS);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String emails = email.getText().toString();
                final String passs = pass.getText().toString();
                final boolean emailVerified = user.isEmailVerified();
                if (TextUtils.isEmpty(emails) || TextUtils.isEmpty(passs)) {
                    Toast.makeText(Login.this, "Fields are Empty", Toast.LENGTH_SHORT).show();
                } else {
                    signin.setProgress(1);
                    mAuth.signInWithEmailAndPassword(emails, passs)
                            .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful() && emailVerified ) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "signInWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        signin.setProgress(100);
                                        startActivity(new Intent(Login.this,MainActivity.class));
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                                        signin.setProgress(-1);
                                        Toast.makeText(Login.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                    }

                                    // ...
                                }
                            });
                }
            }
        });

    }


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthstatelistener);
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthstatelistener != null) {
            mAuth.removeAuthStateListener(mAuthstatelistener);
        }
    }
}
