package com.example.navigation_drawer.formattedadress;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface api  {

    @GET("json?q=33.652187+73.156751&key=61d2f365245049c585906f0b11ead03e")
    Call<Formattedadress> getdata();

    @GET("json")
    Call<Formattedadress> getdynamicdata(@Query("q") String user,
                                         @Query("key") String key);

    @GET
    Call<Formattedadress> getfulldata(@Url String url);
}
