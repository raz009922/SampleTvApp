package com.example.sampletvapp.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.sampletvapp.model.Token;
import com.example.sampletvapp.model.TvChannel;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

public class Repository {
    private static final Repository ourInstance = new Repository();

    public static Repository getInstance() {
        return ourInstance;
    }

    private Repository() {

    }

    public void getToken(MutableLiveData<Token> tokenMutableLiveData) {

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Api.BaseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                Api api = retrofit.create(Api.class);
                Call<Token> call = api.getToken("820783060533");
                call.enqueue(new Callback<Token>() {
                    @Override
                    public void onResponse(Call<Token> call, Response<Token> response) {

                        Log.d(TAG, "onResponse:  " + response.body().getAccessToken());
                        tokenMutableLiveData.postValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<Token> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + t);
                    }
                });

            }
        };
        runnable.run();
    }


    public void getDataItem(String accessToken, MutableLiveData<TvChannel> liveDataDataItem,
                            int currentPage, int pageSize) {
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
/*                Gson gson = new GsonBuilder()
                        .setLenient()
                        .create();*/


                OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request newRequest = chain.request().newBuilder()
                                .addHeader("Authorization", "Bearer " + accessToken)

                                .build();
                        Log.d(TAG, "intercept: " + accessToken);
                        return chain.proceed(newRequest);
                    }
                }).build();
                Retrofit retrofit = new Retrofit.Builder()
                        .client(client)
                        .baseUrl(Api.BaseUrl)
                        .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                        .build();


                Api api = retrofit.create(Api.class);


                Call<TvChannel> call = api.listOfDataItem(pageSize, currentPage);
                call.enqueue(new Callback<TvChannel>() {
                    @Override
                    public void onResponse(Call<TvChannel> call, Response<TvChannel> response) {
                        Log.d(TAG, "onResponse: " + response.body().getData().size());
                        Log.d(TAG, "onResponse: " + response.body().getTotalPage());
                        if (liveDataDataItem.getValue() != null) {
                            TvChannel tvChannel = liveDataDataItem.getValue();
                            tvChannel.getData().addAll(response.body().getData());
                            liveDataDataItem.postValue(tvChannel);

                        } else {
                            liveDataDataItem.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<TvChannel> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + t + "getResponse");

                    }
                });
            }
        };
        runnable.run();


    }
}
