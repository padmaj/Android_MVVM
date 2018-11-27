package com.anddev.pm.pizzame;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

/**
 * LifeCycleObserver class relies on given lifecycle events
 */
public class AppObserver implements LifecycleObserver {

    private final String TAG = this.getClass().getSimpleName();

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        Log.d(TAG, "onResume called");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        Log.d(TAG, "onPause called");
    }
}
