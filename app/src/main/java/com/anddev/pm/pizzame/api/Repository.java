package com.anddev.pm.pizzame.api;

import android.arch.lifecycle.MutableLiveData;

import com.anddev.pm.pizzame.api.model.Pizza;
import com.anddev.pm.pizzame.api.model.Result;

import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A repository class to send request and update the data
 */
public class Repository {

    private PizzaApi pizzaApi;
    private static Repository repository;

    private Repository() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EndPoints.BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        pizzaApi = retrofit.create(PizzaApi.class);
    }

    /**
     * method to enqueue the api call
     *
     * @param zipCode, string to update the query
     * @return results data
     */
    public MutableLiveData<List<Result>> getPizzas(String zipCode) {
        HttpUrl query = HttpUrl.parse(EndPoints.BASE_URL + EndPoints.VERSION + String.format(EndPoints.QUERY, zipCode));
        final MutableLiveData<List<Result>> data = new MutableLiveData<>();

        pizzaApi.getResult(query.toString()).enqueue(new Callback<Pizza>() {
            @Override
            public void onResponse(Call<Pizza> call, Response<Pizza> response) {

                Pizza pizza = response.body();
                if (pizza == null) {
                    data.setValue(null);
                    return;
                } else if (pizza.query == null) {
                    data.setValue(null);
                    return;
                } else if (pizza.query.results == null) {
                    data.setValue(null);
                    return;
                } else if (pizza.query.results.resultList == null) {
                    data.setValue(null);
                    return;
                }
                data.setValue(pizza.query.results.resultList);
            }

            @Override
            public void onFailure(Call<Pizza> call, Throwable t) {
                t.printStackTrace();
                data.setValue(null);
            }
        });
        return data;
    }

    /**
     * get the instance of Repository class
     *
     * @return repository
     */
    public synchronized static Repository getInstance() {
        if (repository == null) {
            repository = new Repository();
        }
        return repository;
    }
}
