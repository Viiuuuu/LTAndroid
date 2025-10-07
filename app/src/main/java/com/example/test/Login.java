package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private EditText edtEmail, edtPassword;
    private Button btnLogin;
    private TextView tvForgotPassword;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "Haviiu";
    private static final String KEY_IS_LOGGED_IN = "123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityindex); // dùng đúng tên layout của bạn

        // Khởi tạo SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtEmail.getText().toString();
                String pass = edtPassword.getText().toString();

                // Kiểm tra thông tin đăng nhập (ở đây chỉ là demo)
                if (username.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(Login.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    // Đăng nhập thành công
                    Toast.makeText(Login.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();

                    // Lưu trạng thái đăng nhập
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(KEY_IS_LOGGED_IN, true);
                    editor.apply();

                    // Chuyển sang trang Profile và truyền thông tin người dùng
                    Intent intent = new Intent(Login.this, Profile.class);
                    intent.putExtra("username", username);
                    intent.putExtra("dob", "11/01/2005");
                    intent.putExtra("gender", "Nữ");
                    intent.putExtra("email", "nguyenhavyy0105@gmail.com");
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        });

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Login.this, "Tính năng quên mật khẩu", Toast.LENGTH_SHORT).show();
            }
        });
    }
}