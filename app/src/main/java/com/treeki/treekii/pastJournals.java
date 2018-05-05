package com.treeki.treekii;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
//import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.treeki.treekii.MainActivity;

import java.util.Calendar;
public class pastJournals extends AppCompatActivity {

    private TextView pastJournal;
    //private TextView pastJournalContent;
    private DatabaseReference mDatabase;
    private static final String TAG = "Past_Journals_Activity";

    public class Journal {
        public String date;
        public String content;

        public Journal() {

        }

        public Journal (String date, String content) {
            this.date = date;
            this.content = content;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_journals);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        pastJournal = findViewById(R.id.textView);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Journals").child("01").child("01").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //get question at /Questions/0101
                        String journal = dataSnapshot.getValue(String.class);

                        //error handling
                        if (journal == null) {
                            Toast.makeText(getApplicationContext(),"No Journal",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            //display journal
                        }
                        //if no err, change the question

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
        );



        ValueEventListener postJournal = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Journal journal = dataSnapshot.getValue(Journal.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
    }
}
