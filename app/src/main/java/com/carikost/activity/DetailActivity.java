package com.carikost.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.carikost.R;
import com.carikost.adapter.AdapterKost;
import com.carikost.api.RequestData;
import com.carikost.api.RetroServer;
import com.carikost.model.ModelKost;
import com.carikost.model.ModelResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    String id,foto,nama,daerah,tipe,harga,alamat,status,deskripsi;
    ImageView ivFoto;
    TextView tvId,tvNama,tvTipe,tvDaerah,tvAlamat,tvStatus,tvHarga,tvDeskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent i = getIntent();
        id = i.getStringExtra("xId");
        foto = i.getStringExtra("xFoto");
        nama = i.getStringExtra("xNama");
        tipe = i.getStringExtra("xTipe");
        daerah = i.getStringExtra("xDaerah");
        alamat=i.getStringExtra("xAlamat");
        harga =i.getStringExtra("xHarga");
        status=i.getStringExtra("xStatus");
        deskripsi=i.getStringExtra("xDeskripsi");

        ivFoto = findViewById(R.id.image_kost);
        tvId = findViewById(R.id.tv_id_detail);
        tvNama = findViewById(R.id.tv_nama);
        tvTipe = findViewById(R.id.tv_tipe);
        tvDaerah = findViewById(R.id.tv_daerah);
        tvAlamat = findViewById(R.id.tv_alamat);
        tvStatus = findViewById(R.id.tv_status);
        tvHarga = findViewById(R.id.tv_harga);
        tvDeskripsi = findViewById(R.id.tv_deskripsi);

        Glide.with(DetailActivity.this)
                .load(foto)
                .into(ivFoto);

        tvId.setText(id);
        tvNama.setText(nama);
        tvDaerah.setText(daerah);
        tvAlamat.setText(alamat);
        tvHarga.setText(harga);
        tvTipe.setText(tipe);
        tvStatus.setText(status);
        tvDeskripsi.setText(deskripsi);
    }
}