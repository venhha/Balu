package com.ven.balu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ven.balu.R;
import com.ven.balu.common.Constants;
import com.ven.balu.common.MyUtils;
import com.ven.balu.data.SharedPreferenceManager;
import com.ven.balu.model.Customer;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    ImageView btn_changeAvatar;
    LinearLayout btn_EditProfile;
    CircleImageView iv_avatar_profile;
    TextView tvFullName, tvUsername, tvCustomerID, tvGender, tvDob, tvAddress, tvEmail, tvPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // *processing
        bindViews();
        try {
            loadData();
        } catch (Exception e) {
            System.out.println("ERROR ProfileActivity");
            e.printStackTrace();
        }
    }

    private void loadData() {
        Customer customer = SharedPreferenceManager.getInstance(this).getCustomer();

        tvFullName.setText(customer.getFirstName());
        tvUsername.setText(customer.getUsername());

        tvCustomerID.setText("UID: " + customer.getCustomerId().toString());
        tvGender.setText("Gender: " + customer.getGender());
        tvDob.setText("Birthday: " + customer.getDob());
        tvAddress.setText("Address: " + customer.getAddress());
        tvEmail.setText("Email: " + customer.getEmail());
        tvPhoneNumber.setText("PhoneNumber: " + customer.getPhoneNumber());

//        String imageUrl = "https://static.vecteezy.com/system/resources/previews/000/439/863/original/vector-users-icon.jpg";
        String imageUrl = Constants.IMAGE_URL + "a1.jpg";

        MyUtils.getInstance(this).showCircleImageView(imageUrl,iv_avatar_profile);
    }

    private void bindViews() {
        btn_changeAvatar = findViewById(R.id.btn_changeAvatar);
        tvFullName = findViewById(R.id.tvFullName_profile);
        tvUsername = findViewById(R.id.tvUsername_profile);
        tvCustomerID = findViewById(R.id.tvCustomerID_profile);
        tvGender = findViewById(R.id.tvGender_profile);
        tvDob = findViewById(R.id.tvDob_profile);
        tvAddress = findViewById(R.id.tvAddress_profile);
        tvEmail = findViewById(R.id.tvEmail_profile);
        tvPhoneNumber = findViewById(R.id.tvPhoneNumber_profile);
        iv_avatar_profile = findViewById(R.id.iv_avatar_profile);
        btn_EditProfile = findViewById(R.id.btnEditProfile);

        btn_EditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}