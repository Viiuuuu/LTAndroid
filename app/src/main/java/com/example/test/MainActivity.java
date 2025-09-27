package com.example.test;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        btnLogin.setOnClickListener(v -> Toast.makeText(this, "Đăng nhập", Toast.LENGTH_SHORT).show());

        // RecyclerViews
        recyclerComics = findViewById(R.id.recyclerComics);
        recyclerHot = findViewById(R.id.recyclerHot);

        // Setup dữ liệu mẫu
        comicList = new ArrayList<>();
        // sử dụng ic_launcher_foreground làm ảnh demo (có sẵn)
        comicList.add(new Comic(R.drawable.ic_launcher_foreground, "Thợ Săn Hạng S", "Chap 63"));
        comicList.add(new Comic(R.drawable.ic_launcher_foreground, "Đại Tiểu Thư", "Chap 108"));
        comicList.add(new Comic(R.drawable.ic_launcher_foreground, "Sống Sót Như Nàng", "Chap 8"));
        comicList.add(new Comic(R.drawable.ic_launcher_foreground, "Xin Người Hãy Chăm Sóc", "Chap 26"));

        hotList = new ArrayList<>();
        hotList.add(new Comic(R.drawable.ic_launcher_foreground, "Hot: Bát Đặc", "Chap 6"));
        hotList.add(new Comic(R.drawable.ic_launcher_foreground, "Hot: Trở Thành Mới", "Chap 34"));
        hotList.add(new Comic(R.drawable.ic_launcher_foreground, "Hot: [18+] Cuộc Hôn", "Chap 39"));

        // Adapter & layout
        comicAdapter = new ComicAdapter(this, comicList);
        hotAdapter = new ComicAdapter(this, hotList);

        // grid 2 cột cho danh sách chính
        recyclerComics.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerComics.setAdapter(comicAdapter);

        // list dọc cho hot
        recyclerHot.setLayoutManager(new LinearLayoutManager(this));
        recyclerHot.setAdapter(hotAdapter);
    }
}
