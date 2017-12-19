package niot.imoon;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class buoy_info extends AppCompatActivity {
    DatabaseReference databaseReference,databaseReference1,databaseReference2;
    //public static final String Firebase_Server_URL = "https://imoonnew-7e6e5.firebaseio.com/AD06/0";
    ProgressDialog progressDialog;
String val,parm,parent;
    List<buoy_parameters> list = new ArrayList<>();
    buoy_parameters buoyParameters;
    RecyclerView recyclerView;
    Firebase firebase;
    RecyclerView.Adapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buoy_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String buoys = getIntent().getStringExtra("buoys");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        //Firebase.setAndroidContext(buoy_info.this);
        //firebase=new Firebase(Firebase_Server_URL);
        recyclerView.setLayoutManager(new LinearLayoutManager(buoy_info.this));

        progressDialog = new ProgressDialog(buoy_info.this);

        progressDialog.setMessage("Loading Data from Firebase Database");

        progressDialog.show();
        databaseReference=FirebaseDatabase.getInstance().getReference();
        databaseReference1 = databaseReference.child(buoys).child("0");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot snapshot) {
                databaseReference2= databaseReference.child("  Parameter             Buoy ID").child("0");
                databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot1) {
                    for(DataSnapshot dataSnapshot:snapshot.getChildren()) {
                        val = String.valueOf(dataSnapshot.getValue());
                        parent=String.valueOf(dataSnapshot.getKey());
                        parm=String.valueOf(dataSnapshot1.child(parent).getValue());
                        buoyParameters = new buoy_parameters(parm,val);
                        list.add(buoyParameters);
                    }
                    adapter = new RecyclerViewAdapter(buoy_info.this, list);

                    recyclerView.setAdapter(adapter);

                    progressDialog.dismiss();
                    }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(buoy_info.this, "error", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();

                }
            });
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

}
