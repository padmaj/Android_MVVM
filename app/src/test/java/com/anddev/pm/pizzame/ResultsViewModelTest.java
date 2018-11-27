package com.anddev.pm.pizzame;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;

import com.anddev.pm.pizzame.api.Repository;
import com.anddev.pm.pizzame.api.model.Result;
import com.anddev.pm.pizzame.viewModel.ResultsViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
public class ResultsViewModelTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    private ResultsViewModel mockViewModel;
    private ResultsViewModel viewModel;
    private Repository repository;

    @Before
    public void before() {
        repository = Repository.getInstance();
        mockViewModel = mock(ResultsViewModel.class);
        viewModel = new ResultsViewModel();
    }

    @Test
    public void zipCode() {
        when(mockViewModel.getZipCode()).thenReturn("78748");

        assertEquals(mockViewModel.getZipCode(), "78748");
        assertNotEquals(mockViewModel.getZipCode(), "00000");
    }

    @Test
    public void getResults_mockData() {
        List<Result> mockData = MockResults.getResultList();
        LiveData<List<Result>> liveMockData = new MutableLiveData<>();
        ((MutableLiveData<List<Result>>) liveMockData).setValue(mockData);

        LiveData<List<Result>> emptyData = new MutableLiveData<>();

        when(mockViewModel.getResult()).thenReturn(liveMockData);

        assertEquals(mockViewModel.getResult(), liveMockData);
        assertNotEquals(mockViewModel.getResult(), emptyData);
    }

    @Test
    public void getResults_validZipCodeData() {
        Observer<List<Result>> observer = (Observer<List<Result>>) mock(Observer.class);
        viewModel.resultsLiveData = new MutableLiveData<>();
        viewModel.resultsLiveData.observeForever(observer);

        viewModel.setZipCode("78748");
        viewModel.getResult();

        verify(observer).equals(viewModel.resultsLiveData.getValue());

        List<Result> emptyData = MockResults.getResultList();
        MutableLiveData<List<Result>> data = repository.getPizzas("78478");

        assertEquals(mockViewModel.resultsLiveData, data.getValue());
        assertNotEquals(mockViewModel.getResult(), emptyData);
    }
}
