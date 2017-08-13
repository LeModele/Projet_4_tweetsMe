package com.codepath.apps.tweetsme.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.codepath.apps.tweetsme.R;
import com.codepath.apps.tweetsme.constants.Extras;
import com.codepath.apps.tweetsme.fragments.SearchFragment;

public class SearchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        String query = getIntent().getStringExtra(Extras.QUERY);
        if (savedInstanceState == null) {
            populateSearchResults(query);
        }
    }

    // Methode for the back button

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }

    private void populateSearchResults(String query) {
        SearchFragment fragmentSearch = SearchFragment.newInstance(query);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.flContainer, fragmentSearch);
        ft.commit();
    }

    @Override
    protected void showAuthenticatedUserProfile() {
        Intent intent = new Intent(SearchActivity.this, ProfileActivity.class);
        intent.putExtra(Extras.USER_ID, authenticatedUser.getId());
        startActivity(intent);
    }

    @Override
    protected void showSearchResults(String query) {
        populateSearchResults(query);
    }

    @Override
    protected String getTag() {
        return "SEARCH";
    }

    @Override
    public void showLatestHomeTimelineTweets() {
        startActivity(new Intent(this, TimelineActivity.class));
    }

}
