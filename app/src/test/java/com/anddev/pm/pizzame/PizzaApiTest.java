package com.anddev.pm.pizzame;

import android.arch.lifecycle.MutableLiveData;

import com.anddev.pm.pizzame.api.Repository;
import com.anddev.pm.pizzame.api.model.Result;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PizzaApiTest {

    private Repository repository;

    @Before
    public void before() {
        repository = Repository.getInstance();
    }

    @Test
    public void getResult_withValidZipCode() {
        MutableLiveData<List<Result>> data = repository.getPizzas("78748");
        if (data.getValue() != null) {
            assertEquals(data.getValue().size(), 10);
        }
    }

    @Test
    public void getResult_withInValidZipCode() {
        MutableLiveData<List<Result>> data = repository.getPizzas("000");

        assertNull(data.getValue());
    }

    @Test
    public void getResult_withEmptyZipCode() {
        MutableLiveData<List<Result>> data = repository.getPizzas(" ");

        assertNull(data.getValue());
    }
}
