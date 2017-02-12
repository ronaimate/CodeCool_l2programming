package com.example.n.retrofit;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;


interface MovieAPI {
    String BASE_URL = "http://www.omdbapi.com/";

    @GET("?t=Matrix&y=&plot=short&r=json")
    Call<Movie> getMovie();

    class Factory {
        private static MovieAPI service;

        public static MovieAPI getInstance() {
            if (service == null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(BASE_URL)
                        .build();
                service = retrofit.create(MovieAPI.class);
                return service;
            } else {
                return service;
            }
        }
    }
}
