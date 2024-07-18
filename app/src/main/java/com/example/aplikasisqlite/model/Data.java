package com.example.aplikasisqlite.model;

public class Data {
    private String id, name, tgl, jenis, address;

    public Data() {
    }

    public Data(String id, String name, String tgl, String jenis, String address) {
        this.id = id;
        this.name = name;
        this.tgl = tgl;
        this.jenis = jenis;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return name;
    }

    public void setNama(String nama) {
        this.name = nama;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getAlamat() {
        return address;
    }

    public void setAlamat(String address) {
        this.address = address;
    }
}
