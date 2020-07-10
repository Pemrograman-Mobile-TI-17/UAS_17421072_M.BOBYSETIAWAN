package com.mbobys.apotekmbs.server;

public class BaseURL {

    public static String baseUrl ="http://192.168.43.149:5050/";
    public static String login = baseUrl + "user/login";
    public static String regis = baseUrl + "user/registrasi";

    //buku
    public static String dataObat = baseUrl + "obat/dataobat";
    public static String editDataObat = baseUrl + "obat/ubah/";
    public static String hapusData = baseUrl + "obat/hapus/";
    public static String inputObat = baseUrl + "obat/input";
}
