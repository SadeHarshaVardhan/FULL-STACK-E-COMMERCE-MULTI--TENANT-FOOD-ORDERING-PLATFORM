package com.example.demo;

public class Tea {
    private int teaid;
    private String teaname;
    private String tealink;
    private int teaprice;
    private String teadet;

    public Tea(int teaid, String teaname, String tealink, int teaprice, String teadet) {
        this.teaid = teaid;
        this.teaname = teaname;
        this.tealink = tealink;
        this.teaprice = teaprice;
        this.teadet = teadet;
    }

    public int getTeaid() {
        return teaid;
    }

    public void setTeaid(int teaid) {
        this.teaid = teaid;
    }

    public String getTeaname() {
        return teaname;
    }

    public void setTeaname(String teaname) {
        this.teaname = teaname;
    }

    public String getTealink() {
        return tealink;
    }

    public void setTealink(String tealink) {
        this.tealink = tealink;
    }

    public int getTeaprice() {
        return teaprice;
    }

    public void setTeaprice(int teaprice) {
        this.teaprice = teaprice;
    }

    public String getTeadet() {
        return teadet;
    }

    public void setTeadet(String teadet) {
        this.teadet = teadet;
    }
}
