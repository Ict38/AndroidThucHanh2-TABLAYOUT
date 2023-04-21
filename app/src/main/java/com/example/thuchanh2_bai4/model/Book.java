package com.example.thuchanh2_bai4.model;

import java.io.Serializable;

public class Book implements Serializable {
    private int id;
    private String ten,tacgia, phamvi, doituong;
    private Float rating;

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public String getPhamvi() {
        return phamvi;
    }

    public void setPhamvi(String phamvi) {
        this.phamvi = phamvi;
    }

    public String getDoituong() {
        return doituong;
    }

    public void setDoituong(String doituong) {
        this.doituong = doituong;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Book(String ten, String tacgia, String phamvi, String doituong, Float rating) {
        this.ten = ten;
        this.tacgia = tacgia;
        this.phamvi = phamvi;
        this.doituong = doituong;
        this.rating = rating;
    }

    public Book(int id, String ten, String tacgia, String phamvi, String doituong, Float rating) {
        this.id = id;
        this.ten = ten;
        this.tacgia = tacgia;
        this.phamvi = phamvi;
        this.doituong = doituong;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", ten='" + ten + '\'' +
                ", tacgia='" + tacgia + '\'' +
                ", phamvi='" + phamvi + '\'' +
                ", doituong='" + doituong + '\'' +
                ", rating=" + rating +
                '}';
    }
}
