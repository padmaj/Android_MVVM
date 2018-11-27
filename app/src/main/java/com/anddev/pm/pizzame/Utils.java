package com.anddev.pm.pizzame;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.RatingBar;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * utility class
 */
public class Utils {

    public static final int APP_PERMISSIONS_REQUEST_LOCATION = 1001;
    private static final int MAC_LOCATIONS = 1;

    /**
     * get current zipCode based user's current location
     *
     * @param context
     * @return String, postalCode
     * @throws IOException
     */
    public static String getCurrentZipCode(Context context) throws IOException {
        String postalCode = "";
        if (context != null) {
            Location currentLocation = getCurrentLocation(context);
            if (currentLocation != null) {
                Geocoder geocoder;
                geocoder = new Geocoder(context, Locale.getDefault());
                List<Address> addresses = geocoder.getFromLocation(currentLocation.getLatitude(),
                        currentLocation.getLongitude(),
                        MAC_LOCATIONS);
                postalCode = addresses.get(0).getPostalCode();
            }
        }
        return postalCode;
    }

    /**
     * get user's current location if the location permission is granted
     *
     * @param context
     * @return Location, users' current location
     */
    public static Location getCurrentLocation(Context context) {
        Location currentLocation = null;
        if (context != null) {
            LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            if (manager != null && checkLocationPermission(context,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION}, APP_PERMISSIONS_REQUEST_LOCATION)) {
                currentLocation = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }
        }
        return currentLocation;
    }

    /**
     * check whether location permission is granted, if not request permission
     *
     * @param context
     * @param permissions permissions to request
     * @param requestCode request code to match with a result
     * @return
     */
    public static boolean checkLocationPermission(Context context, String[] permissions, int requestCode) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(context, permissions, requestCode);
            return false;
        }
        return true;
    }

    /**
     * update progress drawable on ratingBar for api less than 21
     *
     * @param view ratingBar view
     */
    public static void updateRatingProgressDrawable(RatingBar view) {
        LayerDrawable stars = (LayerDrawable) view.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(ContextCompat.getColor(view.getContext(), R.color.colorPrimary),
                PorterDuff.Mode.SRC_ATOP); // for filled stars
        stars.getDrawable(1).setColorFilter(ContextCompat.getColor(view.getContext(), R.color.colorPrimary),
                PorterDuff.Mode.SRC_ATOP); // for half filled stars
        stars.getDrawable(0).setColorFilter(ContextCompat.getColor(view.getContext(), R.color.light_gray),
                PorterDuff.Mode.SRC_ATOP); // for empty stars
    }

    /**
     * method to request permissions
     *
     * @param context
     * @param permissions permissions to request
     * @param requestCode request code to match with a result
     */
    public static void requestPermissions(Context context, String[] permissions, int requestCode) {
        ActivityCompat.requestPermissions((Activity) context, permissions, requestCode);
    }

}
