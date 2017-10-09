package com.example.geekalex.shoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class AdminAccount extends AppCompatActivity {

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
        setContentView(R.layout.activity_admin_account);

        mAuth= FirebaseAuth.getInstance();
        mAuthListener=new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser()==null){

                    Intent homeIntent=new Intent(AdminAccount.this,Main2Activity.class);
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
                    Intent cholera = new Intent(AdminAccount.this, Electronics.class);
                    startActivity(cholera);
                } else if (i == 1) {

                    Intent Malaria = new Intent(AdminAccount.this, Machinery.class);
                    startActivity(Malaria);

                } else if (i == 2) {

                    Intent Typhoid = new Intent(AdminAccount.this, Utensils.class);
                    startActivity(Typhoid);

                }
                else if (i == 3) {

                    Intent Accident = new Intent(AdminAccount.this, Grocery.class);
                    startActivity(Accident);

                }
                else if (i == 4) {

                    Intent Tuberculosis = new Intent(AdminAccount.this, Cereals.class);
                    startActivity(Tuberculosis);

                }

                else if (i == 5) {

                    Intent Epilepsy = new Intent(AdminAccount.this, Bread.class);
                    startActivity(Epilepsy);

                }
                else if (i == 6) {

                    Intent Asthma = new Intent(AdminAccount.this, Snacks.class);
                    startActivity(Asthma);

                }
                else if (i == 7) {

                    Intent Head_Injury = new Intent(AdminAccount.this, Funiture.class);
                    startActivity(Head_Injury);

                }
                else if (i == 8) {

                    Intent Diabetes = new Intent(AdminAccount.this, Bedding.class);
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


    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }


    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu1, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == R.id.action_logout) {

            logout();

        }
        if (item.getItemId() == R.id.action_settings) {

            Intent mainIntent = new Intent(AdminAccount.this,Post.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(mainIntent);

        }
        if (item.getItemId() == R.id.cutomer) {

            Intent mainIntent = new Intent(AdminAccount.this,MainActivity.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(mainIntent);

        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {

        mAuth.signOut();

    }
}
