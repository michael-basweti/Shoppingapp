package com.example.geekalex.shoppingapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Snack_Retrieval extends AppCompatActivity {
    private RecyclerView mBlogList;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabaseUsers;
    private boolean mProcessLike=false;
    private DatabaseReference mDatabaseLikes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack__retrieval);
        mAuth=FirebaseAuth.getInstance();


        mDatabase= FirebaseDatabase.getInstance().getReference().child("Snacks");



        mDatabase.keepSynced(true);


        mBlogList=(RecyclerView)findViewById(R.id.blog_list);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    protected void onStart() {
        super.onStart();

        mDatabase.keepSynced(true);




        FirebaseRecyclerAdapter<Blog7,BlogViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Blog7,BlogViewHolder>(
                Blog7.class,
                R.layout.blogrow_snacks,
                BlogViewHolder.class,
                mDatabase

        ) {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, Blog7 model, int position) {

                final String post_key=getRef(position).getKey();

                viewHolder.setAccount_no(model.getPrice());
                viewHolder.setPhone(model.getCommodity());
                viewHolder.setBank(model.getDescription());
                viewHolder.setFirst_Name(model.getName());
                viewHolder.setDate(model.getLocation());
                viewHolder.setQuantity(model.getQuantity());
                viewHolder.setImage(getApplicationContext(),model.getImage());





                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Toast.makeText(MainActivity.this,post_key,Toast.LENGTH_LONG).show();

                        Intent singleBlogIntent=new Intent(Snack_Retrieval.this,Desna.class);
                        singleBlogIntent.putExtra("blog_id",post_key);
                        startActivity(singleBlogIntent);

                    }
                });
                viewHolder.mView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        Intent singleBlogIntent=new Intent(Snack_Retrieval.this,Snack_Retrieval.class);
                        singleBlogIntent.putExtra("blog_id",post_key);
                        startActivity(singleBlogIntent);
                        return true;

                    }
                });


            }
        };

        mBlogList.setAdapter(firebaseRecyclerAdapter);

    }




    public static class BlogViewHolder extends RecyclerView.ViewHolder{


        View mView;

        FirebaseAuth mAuth;
        public BlogViewHolder(View itemView) {
            super(itemView);

            mView=itemView;

            mAuth=FirebaseAuth.getInstance();


        }
        public void setAccount_no(String gender){
            TextView post_title=(TextView)mView.findViewById(R.id.price);
            post_title.setText(gender);
        }
        public void setQuantity(String quantity){
            TextView post_quantity=(TextView)mView.findViewById(R.id.quantity);
            post_quantity.setText(quantity);
        }
        public void setPhone(String gender){
            TextView post_title=(TextView)mView.findViewById(R.id.commodity);
            post_title.setText(gender);
        }

        public void setBank(String age){
            TextView post_title=(TextView)mView.findViewById(R.id.description);
            post_title.setText(age);
        }
        public void setFirst_Name(String desc){
            TextView post_desc=(TextView)mView.findViewById(R.id.name);
            post_desc.setText(desc);
        }

        public void setDate(String username){
            TextView post_username=(TextView)mView.findViewById(R.id.location);
            post_username.setText(username);
        }
        public void setImage(Context ctx, String image){

            ImageView post_image=(ImageView) mView.findViewById(R.id.post_image);
            Picasso.with(ctx).load(image).into(post_image);

        }


    }
}


