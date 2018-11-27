package com.anddev.pm.pizzame.view.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.anddev.pm.pizzame.AppObserver;
import com.anddev.pm.pizzame.R;
import com.anddev.pm.pizzame.Utils;
import com.anddev.pm.pizzame.api.model.Result;
import com.anddev.pm.pizzame.databinding.DetailBinding;

/**
 * Detail page activity
 */
public class DetailActivity extends AppCompatActivity {

    private static final String INTENT_EXTRA_DETAIL_RESULT = "pizzame.detail.result";
    private static final String INTENT_EXTRA_DETAIL_ZIPCODE = "pizzame.detail.zipcode";

    private DetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.detail);

        //add this activity to the lifecycle observer
        getLifecycle().addObserver(new AppObserver());

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Utils.updateRatingProgressDrawable(binding.rbRating);
        }

        Result result = getIntent().getParcelableExtra(INTENT_EXTRA_DETAIL_RESULT);
        String zipCode = getIntent().getStringExtra(INTENT_EXTRA_DETAIL_ZIPCODE);
        if (result != null) {
            binding.setResult(result);
            binding.setZipCode(zipCode != null ? zipCode : "");
        }
    }

    /**
     * open the maps when user clicked on the view(R.id.maps)
     *
     * @param view
     */
    public void onMapClicked(View view) {
        Location currentLocation = Utils.getCurrentLocation(this);
        if (currentLocation == null || binding.getResult() == null) {
            Toast.makeText(this, R.string.open_maps_error_message, Toast.LENGTH_SHORT).show();
            return;
        }
        Intent mapIntent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse(String.format(getString(R.string.detail_maps_uri),
                        String.valueOf(currentLocation.getLatitude()),
                        String.valueOf(currentLocation.getLongitude()),
                        binding.getResult().latitude,
                        binding.getResult().longitude)));
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            Toast.makeText(this, R.string.open_maps_error_message, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * open the device phone screen to make a call when user clicked on the view(R.id.phone)
     *
     * @param view
     */
    public void onPhoneClicked(View view) {
        if (binding.getResult() == null) {
            Toast.makeText(this, R.string.open_phone_error_message, Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + binding.getResult().phone));
        startActivity(intent);
    }

    /**
     * get the intent to start DetailActivity
     *
     * @param context source context
     * @param result result to update the views in detail page
     * @param zipCode zipcode to update the address line in detail page
     *
     * @return intent to start Detail/activity
     */
    public static Intent getDetailIntent(Context context, Result result, String zipCode) {
        Intent detailIntent = new Intent(context, DetailActivity.class);
        detailIntent.putExtra(INTENT_EXTRA_DETAIL_RESULT, result);
        detailIntent.putExtra(INTENT_EXTRA_DETAIL_ZIPCODE, zipCode);
        return detailIntent;
    }
}
