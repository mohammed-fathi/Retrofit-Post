package com.example.ktapelwesh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getdata();
        getDinamicData();
        postData();
        postDataMap();
    }
    public void getdata(){
        TextView posttitelTV = findViewById(R.id.post_titel_tv);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<List<Post>> call = apiInterface.getPost("1");
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                posttitelTV.setText(response.body().get(0).getTitle());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                posttitelTV.setText(t.getMessage());
            }
        });
    }

    public void getDinamicData(){
        TextView dinamicPostTitelTV = findViewById(R.id.post_titel_tv_dinamic);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<Post> call = apiInterface.getDinamicPost(1);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                dinamicPostTitelTV.setText(response.body().getBody());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                dinamicPostTitelTV.setText(t.getMessage());
            }
        });
    }

    public void postData(){
        Post post = new Post(3 , "I am Android Programer" , "hi, this is my first post");
        TextView storePostTV = findViewById(R.id.store_post_TV);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<Post> call = apiInterface.storePost(post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                storePostTV.setText(response.body().getTitle());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                storePostTV.setText(t.getMessage());
            }
        });
    }
    public void postDataMap(){
        TextView mapStorePostTV = findViewById(R.id.map_store_post_TV);
        HashMap<Object,Object> map = new HashMap<>();
        map.put("title","Mohammed fathi");
        map.put("body" , "hi , this is my second post");
        map.put("userId" , 4);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<Post> call = apiInterface.mapStorePost(map);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                mapStorePostTV.setText(response.body().getBody());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                mapStorePostTV.setText(t.getMessage());
            }
        });

    }
}