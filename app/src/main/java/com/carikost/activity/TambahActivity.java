package com.carikost.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.carikost.R;
import com.carikost.api.RequestData;
import com.carikost.api.RetroServer;
import com.carikost.model.ModelResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahActivity extends AppCompatActivity {
    EditText etFoto,etNama,etTipe,etDaerah,etAlamat,etStatus,etHarga,etDeskripsi;
    String foto,nama,daerah,tipe,harga,alamat,status,deskripsi;
    Button btn_tambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etFoto = findViewById(R.id.et_foto_tambah);
        etNama = findViewById(R.id.et_nama_tambah);
        etTipe = findViewById(R.id.et_tipe_tambah);
        etDaerah = findViewById(R.id.et_daerah_tambah);
        etAlamat = findViewById(R.id.et_alamat_tambah);
        etStatus = findViewById(R.id.et_status_tambah);
        etHarga = findViewById(R.id.et_harga_tambah);
        etDeskripsi = findViewById(R.id.et_deskripsi_tambah);

        btn_tambah = findViewById(R.id.btn_tambah);

        btn_tambah.setOnClickListener(new View.OnClickListener() {
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
                    etTipe.setError("Tipe Kost tidak boleh Kosong dan Hanya Di perbolehkan Mengisi (Putra/Putri/Campur)!");
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
                    etStatus.setError("Status Kost tidak boleh Kosong dan Hanya Di perbolehkan Mengisi (Tersedia/Penuh)");
                }
                else if (deskripsi.trim().isEmpty()) {
                    etDeskripsi.setError("Deskripsi Kost tidak boleh Kosong");
                }
                else{
                    tambahKost();
                }
            }
        });
    }
    private void tambahKost(){
        RequestData ARD = RetroServer.konekRetrofit().create(RequestData.class);
        Call<ModelResponse> proses = ARD.ardCreate(nama,foto,tipe,daerah,alamat,status,harga,deskripsi);

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(TambahActivity.this, "Kode: "+ kode + " Pesan: " + pesan
                        , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(TambahActivity.this, "Gagal Menghubungi Server",Toast.LENGTH_SHORT).show();
            }
        });
    }
}