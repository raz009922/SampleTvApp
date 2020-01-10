package com.example.sampletvapp.repository;

import com.example.sampletvapp.model.DataItem;
import com.example.sampletvapp.model.Token;
import com.example.sampletvapp.model.TvChannel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface Api {
    String BaseUrl = "https://sastaiptv.com/api/";


    @GET("movies")
    Call<TvChannel> listOfDataItem(@Query("number_per_page") int number_per_page,
                                   @Query("page") int page);

    @POST("token")
    Call<Token> getToken(@Query("key") String key);
}
//?key=820783060533
//number_per_page=20&page=1