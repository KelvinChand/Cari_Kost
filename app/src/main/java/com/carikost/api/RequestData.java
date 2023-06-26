package com.carikost.api;

import com.carikost.model.ModelResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RequestData {
    @GET("retrieve.php")
    Call<ModelResponse> ardRetrieve();

    @FormUrlEncoded
    @POST("detail.php")
    Call<ModelResponse> ardDetail(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST("create.php")
    Call<ModelResponse> ardCreate(
            @Field("nama_kost") String nama,
            @Field("foto_kost") String foto,
            @Field("tipe_kost") String tipe,
            @Field("daerah_kost") String daerah,
            @Field("alamat_kost") String alamat,
            @Field("status_kost") String status,
            @Field("harga_kost") String harga,
            @Field("deskripsi_kost") String deskripsi
    );
    @FormUrlEncoded
    @POST("update.php")
    Call<ModelResponse> ardUpdate(
            @Field("id") String id,
            @Field("nama_kost") String nama,
            @Field("foto_kost") String foto,
            @Field("tipe_kost") String tipe,
            @Field("daerah_kost") String daerah,
            @Field("alamat_kost") String alamat,
            @Field("status_kost") String status,
            @Field("harga_kost") String harga,
            @Field("deskripsi_kost") String deskripsi
    );
    @FormUrlEncoded
    @POST("delete.php")
    Call<ModelResponse> ardDelete(
            @Field("id") String id
    );
}
