package com.example.geekalex.shoppingapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Demac extends AppCompatActivity {
    private String mPost_key=null;
    private DatabaseReference mDatabase;

    private ImageView mBlogSingleImage;
    private TextView mBlogSingleTitle;
    private TextView mBlogSingleDesc;



    private Button mSingleRemoveBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demac);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Machinery");


        mDatabase.keepSynced(true);


        mAuth= FirebaseAuth.getInstance();

        mPost_key=getIntent().getExtras().getString("blog_id");

        mBlogSingleDesc=(TextView)findViewById(R.id.singleBlogDesc);
        mBlogSingleImage=(ImageView)findViewById(R.id.singleBlogImage);
        mBlogSingleTitle=(TextView) findViewById(R.id.singleBlogTitle);


        mSingleRemoveBtn=(Button)findViewById(R.id.singleRemoveBtn);

        mDatabase.child(mPost_key).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mDatabase.keepSynced(true);
                String post_title=(String)dataSnapshot.child("commodity").getValue();
                final String post_desc=(String)dataSnapshot.child("location").getValue();
                String post_image=(String)dataSnapshot.child("image").getValue();

                String post_uid=(String)dataSnapshot.child("uid").getValue();

                mBlogSingleTitle.setText(post_title);
                mBlogSingleDesc.setText(post_desc);


                Picasso.with(Demac.this).load(post_image).into(mBlogSingleImage);

                if (mAuth.getCurrentUser().getUid().equals(post_uid)){

                    mSingleRemoveBtn.setVisibility(View.VISIBLE);

                }
                mSingleRemoveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent=new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("http://maps.google.co.in/maps?q="+post_desc));
                        if (intent.resolveActivity(getPackageManager()) !=null){
                            startActivity(intent);
                        }

                    }
                });


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {



            }
        });



        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);



    }
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}




