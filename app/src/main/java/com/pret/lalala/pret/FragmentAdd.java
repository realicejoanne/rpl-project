package com.pret.lalala.pret;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.pret.lalala.pret.Model.Barang;

import java.io.IOException;


public class FragmentAdd extends Fragment {

    String currentUser;
    TextInputLayout namaBarangInput;
    TextInputLayout deskripsiBarangInput;
    TextInputLayout alamatBarangInput;
    TextInputLayout hargaBarangInput;
    TextInputEditText namaBarang;
    TextInputEditText deskripsiBarang;
    TextInputEditText alamatBarang;
    TextInputEditText hargaBarang;
    MaterialButton pinjamkanButton;
    MaterialButton uploadButton;
    private DatabaseReference mDatabase;
    private Uri filePath;
    private StorageReference storageReference;
    private ImageView photo;
    private int PICK_IMAGE_REQUEST = 234;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_add, null);

        currentUser = getActivity().getSharedPreferences("PREFERENCE_CURRENT_USER",
                getActivity().MODE_PRIVATE).getString("currentUser", "lala");

        namaBarangInput = rootView.findViewById(R.id.namaTextInputlayout);
        deskripsiBarangInput = rootView.findViewById(R.id.deskripsiTextInputlayout);
        alamatBarangInput = rootView.findViewById(R.id.alamatTextInputlayout);
        hargaBarangInput = rootView.findViewById(R.id.hargaTextInputlayout);

        namaBarang = rootView.findViewById(R.id.namaEditText);
        deskripsiBarang = rootView.findViewById(R.id.deskripsiEditText);
        alamatBarang = rootView.findViewById(R.id.alamatEditText);
        hargaBarang = rootView.findViewById(R.id.hargaEditText);
        pinjamkanButton = rootView.findViewById(R.id.pinjamkan_button);
        uploadButton = rootView.findViewById(R.id.upload_button);
        photo = rootView.findViewById(R.id.iv_photo);

        storageReference = FirebaseStorage.getInstance().getReference();

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });

        pinjamkanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kirimData(v);
            }
        });

        return rootView;
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

//<<<<<<< HEAD
//    void kirimData(View v) {
//
//        if (TextUtils.isEmpty(namaBarang.getText())) {
//
//            namaBarangInput.setError("Nama barang harus diisi");
//            namaBarangInput.requestFocus();
//
//        } else if (TextUtils.isEmpty(deskripsiBarang.getText())) {
//
//            deskripsiBarangInput.setError("Deskripsi barang harus diisi");
//            deskripsiBarangInput.requestFocus();
//
//        } else if (TextUtils.isEmpty(alamatBarang.getText())) {

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getApplicationContext().getContentResolver(), filePath);
                photo.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void kirimData(View v) {

        if (filePath != null) {
            //displaying a progress dialog while upload is going on
            final ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setTitle("Uploading");
            progressDialog.show();

            StorageReference riversRef = storageReference.child("images/pic.jpg");
            riversRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //if the upload is successfull
                            //hiding the progress dialog
                            progressDialog.dismiss();

                            //and displaying a success toast
                            Toast.makeText(getActivity(), "File Uploaded ", Toast.LENGTH_LONG).show();

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            //if the upload is not successfull
                            //hiding the progress dialog
                            progressDialog.dismiss();

                            //and displaying error message
                            Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //calculating progress percentage
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                            //displaying percentage in progress dialog
                            progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                        }
                    });
            if (TextUtils.isEmpty(namaBarang.getText())) {

                namaBarangInput.setError("Nama barang harus diisi");
                namaBarangInput.requestFocus();

            } else if (TextUtils.isEmpty(deskripsiBarang.getText())) {

                deskripsiBarangInput.setError("Deskripsi barang harus diisi");
                deskripsiBarangInput.requestFocus();

            } else if (TextUtils.isEmpty(alamatBarang.getText())) {

                alamatBarangInput.setError("Alamat barang harus diisi");
                alamatBarangInput.requestFocus();

            } else {

                mDatabase = FirebaseDatabase.getInstance().getReference();
                StorageReference storageRef = storageReference.child("images/pic.jpg");
                storageRef.child("images/pic.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                    }
                });


                //Getting Values
                String nama = namaBarang.getText().toString();
                String deskripsi = deskripsiBarang.getText().toString();
                String alamat = alamatBarang.getText().toString();
                int harga = Integer.parseInt(hargaBarang.getText().toString());
                String link = storageRef.child("images/pic.jpg").getDownloadUrl().toString();


                //Creating new barang node
                String barangId = mDatabase.push().getKey();
                //Creating barang Object
                Barang barang = new Barang(nama, deskripsi, alamat, harga, currentUser, link);

                //Pushing user to 'barang node using barangID
                mDatabase.child("barang").child(barangId).setValue(barang);

                Snackbar.make(v, "Barang anda telah berhasil ditambahkan", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();

                //            Intent intent = new Intent(FormActivity.this, MainActivity.class);
                //            startActivity(intent);
                //            overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
                //            finish();

            }
        }
        //if there is not any file
        else {
            //you can display an error toast
        }
    }
}