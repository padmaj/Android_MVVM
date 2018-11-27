package com.anddev.pm.pizzame.api;

import com.anddev.pm.pizzame.api.model.Pizza;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * https://query.yahooapis.com/v1/public/yql?q=select * from local.search where zip='78759' and query='pizza'&format=json&diagnostics=true&callback
 */
interface PizzaApi {

    @GET
    Call<Pizza> getResult(@Url String ur);
}
