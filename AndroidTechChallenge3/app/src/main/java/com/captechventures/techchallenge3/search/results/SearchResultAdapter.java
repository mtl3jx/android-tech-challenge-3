package com.captechventures.techchallenge3.search.results;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.captechventures.techchallenge3.R;
import com.captechventures.techchallenge3.model.ZipCodeEntry;

import org.apache.commons.text.WordUtils;

import java.util.List;

/**
 * Created by mluansing on 10/13/17.
 */

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ZipCodeEntryViewHolder>  {

    private final Context context;
    private List<ZipCodeEntry> zipCodeEntries;
    private ZipCodeItemListener zipCodeItemListener;
    private int resource;

    // tag for logging purposes
    private static final String TAG = SearchResultAdapter.class.getSimpleName();

    public SearchResultAdapter(@NonNull Context context, @LayoutRes int resource, List<ZipCodeEntry> zipCodeEntries, ZipCodeItemListener zipCodeItemListener) {
        this.context = context;
        this.zipCodeEntries = zipCodeEntries;
        this.zipCodeItemListener = zipCodeItemListener;
        this.resource = resource;
    }

    @Override
    public ZipCodeEntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new ZipCodeEntryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ZipCodeEntryViewHolder holder, final int position) {
        final ZipCodeEntry zipCodeEntry = zipCodeEntries.get(position);

        // grab row view items
        TableRow tableRow = holder.tableRow;
        TextView zipCode = holder.zipCode;
        TextView city = holder.city;
        TextView state = holder.state;

        // make UI changes to row
        zipCode.setText(zipCodeEntry.getZipCode());
        city.setText(WordUtils.capitalizeFully(zipCodeEntry.getCity()));
        state.setText(zipCodeEntry.getState());

        // make table row clickable
        tableRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v(TAG, "Item " + position + " clicked");

                // verify network connection
                if (zipCodeItemListener.isConnectedToInternet()) {
                    // pull up url with details of quake
                    zipCodeItemListener.openMapView(zipCodeEntry);
                } else {
                    Log.d(TAG, "Not connected to internet");
                    Toast.makeText(context, "Could not load Map View\n" +
                            "No Network Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return zipCodeEntries.size();
    }

    // holds the view for each ZipCodeEntry's TableRow
    public class ZipCodeEntryViewHolder extends RecyclerView.ViewHolder {
        public TableRow tableRow;
        public TextView zipCode, city, state;

        // view holder constructor
        public ZipCodeEntryViewHolder(View view) {
            super(view);
            tableRow = view.findViewById(R.id.table_row);
            zipCode = view.findViewById(R.id.zip_code);
            city = view.findViewById(R.id.city);
            state = view.findViewById(R.id.state);
        }
    }

    // one interface for adapter --> activity
    public interface ZipCodeItemListener {
        // activity methods that the adapter should have access to
        boolean isConnectedToInternet();
        void openMapView(ZipCodeEntry zipCodeEntry);
    }
}
