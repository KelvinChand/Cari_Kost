package com.carikost.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.carikost.R;
import com.carikost.activity.DetailActivity;
import com.carikost.activity.UbahActivity;
import com.carikost.api.RequestData;
import com.carikost.api.RetroServer;
import com.carikost.fragment.HomeFragment;
import com.carikost.model.ModelKost;
import com.carikost.model.ModelResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterKost extends RecyclerView.Adapter<AdapterKost.VHKost> {

    private Context ctx;
    private HomeFragment fragment;

    private List<ModelKost> itemList;
    private int clickCount;

    public AdapterKost(Context ctx, List<ModelKost> itemList , HomeFragment fragment) {
        this.ctx = ctx;
        this.itemList = itemList;
        this.fragment = fragment;
    }
    public void setData(List<ModelKost> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public VHKost onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_kost,parent,false);
        return new VHKost(varView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterKost.VHKost holder, int position) {
        ModelKost MK = itemList.get(position);
        holder.ivFavorite.setImageResource(R.drawable.baseline_favorite_border_24);

        holder.ivFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCount++;
                if (clickCount % 2 == 0) {
                    holder.ivFavorite.setImageResource(R.drawable.baseline_favorite_border_24);
                } else {
                    holder.ivFavorite.setImageResource(R.drawable.baseline_favorite_24);
                }
            }
        });
        holder.bindData(MK);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class VHKost extends RecyclerView.ViewHolder {
        ImageView ivFoto,ivFavorite;
        TextView tvId,tvFoto,tvNama,tvTipe,tvDaerah,tvAlamat,tvStatus,tvHarga,tvDeskripsi;
        Button btn_detail;

        public VHKost(@NonNull View itemView) {
            super(itemView);
            ivFavorite=itemView.findViewById(R.id.iv_favorite_default);
            btn_detail = itemView.findViewById(R.id.detail_btn);
            ivFoto = itemView.findViewById(R.id.image_kost);
            tvFoto = itemView.findViewById(R.id.tv_foto);
            tvId = itemView.findViewById(R.id.tv_id_kost);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvTipe = itemView.findViewById(R.id.tv_tipe);
            tvDaerah = itemView.findViewById(R.id.tv_daerah);
            tvAlamat = itemView.findViewById(R.id.tv_alamat);
            tvStatus = itemView.findViewById(R.id.tv_status);
            tvHarga = itemView.findViewById(R.id.tv_harga);
            tvDeskripsi = itemView.findViewById(R.id.tv_deskripsi);

            btn_detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent Detail = new Intent(ctx, DetailActivity.class);
                    Detail.putExtra("xId", tvId.getText().toString());
                    Detail.putExtra("xFoto", tvFoto.getText().toString());
                    Detail.putExtra("xNama", tvNama.getText().toString());
                    Detail.putExtra("xTipe", tvTipe.getText().toString());
                    Detail.putExtra("xDaerah", tvDaerah.getText().toString());
                    Detail.putExtra("xAlamat", tvAlamat.getText().toString());
                    Detail.putExtra("xStatus",tvStatus.getText().toString());
                    Detail.putExtra("xHarga",tvHarga.getText().toString());
                    Detail.putExtra("xDeskripsi", tvDeskripsi.getText().toString());
                    ctx.startActivity(Detail);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder pesan = new AlertDialog.Builder(ctx);
                    pesan.setTitle("Perhatian");
                    pesan.setMessage("Operasi apa yang akan dilakukan?");
                    pesan.setCancelable(true);

                    pesan.setNegativeButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            hapusKost(tvId.getText().toString());
                            dialog.dismiss();
                        }
                    });

                    pesan.setPositiveButton("Ubah", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent pindah = new Intent(ctx, UbahActivity.class);
                            pindah.putExtra("xId", tvId.getText().toString());
                            pindah.putExtra("xFoto", tvFoto.getText().toString());
                            pindah.putExtra("xNama", tvNama.getText().toString());
                            pindah.putExtra("xTipe", tvTipe.getText().toString());
                            pindah.putExtra("xDaerah", tvDaerah.getText().toString());
                            pindah.putExtra("xAlamat", tvAlamat.getText().toString());
                            pindah.putExtra("xStatus",tvStatus.getText().toString());
                            pindah.putExtra("xHarga",tvHarga.getText().toString());
                            pindah.putExtra("xDeskripsi", tvDeskripsi.getText().toString());

                            ctx.startActivity(pindah);
                        }
                    });
                    pesan.show();
                    return false;
                }
            });
        }
        public void bindData(ModelKost modelKost){
            Glide.with(itemView)
                    .load(modelKost.getFoto_kost())
                    .into(ivFoto);
            tvId.setText(modelKost.getId());
            tvFoto.setText(modelKost.getFoto_kost());
            tvNama.setText(modelKost.getNama_kost());
            tvTipe.setText(modelKost.getTipe_kost());
            tvDaerah.setText(modelKost.getDaerah_kost());
            tvAlamat.setText(modelKost.getAlamat_kost());
            tvStatus.setText(modelKost.getStatus_kost());
            tvHarga.setText(modelKost.getHarga_kost());
            tvDeskripsi.setText(modelKost.getDeskripsi_kost());

        }

    }
    public void hapusKost(String id){
        RequestData ARD = RetroServer.konekRetrofit().create(RequestData.class);
        Call<ModelResponse> proses = ARD.ardDelete(id);

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();
                Toast.makeText(ctx, "Kode: " + kode + "Pesan: " + pesan, Toast.LENGTH_SHORT).show();
                if (fragment != null) {
                    fragment.retrieveKost();
                }
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(ctx, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

