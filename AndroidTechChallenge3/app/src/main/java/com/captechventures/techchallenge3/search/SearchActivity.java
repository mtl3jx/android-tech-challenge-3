package com.captechventures.techchallenge3.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.captechventures.techchallenge3.database.ZipCodeDataSource;
import com.captechventures.techchallenge3.R;
import com.captechventures.techchallenge3.search.results.SearchResultTableActivity;

public class SearchActivity extends AppCompatActivity {

    public ZipCodeDataSource zipCodeDataSource;
    private EditText searchZip, searchLocality;
    private Button buttonSearch;

    // tag for logging purposes
    private static final String TAG = SearchActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // set up view variables for activity
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        TextView progressMsg = (TextView) findViewById(R.id.progressMsg);
        LinearLayout searchForm = (LinearLayout) findViewById(R.id.search_form);
        searchZip = (EditText) findViewById(R.id.search_zip);
        searchLocality = (EditText) findViewById(R.id.search_locality);

        // set up database
        zipCodeDataSource = new ZipCodeDataSource(this);
        zipCodeDataSource.open();

        // make search button respond to clicking
        buttonSearch = (Button) findViewById(R.id.button_search);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Search button was clicked!");

                // grab values of EditText
                String zipSearchToken = searchZip.getText().toString().trim();
                String localitySearchToken = searchLocality.getText().toString().trim();

                // validate that something was entered in search field
                if (zipSearchToken.isEmpty() && localitySearchToken.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "You must enter a search token!", Toast.LENGTH_SHORT).show();
                } else if (zipSearchToken.length() > 5) {
                    Toast.makeText(getApplicationContext(), "Valid zip codes are 5 digits long!", Toast.LENGTH_SHORT).show();
                } else {
                    // start new SearchResultTableActivity and pass search tokens
                    Bundle bundle = new Bundle();
                    bundle.putString("zip_search", zipSearchToken);
                    bundle.putString("locality_search", localitySearchToken);

                    // pass search tokens to SearchResultTableActivity via bundle
                    Intent intent = new Intent(SearchActivity.this, SearchResultTableActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

            }
        });

        // TODO: implement this view update in onViewCreated using Fragment (in a later tech challenge)
        // hide progress bar and show search form
        progressBar.setVisibility(View.GONE);
        progressMsg.setVisibility(View.GONE);
        searchForm.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // close database
        zipCodeDataSource.close();

        // save memory
        zipCodeDataSource = null;
    }

    public ZipCodeDataSource getZipCodeDataSource() {
        return this.zipCodeDataSource;
    }

}
