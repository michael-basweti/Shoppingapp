package com.example.geekalex.shoppingapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class Post extends AppCompatActivity {
    private RecyclerView mBlogList;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabaseUsers;
    private boolean mProcessLike=false;
    private DatabaseReference mDatabaseLikes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ListView lv;
        String[] charactersDC={"Electronics","Machinery","Utensils & Cutlery","Grocery","Cereals","Bread & Cakes",
                "Snacks","Furniture","Bedding"};
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        mAuth=FirebaseAuth.getInstance();
        mAuthListener=new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser()==null){

                    Intent homeIntent=new Intent(Post.this,Main2Activity.class);
                    homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(homeIntent);
                }

            }
        };
        lv=(ListView)findViewById(R.id.ListView);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,charactersDC);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (i == 0) {
                    Intent cholera = new Intent(Post.this, Single.class);
                    startActivity(cholera);
                } else if (i == 1) {

                    Intent Malaria = new Intent(Post.this, Mac.class);
                    startActivity(Malaria);

                } else if (i == 2) {

                    Intent Typhoid = new Intent(Post.this, Ut.class);
                    startActivity(Typhoid);

                }
                else if (i == 3) {

                    Intent Accident = new Intent(Post.this, Groc.class);
                    startActivity(Accident);

                }
                else if (i == 4) {

                    Intent Tuberculosis = new Intent(Post.this, Cer.class);
                    startActivity(Tuberculosis);

                }

                else if (i == 5) {

                    Intent Epilepsy = new Intent(Post.this, Bre.class);
                    startActivity(Epilepsy);

                }
                else if (i == 6) {

                    Intent Asthma = new Intent(Post.this, Sna.class);
                    startActivity(Asthma);

                }
                else if (i == 7) {

                    Intent Head_Injury = new Intent(Post.this, Fun.class);
                    startActivity(Head_Injury);

                }
                else if (i == 8) {

                    Intent Diabetes = new Intent(Post.this, Bed.class);
                    startActivity(Diabetes);

                }
                /*
                else if (i == 9) {

                    Intent Cardiac_Arrest = new Intent(AdminAccount.this, Cardiac_Arrest.class);
                    startActivity(Cardiac_Arrest);

                }
                else if (i == 10) {

                    Intent Labour = new Intent(AdminAccount.this, Labour.class);
                    startActivity(Labour);

                }*/
            }




        });
    }
}
