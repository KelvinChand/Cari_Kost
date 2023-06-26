package com.carikost.model;

import java.util.List;

public class ModelResponse {
    private String kode,pesan;
    private List<ModelKost> data;

    public String getKode() {
        return kode;
    }

    public String getPesan() {
        return pesan;
    }

    public List<ModelKost> getData() {
        return data;
    }
}
