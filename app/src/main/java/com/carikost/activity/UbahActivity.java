package com.carikost.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.carikost.R;
import com.carikost.api.RequestData;
import com.carikost.api.RetroServer;
import com.carikost.model.ModelResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahActivity extends AppCompatActivity {
    String id_kost,foto,nama,daerah,tipe,harga,alamat,status,deskripsi;
    ImageView ivFoto;
    Context ctx;
    EditText etNama,etFoto,etTipe,etDaerah,etAlamat,etStatus,etHarga,etDeskripsi;
    Button btnUbah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);

        Intent i = getIntent();
        id_kost = i.getStringExtra("xId");
        foto = i.getStringExtra("xFoto");
        nama = i.getStringExtra("xNama");
        tipe = i.getStringExtra("xTipe");
        daerah = i.getStringExtra("xDaerah");
        alamat=i.getStringExtra("xAlamat");
        harga =i.getStringExtra("xHarga");
        status=i.getStringExtra("xStatus");
        deskripsi=i.getStringExtra("xDeskripsi");

        ivFoto = findViewById(R.id.iv_foto_ubah);
        etNama = findViewById(R.id.et_nama_ubah);
        etFoto = findViewById(R.id.et_foto_ubah);
        etTipe = findViewById(R.id.et_tipe_ubah);
        etDaerah = findViewById(R.id.et_daerah_ubah);
        etAlamat = findViewById(R.id.et_alamat_ubah);
        etStatus = findViewById(R.id.et_status_ubah);
        etHarga = findViewById(R.id.et_harga_ubah);
        etDeskripsi = findViewById(R.id.et_deskripsi_ubah);

        Glide.with(UbahActivity.this)
                .load(foto)
                .into(ivFoto);

        etFoto.setText(foto);
        etNama.setText(nama);
        etDaerah.setText(daerah);
        etAlamat.setText(alamat);
        etHarga.setText(harga);
        etTipe.setText(tipe);
        etStatus.setText(status);
        etDeskripsi.setText(deskripsi);

        btnUbah = findViewById(R.id.btn_ubah);

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foto = etFoto.getText().toString();
                nama = etNama.getText().toString();
                tipe = etTipe.getText().toString();
                daerah = etDaerah.getText().toString();
                alamat = etAlamat.getText().toString();
                status = etStatus.getText().toString();
                harga = etHarga.getText().toString();
                deskripsi = etDeskripsi.getText().toString();

                if(foto.trim().isEmpty()){
                    etFoto.setError("Foto Kost tidak boleh Kosong");
                }
                else if(nama.trim().isEmpty()){
                    etNama.setError("Nama Kost tidak boleh Kosong");
                }
                else if(tipe.trim().isEmpty()){
                    etTipe.setError("Tipe Kost tidak boleh Kosong");
                }
                else if (daerah.trim().isEmpty()) {
                    etDaerah.setError("Daerah Kost tidak boleh Kosong");
                }
                else if(alamat.trim().isEmpty()){
                    etAlamat.setError("Alamat Kost tidak boleh Kosong");
                }
                else if(harga.trim().isEmpty()){
                    etHarga.setError("Harga Kost tidak boleh Kosong");
                }
                else if(status.trim().isEmpty()){
                    etStatus.setError("Status Kost tidak boleh Kosong");
                }
                else if (deskripsi.trim().isEmpty()) {
                    etDeskripsi.setError("Deskripsi Kost tidak boleh Kosong");
                }
                else{
                    ubahKost(id_kost,nama,foto,tipe,daerah,alamat,status,harga,deskripsi);
                }
            }
        });
    }
    private void ubahKost(String id_kost,String nama_kost , String foto_kost , String tipe_kost, String daerah_kost , String alamat_kost , String status_kost , String harga_kost , String deskripsi_kost){
        RequestData ARD = RetroServer.konekRetrofit().create(RequestData.class);
        Call<ModelResponse> proses = ARD.ardUpdate(id_kost,nama_kost,foto_kost,tipe_kost,daerah_kost,alamat_kost,status_kost,harga_kost,deskripsi_kost);

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();
                Toast.makeText(UbahActivity.this,"Kode: " + kode + " Pesan: " + pesan, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(UbahActivity.this, "Gagal Menghubungi Server" , Toast.LENGTH_SHORT).show();
            }
        });
    }
}