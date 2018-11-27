package com.anddev.pm.pizzame.viewModel;

import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

/**
 * A factory class to provide ResultsViewModel
 */
public class ResultsViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    @SuppressWarnings("uncheckd")
    public ResultsViewModel create(@NonNull Class modelClass) {
        return new ResultsViewModel();
    }
}
