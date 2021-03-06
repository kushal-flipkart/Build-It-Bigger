package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.udacity.gradle.utils.AsyncJokeDownloader;
import com.udacity.gradle.utils.JokeDownloadListener;

import xyz.kushal.jokerlibrary.DisplayJokeActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements JokeDownloadListener {
    ProgressBar spinner;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        spinner = (ProgressBar) root.findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);

        Button tellJokeButton = (Button) root.findViewById(R.id.tell_joke_button);
        tellJokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncJokeDownloader(MainActivityFragment.this).downloadJoke();
                spinner.setVisibility(View.VISIBLE);
            }
        });
        return root;
    }

    @Override
    public void downloadCompleted(String j) {
        spinner.setVisibility(View.GONE);
        Intent mIntent = new Intent(getActivity(), DisplayJokeActivity.class);
        mIntent.putExtra("joke", j);
        startActivity(mIntent);
    }
}
