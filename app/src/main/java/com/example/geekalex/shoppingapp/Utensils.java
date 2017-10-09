package com.example.geekalex.shoppingapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class Utensils extends AppCompatActivity {
    private EditText mPrice,mName,mDescription;
    private Button mPost;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser mCurrentUser;
    private DatabaseReference mDatabaseUser;
    private DatabaseReference mDatabaseUser1;
    private DatabaseReference mDatabase;
    private static final int GALLERY_REQUEST=1;
    private StorageReference mStorageImage;
    private ProgressDialog mProgress;
    private Spinner mSpinner;
    private ImageButton mIB;

    private Uri mImageUri=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utensils);
        mAuth= FirebaseAuth.getInstance();
        mCurrentUser=mAuth.getCurrentUser();
        mDatabaseUser= FirebaseDatabase.getInstance().getReference().child("Users").child(mCurrentUser.getUid());
        mDatabaseUser1= FirebaseDatabase.getInstance().getReference().child("Users");
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Utensils");
        mProgress=new ProgressDialog(this);
        mStorageImage= FirebaseStorage.getInstance().getReference().child("Profile_images");


        mDescription=(EditText)findViewById(R.id.description);
        mIB=(ImageButton)findViewById(R.id.imageView2);
        mName=(EditText)findViewById(R.id.name);
        mPrice=(EditText)findViewById(R.id.price);
        mPost=(Button)findViewById(R.id.post);

        mIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent=new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,GALLERY_REQUEST);
            }
        });

        mPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postCommodities();
            }
        });

    }

    private void postCommodities() {
        mProgress.setMessage("sending request...");
        mProgress.show();

        final String price=mPrice.getText().toString().trim();
        final String description=mDescription.getText().toString().trim();
        final String name=mName.getText().toString().trim();

        if (!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(description)&&!TextUtils.isEmpty(price)&&mImageUri!=null) {


            final DatabaseReference newPost = mDatabase.push();


            mDatabaseUser.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(final DataSnapshot dataSnapshot) {

                    StorageReference filepath=mStorageImage.child(mImageUri.getLastPathSegment());
                    filepath.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            String downloadUri=taskSnapshot.getDownloadUrl().toString();


                            newPost.child("price").setValue("Price:Ksh"+price);
                            newPost.child("description").setValue(description);
                            newPost.child("commodity").setValue(name);


                            newPost.child("uid").setValue(mCurrentUser.getUid());
                            newPost.child("image").setValue(downloadUri);
                            newPost.child("name").setValue( dataSnapshot.child("name").getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {

                                    } else {
                                        Toast.makeText(Utensils.this, "Please fill all the spaces or check your internet connection...", Toast.LENGTH_LONG).show();

                                    }
                                }
                            });
                            newPost.child("location").setValue(dataSnapshot.child("location").getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {

                                    } else {
                                        Toast.makeText(Utensils.this, "Please fill all the spaces or check your internet connection...", Toast.LENGTH_LONG).show();

                                    }
                                }
                            });

                        }
                    });


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            mProgress.dismiss();
            Toast.makeText(Utensils.this, "Your Data Has been sent", Toast.LENGTH_LONG).show();

            Intent mainIntent = new Intent(Utensils.this, Utensils.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(mainIntent);

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==GALLERY_REQUEST && resultCode==RESULT_OK){

            Uri imageUri=data.getData();

            CropImage.activity(imageUri)
                    .setGuidelines(CropImageView.Guidelines.ON)

                    .start(this);


        }
        if (requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result=CropImage.getActivityResult(data);
            if (resultCode==RESULT_OK){
                mImageUri=result.getUri();
                mIB.setImageURI(mImageUri);
            }else if(resultCode==CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                Exception error=result.getError();
            }
        }
    }
}


