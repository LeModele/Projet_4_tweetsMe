package com.codepath.apps.tweetsme.fragments;

import android.os.Bundle;
import android.util.Log;

import com.codepath.apps.tweetsme.TwitterApplication;
import com.codepath.apps.tweetsme.TwitterClient;
import com.codepath.apps.tweetsme.models.Tweet;

import java.util.List;

public class HomeTimelineFragment extends TweetListFragment {
    private TwitterClient client;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = TwitterApplication.getRestClient();
        populateWithLatestTweets();
    }

    @Override
    public void populateWithLatestTweets() {
        client.getHomeTimeline(new TwitterClient.TimelineResponseHandler() {
            @Override
            public void onSuccess(List<Tweet> tweets) {
                clear();
                addAll(tweets);
                showLatest();
            }

            @Override
            public void onFailure(Throwable error) {
                logError(error);
            }
        });
    }

    @Override
    public void populateWithOlderTweets(Long oldestTweetId) {
        client.getOlderHomeTimeline(new TwitterClient.TimelineResponseHandler() {
            @Override
            public void onSuccess(List<Tweet> tweets) {
                addAll(tweets.isEmpty() ? tweets : tweets.subList(1, tweets.size()));
            }

            @Override
            public void onFailure(Throwable error) {
                logError(error);
            }
        }, oldestTweetId);
    }

    private void logError(Throwable error) {
        Log.d("HOME_TIMELINE", "Failed to retrieve tweets", error);
    }

}
