package com.anddev.pm.pizzame.view.activity;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.anddev.pm.pizzame.AppObserver;
import com.anddev.pm.pizzame.R;
import com.anddev.pm.pizzame.Utils;
import com.anddev.pm.pizzame.databinding.HomeBinding;
import com.anddev.pm.pizzame.view.adapter.PizzaListAdapter;
import com.anddev.pm.pizzame.view.listener.RecyclerViewItemClickListener;
import com.anddev.pm.pizzame.viewModel.ResultsViewModel;
import com.anddev.pm.pizzame.viewModel.ResultsViewModelFactory;

import java.io.IOException;

/**
 * Home page activity
 */
public class HomeActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private HomeBinding binding;
    private PizzaListAdapter recyclerViewAdapter;
    private ResultsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.home);

        //add this activity to the lifecycle observer
        getLifecycle().addObserver(new AppObserver());
        viewModel = ViewModelProviders.of(this, new ResultsViewModelFactory()).get(ResultsViewModel.class);

        binding.swipeRefreshLayout.setOnRefreshListener(this);
        binding.swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);

        //set adapter to recyclerView and onItemClickListener
        RecyclerViewItemClickListener clickListener = (result) ->
                startActivity(DetailActivity.getDetailIntent(this, result, viewModel.getZipCode()));
        recyclerViewAdapter = new PizzaListAdapter(viewModel, clickListener);
        binding.rvPizza.setAdapter(recyclerViewAdapter);

        //set OnEditorActionListener when user try to search for new zipCode
        binding.etZipCode.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            hideKeyboard();
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if(!binding.etZipCode.getText().toString().equals(viewModel.getZipCode())) {
                    viewModel.setZipCode(binding.etZipCode.getText().toString());
                    loadData();
                }
                return true;
            }
            return false;
        });
        loadData();
    }

    /**
     * Checks LocationPermission and network status, then shows progress, get the zipCode and call observeData()
     */
    private void loadData() {
        //check location permission to get the user's current location
        if (Utils.checkLocationPermission(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                Utils.APP_PERMISSIONS_REQUEST_LOCATION)) {

            if (Utils.isNetworkConnected(this)) {
                binding.swipeRefreshLayout.setRefreshing(true);
                String zip = viewModel.getZipCode();
                if (zip.isEmpty()) {
                    try {
                        zip = Utils.getCurrentZipCode(this);
                        viewModel.setZipCode(zip);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                observeData(viewModel);
            } else {
                binding.swipeRefreshLayout.setRefreshing(false);
                Utils.showAlertDialog(this, getString(R.string.connect_to_internet_message));
            }
        }
    }

    /**
     * method to observe change in data
     *
     * @param viewModel
     */
    private void observeData(ResultsViewModel viewModel) {
        // Update the list when the data changes
        viewModel.getResult().observe(this, results -> {
            binding.swipeRefreshLayout.setRefreshing(false);
            if (results != null) {
                recyclerViewAdapter.setResultsList(results);
            } else {
                Toast.makeText(this, R.string.results_null_message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * method to refresh the data using swipeRefreshLayout when user swipes down on the screen
     */
    @Override
    public void onRefresh() {
        loadData();
    }

    /**
     * hide the keyboard while starting the activity
     */
    private void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(binding.etZipCode.getApplicationWindowToken(), 0);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Utils.APP_PERMISSIONS_REQUEST_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadData();
            } else {
                binding.swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(this, R.string.no_permission_message, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
