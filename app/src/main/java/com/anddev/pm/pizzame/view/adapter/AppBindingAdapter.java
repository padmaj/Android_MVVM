package com.anddev.pm.pizzame.view.adapter;

import android.databinding.BindingAdapter;
import android.util.Log;
import android.widget.RatingBar;

/**
 * Custom binding adapter class
 */
public class AppBindingAdapter {

    private static final String TAG = "AppBindingAdapter";

    @BindingAdapter("rating")
    public static void setRating(RatingBar view, String rating) {
        float ratingValue = 0.0f;
        try {
            ratingValue = Float.parseFloat(rating);
        } catch (NumberFormatException e) {
            Log.e(TAG, e.getMessage());
        }
        view.setRating(ratingValue);
    }
}
