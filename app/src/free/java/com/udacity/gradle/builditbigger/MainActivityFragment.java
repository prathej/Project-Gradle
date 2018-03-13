package com.udacity.gradle.builditbigger;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.ravitheja.jokedisplay.JokeTeller;
import com.ravitheja.joker.MrJoker;
import com.udacity.gradle.builditbigger.utilities.GetJokeAsyncTask;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    Button mButton;
    ProgressBar mProgressBar;

    GetJokeAsyncTask mGetJoke;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mButton = root.findViewById(R.id.jokeButton);
        mProgressBar = root.findViewById(R.id.progressBar1);
        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        final MrJoker myJoker = new MrJoker();
        mButton.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View view) {
                //Toast.makeText(getActivity(), myJoker.getJoke(), Toast.LENGTH_LONG).show();
            /*    String jokeText = myJoker.getJoke();
                Intent intent = new Intent(getActivity(),JokeTeller.class);
                intent.putExtra(JokeTeller.RETRIEVED_TEXT, jokeText);
                startActivity(intent);*/

                mProgressBar.setVisibility(View.VISIBLE);

                 new GetJokeAsyncTask() {
                    @Override
                    protected void onPostExecute(String s) {
                        if (s != null) {
                            Intent intent = new Intent(getActivity(),JokeTeller.class);
                            intent.putExtra(JokeTeller.RETRIEVED_TEXT, s);
                            startActivity(intent);
                            mProgressBar.setVisibility(View.INVISIBLE);
                        } else {

                            Toast.makeText(getContext(), "Error while retrieving joke. This is no joke!", Toast.LENGTH_LONG).show();
                        }
                    }
                }.execute();
            }

        });
        return root;
    }
}
