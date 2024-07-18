package com.example.aplikasisqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.EditText;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {
    EditText txt_id, txt_name, txt_address, txt_tanggal, txt_jenis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        txt_id = findViewById(R.id.txt_nomord);
        txt_name = findViewById(R.id.txt_namad);
        txt_tanggal = findViewById(R.id.txt_tgld);
        txt_jenis = findViewById(R.id.txt_jenisd);
        txt_address = findViewById(R.id.txt_alamatd);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Detail Mahasiswa");
        // Ambil data dari intent
        String id = getIntent().getStringExtra(MainActivity.TAG_ID);
        String name = getIntent().getStringExtra(MainActivity.TAG_NAME);
        String tanggal = getIntent().getStringExtra(MainActivity.TAG_TANGGAL);
        String jenis = getIntent().getStringExtra(MainActivity.TAG_JENIS);
        String address = getIntent().getStringExtra(MainActivity.TAG_ADDRESS);

        // Tampilkan data di EditText
        txt_id.setText(id);
        txt_name.setText(name);
        txt_tanggal.setText(tanggal);
        txt_jenis.setText(jenis);
        txt_address.setText(address);
    }
}