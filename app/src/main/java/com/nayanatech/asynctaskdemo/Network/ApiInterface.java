package com.nayanatech.asynctaskdemo.Network;

import com.nayanatech.asynctaskdemo.model.UnknownModel;
import com.nayanatech.asynctaskdemo.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import static com.nayanatech.asynctaskdemo.Network.Api.UNKNOWN;
import static com.nayanatech.asynctaskdemo.Network.Api.USERS;

public interface ApiInterface {

    @GET(UNKNOWN)
    Call<UnknownModel> getUnknownList();

    @POST(USERS)
    Call<User> createUser(@Body User user);

}
