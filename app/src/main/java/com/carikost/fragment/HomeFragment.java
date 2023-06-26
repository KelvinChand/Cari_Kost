package com.carikost.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.carikost.R;
import com.carikost.activity.TambahActivity;
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

public class HomeFragment extends Fragment {
    private SwipeRefreshLayout swipeRefreshLayout;

    private RecyclerView rvKost;
    private AdapterKost adKost;
    private ProgressBar pbKost;
    private FloatingActionButton fab_tambah;
    private List<ModelKost> listKost;
    private HomeFragment homeFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rvKost = view.findViewById(R.id.rv_kost);
        pbKost = view.findViewById(R.id.pb_kost);
        fab_tambah = view.findViewById(R.id.fab_add);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.dark_blue, R.color.dark_green);
        rvKost.setLayoutManager(new LinearLayoutManager(requireContext()));
        retrieveKost();

        fab_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isAdded()) {
                    startActivity(new Intent(requireContext(),TambahActivity.class));
                }
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                retrieveKost();
            }
        });
        return view;
    }
    public void onResume(){
        super.onResume();
        retrieveKost();
    }
    public void retrieveKost() {

        RequestData ARD = RetroServer.konekRetrofit().create(RequestData.class);
        Call<ModelResponse> proses = ARD.ardRetrieve();
        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                if(isAdded()) {
                    swipeRefreshLayout.setRefreshing(true);
                    pbKost.setVisibility(View.VISIBLE);
                    String kode = response.body().getKode();
                    String pesan = response.body().getPesan();
                    listKost = response.body().getData();
                    adKost = new AdapterKost(requireContext(), listKost , homeFragment);
                    rvKost.setAdapter(adKost);
                    adKost.setData(listKost);
                    Toast.makeText(requireContext(), "Kode: " + kode + " Pesan: " + pesan, Toast.LENGTH_SHORT).show();
                    pbKost.setVisibility(View.GONE);
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                if(isAdded()) {
                    Toast.makeText(requireContext(), "Gagal Menghubungi Server" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    pbKost.setVisibility(View.GONE);
                }
            }
        });
    }
}