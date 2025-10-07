package com.example.test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText searchBar;
    private ImageButton btnNotification, btnSettings, btnLogin;
    private RecyclerView recyclerComics, recyclerHot;
    private ComicAdapter comicAdapter, hotAdapter;
    private List<Comic> comicList, hotList;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "LoginPrefs";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Toolbar (nếu muốn dùng API toolbar)
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // ẩn title mặc định (vì ta dùng TextView làm title)
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Ánh xạ header
        searchBar = findViewById(R.id.searchBar);
        btnNotification = findViewById(R.id.btnNotification);
        btnSettings = findViewById(R.id.btnSettings);
        btnLogin = findViewById(R.id.btnLogin);

        btnNotification.setOnClickListener(v -> Toast.makeText(this, "Thông báo", Toast.LENGTH_SHORT).show());
        btnSettings.setOnClickListener(v -> Toast.makeText(this, "Cài đặt", Toast.LENGTH_SHORT).show());
        btnLogin.setOnClickListener(v -> {
            if (isLoggedIn()) {
                // Nếu đã đăng nhập, hiển thị menu đăng xuất
                showLogoutDialog();
            } else {
                // Nếu chưa đăng nhập, chuyển đến trang login
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });

        // RecyclerViews
        recyclerComics = findViewById(R.id.recyclerComics);
        recyclerHot = findViewById(R.id.recyclerHot);

        // Setup dữ liệu mẫu - SỬA LẠI TÊN ẢNH
        comicList = new ArrayList<>();
        // Sử dụng ảnh có sẵn và ảnh bạn đã thêm
        comicList.add(new Comic(R.drawable.anh1, "Thợ Săn Hạng S", "Chap 63"));
        comicList.add(new Comic(R.drawable.anh1, "Đại Tiểu Thư", "Chap 108"));
        comicList.add(new Comic(R.drawable.anh1, "Sống Sót Như Nàng", "Chap 8"));
        comicList.add(new Comic(R.drawable.anh1, "Xin Người Hãy Chăm Sóc", "Chap 26"));

        hotList = new ArrayList<>();
        hotList.add(new Comic(R.drawable.anh1, "Hot: Bát Đặc", "Chap 6"));
        hotList.add(new Comic(R.drawable.anh1, "Hot: Trở Thành Mới", "Chap 34"));
        hotList.add(new Comic(R.drawable.anh1, "Hot: [18+] Cuộc Hôn", "Chap 39"));

        // Adapter & layout
        comicAdapter = new ComicAdapter(this, comicList);
        hotAdapter = new ComicAdapter(this, hotList);

        // Thiết lập click listener cho comic adapter
        comicAdapter.setOnComicClickListener(this::onComicClick);
        hotAdapter.setOnComicClickListener(this::onComicClick);

        // grid 2 cột cho danh sách chính
        recyclerComics.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerComics.setAdapter(comicAdapter);

        // list dọc cho hot
        recyclerHot.setLayoutManager(new LinearLayoutManager(this));
        recyclerHot.setAdapter(hotAdapter);
    }

    private void onComicClick(Comic comic) {
        if (isLoggedIn()) {
            // Nếu đã đăng nhập, cho phép xem truyện
            Toast.makeText(this, "Đang mở truyện: " + comic.getTitle(), Toast.LENGTH_SHORT).show();
            // Ở đây bạn có thể thêm logic để mở trang chi tiết truyện
        } else {
            // Nếu chưa đăng nhập, hiển thị dialog yêu cầu đăng nhập
            showLoginRequiredDialog(comic);
        }
    }

    private void showLoginRequiredDialog(Comic comic) {
        new AlertDialog.Builder(this)
                .setTitle("Yêu cầu đăng nhập")
                .setMessage("Bạn cần đăng nhập để xem truyện: " + comic.getTitle())
                .setPositiveButton("Có", (dialog, which) -> {
                    // Chuyển đến trang login
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    startActivity(intent);
                })
                .setNegativeButton("Không", (dialog, which) -> {
                    // Quay về trang chủ (không làm gì cả)
                    Toast.makeText(this, "Đã hủy", Toast.LENGTH_SHORT).show();
                })
                .show();
    }

    private void showLogoutDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Đăng xuất")
                .setMessage("Bạn có muốn đăng xuất không?")
                .setPositiveButton("Có", (dialog, which) -> {
                    logout();
                })
                .setNegativeButton("Không", null)
                .show();
    }

    private boolean isLoggedIn() {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    private void logout() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_IS_LOGGED_IN, false);
        editor.apply();
        Toast.makeText(this, "Đã đăng xuất", Toast.LENGTH_SHORT).show();
    }

    public void setLoggedIn(boolean loggedIn) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_IS_LOGGED_IN, loggedIn);
        editor.apply();
    }
}