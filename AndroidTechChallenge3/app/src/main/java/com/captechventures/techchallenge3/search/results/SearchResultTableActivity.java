package com.captechventures.techchallenge3.search.results;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.captechventures.techchallenge3.database.ZipCodeDataSource;
import com.captechventures.techchallenge3.map.MapsActivity;
import com.captechventures.techchallenge3.R;
import com.captechventures.techchallenge3.details.ZipCodeDetailsActivity;
import com.captechventures.techchallenge3.model.ZipCodeEntry;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class SearchResultTableActivity extends AppCompatActivity implements SearchResultAdapter.ZipCodeItemListener {

    // tag for logging purposes
    private static final String TAG = SearchResultTableActivity.class.getSimpleName();

    // DB variables
    private ZipCodeDataSource zipCodeDataSource;

    // layout variables
    private TextView searchToken, resultsMsg;
    private TableLayout tableLayout;
    private RecyclerView recyclerView;
    private SearchResultAdapter adapter;

    // list of zip codes
    private List<ZipCodeEntry> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_table);

        // initialize layout variables
        tableLayout = (TableLayout) findViewById(R.id.table_layout);
        searchToken = (TextView) findViewById(R.id.search_tokens);
        resultsMsg = (TextView) findViewById(R.id.results_msg);

        // init variables
        list = new ArrayList<>();

        // grab values from bundle
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String searchZip = bundle.getString("zip_search");
        String searchLocality = bundle.getString("locality_search");
        Log.v(TAG, "searchZip: " + searchZip);
        Log.v(TAG, "searchLocality: " + searchLocality);

        // get existing DB connection or create new ZipCodeDataSource and open it
        zipCodeDataSource = new ZipCodeDataSource(this);
        zipCodeDataSource.open();
        Log.v(TAG, "getting zipCodeDataSource");

        // validate search tokens entered
        if (searchZip.isEmpty() && searchLocality.isEmpty()) {
            Log.e(TAG, "no search tokens entered");

        } else {
            StringBuilder searchParams = new StringBuilder();

            if (!searchZip.isEmpty()) {
                searchParams.append("ZipCode '" + searchZip + "' ");
            }
            if (!searchLocality.isEmpty()) {
                if (!searchParams.toString().isEmpty()) {
                    searchParams.append("and ");
                }
                searchParams.append("Locality '" + searchLocality + "'");
            }

            searchToken.setText("All results with " + searchParams.toString());

            list = zipCodeDataSource.search(searchZip, searchLocality);
        }

        if (list.isEmpty()) {
            // message for no results found
            tableLayout.setVisibility(View.GONE);
            resultsMsg.setVisibility(View.VISIBLE);
        } else {
            // show table of results
            resultsMsg.setVisibility(View.GONE);
            tableLayout.setVisibility(View.VISIBLE);
        }

        // adapter and recyclerView to render results in table
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        adapter = new SearchResultAdapter(this, R.layout.item_search_result, list, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean isConnectedToInternet(){
        ConnectivityManager connectivity = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
        }
        return false;
    }

    @Override
    public void openMapView(ZipCodeEntry zipCodeEntry) {
        // start new activity with mapview with pin on lat/lon of location

        Bundle bundle = new Bundle();
        bundle.putParcelable("zipCodeEntry", Parcels.wrap(zipCodeEntry));

        Intent intent;
        if (zipCodeEntry.getLatitude() == null || zipCodeEntry.getLongitude() == null) {
            Toast.makeText(getApplicationContext(), "No Map Coordinates in Database\n" +
                    "Opening Locality Details", Toast.LENGTH_SHORT).show();
            intent = new Intent(SearchResultTableActivity.this, ZipCodeDetailsActivity.class);
        } else {
            intent = new Intent(SearchResultTableActivity.this, MapsActivity.class);
        }
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
