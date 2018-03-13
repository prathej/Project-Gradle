package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;
import android.util.Log;

import com.udacity.gradle.builditbigger.utilities.GetJokeAsyncTask;

import junit.framework.Assert;

import java.util.concurrent.TimeUnit;


public class GetJokeAsyncTaskTesting extends AndroidTestCase {

    private static String LOG_TAG = GetJokeAsyncTaskTesting.class.getSimpleName();


    public void testJokeDownload() {

        try {
            GetJokeAsyncTask task = new GetJokeAsyncTask();
            task.execute();
            String joke = task.get(30, TimeUnit.SECONDS);
            Log.d(LOG_TAG, "non empty joke string is " + joke);
            Assert.assertNotNull(joke);
            assertTrue(joke.length() > 0);

        } catch (Exception e) {
            fail("Operation timed out");
        }
    }

}
