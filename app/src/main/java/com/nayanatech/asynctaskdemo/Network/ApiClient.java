package com.nayanatech.asynctaskdemo.Network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static com.nayanatech.asynctaskdemo.Network.Api.BASE_URL;

public class ApiClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(){
        //interceptor
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //okhttpclient
        OkHttpClient client=new OkHttpClient.Builder().addInterceptor(interceptor).build();

        //retrofit client and add okhttpclient to retrofit
        retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(client).build();

        //returns retrofit client
        return retrofit;
    }
}
