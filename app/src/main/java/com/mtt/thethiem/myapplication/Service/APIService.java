package com.mtt.thethiem.myapplication.Service;

public class APIService {

    private static String base_url = "https://thethiemvip97.000webhostapp.com/Server/";

    public static Dataservice getService(){
        return APIRetrofitClient.getClient(base_url).create(Dataservice.class);
    }
}
