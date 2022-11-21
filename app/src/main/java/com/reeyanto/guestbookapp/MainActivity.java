package com.reeyanto.guestbookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.reeyanto.guestbookapp.models.Guest;

public class MainActivity extends AppCompatActivity {

    private EditText etNama, etEmail, etTelp;
    private String nama, email, telp;
    private Button btnKirim, btnKirimParcelable, btnBukaWebsite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inisialisasiKomponen();

        btnKirim.setOnClickListener(this::buttonClicked);
        btnKirimParcelable.setOnClickListener(this::buttonClicked);
        btnBukaWebsite.setOnClickListener(this::buttonClicked);
    }

    private void buttonClicked(View view) {
        if ((view.getId() == R.id.btn_kirim) && dataValid()) {
            kirimDetailExtraActivity();
        } else if ((view.getId() == R.id.btn_kirim_parcelable) && dataValid()) {
            kirimDetailParcelableActivity();
        } else if (view.getId() == R.id.btn_buka_website) {
            bukaWebsite();
        }
    }

    private void kirimDetailParcelableActivity() {
        Intent intent = new Intent(this, DetailActivity.class);
        Guest guest = new Guest(nama, email, telp);

        intent.putExtra("GUEST", guest);
        startActivity(intent);
    }

    private void bukaWebsite() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.pnp.ac.id"));

        // khusus untuk android API 30 ke atas, perlu ditambahkan <queries> pada AndroidManifest.xml
        // karena baris berikut tetap mengembalikan nilai null meskipun web browser telah terinstal
        if (intent.resolveActivity(getPackageManager()) != null) startActivity(intent);
        else Toast.makeText(this, "Aplikasi web browser tidak tersedia", Toast.LENGTH_SHORT).show();
    }

    private void kirimDetailExtraActivity() {
        Intent intent = new Intent(this, DetailActivity.class);

        intent.putExtra("NAMA", nama);
        intent.putExtra("EMAIL", email);
        intent.putExtra("TELP", telp);

        startActivity(intent);
    }

    private boolean dataValid() {
        boolean apakahDataValid = true;

        nama = etNama.getText().toString().trim();
        email = etEmail.getText().toString().trim();
        telp = etTelp.getText().toString().trim();

        if (nama.isEmpty()) {
            apakahDataValid = false;
            etNama.setError("Nama harus diisi");
            etNama.requestFocus();
        }

        if (email.isEmpty()) {
            apakahDataValid = false;
            etEmail.setError("Email harus diisi");
            etEmail.requestFocus();
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            apakahDataValid = false;
            etEmail.setError("Format email tidak lengkap");
            etEmail.requestFocus();
        }

        if (telp.isEmpty()) {
            apakahDataValid = false;
            etTelp.setError("Telp harus diisi");
            etTelp.requestFocus();
        }

        return apakahDataValid;
    }

    private void inisialisasiKomponen() {
        etNama = findViewById(R.id.etNama);
        etEmail = findViewById(R.id.etEmail);
        etTelp = findViewById(R.id.etTelp);

        btnKirim = findViewById(R.id.btn_kirim);
        btnKirimParcelable = findViewById(R.id.btn_kirim_parcelable);
        btnBukaWebsite = findViewById(R.id.btn_buka_website);
    }
}