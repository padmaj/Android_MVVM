package com.anddev.pm.pizzame.activity;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import com.anddev.pm.pizzame.SampleResults;
import com.anddev.pm.pizzame.api.model.Result;
import com.anddev.pm.pizzame.view.activity.DetailActivity;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class ResultTest {

    @Test
    public void checkResult_isParcelable() {
        Result sampleResult = SampleResults.getResult();

        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Intent intent = new Intent(targetContext, DetailActivity.class);
        intent.putExtra("EXTRA_RESULT", sampleResult);

        Result newResult = intent.getParcelableExtra("EXTRA_RESULT");
        assertEquals(newResult.id, sampleResult.id);
    }
}
