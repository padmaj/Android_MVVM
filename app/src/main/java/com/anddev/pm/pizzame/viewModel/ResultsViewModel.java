package com.anddev.pm.pizzame.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.anddev.pm.pizzame.api.Repository;
import com.anddev.pm.pizzame.api.model.Result;

import java.util.List;

/**
 * A view model class to hold the data and get the results
 */
public class ResultsViewModel extends ViewModel {

    public MutableLiveData<List<Result>> resultsLiveData;
    public String zipCode = "";

    public LiveData<List<Result>> getResult() {
        if (resultsLiveData == null) {
            resultsLiveData = new MutableLiveData<>();
        }
        resultsLiveData = Repository.getInstance().getPizzas(zipCode);
        return resultsLiveData;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
