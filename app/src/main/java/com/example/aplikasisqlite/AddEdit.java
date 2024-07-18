package com.example.aplikasisqlite;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.aplikasisqlite.databinding.ActivityMainBinding;
import com.example.aplikasisqlite.helper.DbHelper;

import java.util.Objects;

public class AddEdit extends AppCompatActivity {
    EditText txt_id, txt_name, txt_address, txt_tanggal,txt_jenis;
    Button btn_submit, btn_cancel;
    DbHelper SQLite = new DbHelper(this);
    String id, name, address, tanggal, jenis;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Toolbar toolbar = findViewById(R.id.toolbar);
        //menetapkan toolbar sebagai actionbar
        setSupportActionBar(toolbar);


        txt_id = (EditText) findViewById(R.id.txt_id);
        txt_name = (EditText) findViewById(R.id.txt_nama);
        txt_tanggal = (EditText) findViewById(R.id.txt_tanggal);
        txt_jenis = (EditText) findViewById(R.id.txt_jenis);
        txt_address = (EditText) findViewById(R.id.txt_alamat);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        id = getIntent().getStringExtra(MainActivity.TAG_ID);
        name = getIntent().getStringExtra(MainActivity.TAG_NAME);
        tanggal = getIntent().getStringExtra(MainActivity.TAG_TANGGAL);
        jenis = getIntent().getStringExtra(MainActivity.TAG_JENIS);
        address = getIntent().getStringExtra(MainActivity.TAG_ADDRESS);

        if(id == null || id.equals("")){
            Objects.requireNonNull(getSupportActionBar()).setTitle("Tambah Data");
        }else{
            Objects.requireNonNull(getSupportActionBar()).setTitle("Edit Data");
            txt_id.setText(id);
            txt_name.setText(name);
            txt_tanggal.setText(tanggal);
            txt_jenis.setText(jenis);
            txt_address.setText(address);
        }

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(txt_id.getText().toString().equals("")){
                        save();
                    }else{
                        edit();
                    }
                } catch (Exception e){
                    Log.e("Submit", e.toString());
                }
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blank();
                finish();
            }
        });
    }
    public  void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                blank();
                this.finish();
                return true;
            default:
                return  super.onOptionsItemSelected(item);
        }
    }
    private void blank()
    {
        txt_name.requestFocus();
        txt_id.setText(null);
        txt_name.setText(null);
        txt_tanggal.setText(null);
        txt_jenis.setText(null);
        txt_address.setText(null);
    }

    private void save(){

        if(String.valueOf(txt_name.getText()).equals("") || String.valueOf(txt_address.getText()).equals("")){
            Toast.makeText(getApplicationContext(), "Kolom tidak boleh kosong",Toast.LENGTH_SHORT).show();
        }else{
            SQLite.insert(txt_name.getText().toString().trim(), txt_tanggal.getText().toString().trim(), txt_jenis.getText().toString().trim(), txt_address.getText().toString().trim());
            blank();
            finish();
        }
    }
    private void edit(){

        if(String.valueOf(txt_name.getText()).equals("") || String.valueOf(txt_address.getText()).equals("")){
            Toast.makeText(getApplicationContext(), "Please input name or address",Toast.LENGTH_SHORT).show();
        }else{
            SQLite.update(Integer.parseInt(txt_id.getText().toString().trim()),txt_name.getText().toString().trim(),txt_tanggal.getText().toString().trim(),txt_jenis.getText().toString().trim(), txt_address.getText().toString().trim());
            blank();
            finish();
        }
    }
}
