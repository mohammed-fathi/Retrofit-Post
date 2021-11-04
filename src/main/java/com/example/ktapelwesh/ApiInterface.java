package com.example.ktapelwesh;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET ("posts")
    public Call<List<Post>> getPost(@Query("userId") String userId);

    @GET("posts/{id}")
    public Call<Post> getDinamicPost(@Path("id") int postId);

    @POST("posts")
    public Call<Post> storePost(@Body Post post);

    @POST("posts")
    public Call<Post> mapStorePost(@Body HashMap<Object,Object> map);
}
