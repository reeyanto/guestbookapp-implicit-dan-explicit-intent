package com.reeyanto.guestbookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.reeyanto.guestbookapp.models.Guest;

public class DetailActivity extends AppCompatActivity {

    private TextView tvHasil;
    private Button btnKembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initComponents();
        showData();

        btnKembali.setOnClickListener(view -> super.onBackPressed());
    }

    private void showData() {
        String hasil;

        if (getIntent().getParcelableExtra("GUEST") != null) {
            Guest guest = getIntent().getParcelableExtra("GUEST");

            hasil = guest.getNama().concat("\n");
            hasil+= guest.getEmail().concat("\n");
            hasil+= guest.getTelp().concat("\n");

        } else {
            Intent intent = getIntent();
            hasil = intent.getStringExtra("NAMA").concat("\n");
            hasil+= intent.getStringExtra("EMAIL").concat("\n");
            hasil+= intent.getStringExtra("TELP");
        }

        tvHasil.setText(hasil);
    }

    private void initComponents() {
        tvHasil = findViewById(R.id.tv_hasil);
        btnKembali = findViewById(R.id.btn_kembali);
    }
}