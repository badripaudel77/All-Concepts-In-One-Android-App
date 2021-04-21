package com.manav.allinoneandroidapp.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//docs : https://www.androidhive.info/2016/05/android-working-with-retrofit-http-library/
//docs : https://www.androidauthority.com/retrofit-android-tutorial-906928/

public class RetrofitClientInstance {
    private static Retrofit retrofit;

    private static final String baseURL = "https://jsonplaceholder.typicode.com/";

    public static Retrofit getRetrofitInstance() {

      if(retrofit == null) {
          retrofit = new Retrofit.Builder()
                  .baseUrl(baseURL)
                  .addConverterFactory(GsonConverterFactory.create())
                  .build();
      }
      return retrofit;
   }
}
