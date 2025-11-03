package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);

		TextView tvUsername = findViewById(R.id.tvUsername);
		TextView tvDob = findViewById(R.id.tvDob);
		TextView tvGender = findViewById(R.id.tvGender);
		TextView tvEmail = findViewById(R.id.tvEmail);
        ImageButton btnSettings = findViewById(R.id.btnSettingsProfile);
        TextView tvBack = findViewById(R.id.tvBack);

		Intent intent = getIntent();
		String username = intent.getStringExtra("username");
		String dob = intent.getStringExtra("dob");
		String gender = intent.getStringExtra("gender");
		String email = intent.getStringExtra("email");

		tvUsername.setText(username != null ? username : "");
		tvDob.setText(dob != null ? dob : "");
		tvGender.setText(gender != null ? gender : "");
		tvEmail.setText(email != null ? email : "");

		btnSettings.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent backToLogin = new Intent(Profile.this, Login.class);
				backToLogin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(backToLogin);
				finish();
			}
		});

        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToMain = new Intent(Profile.this, MainActivity.class);
                backToMain.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(backToMain);
                finish();
            }
        });
	}
}


