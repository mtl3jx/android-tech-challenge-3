package com.captechventures.techchallenge3.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import com.captechventures.techchallenge3.R;
import com.captechventures.techchallenge3.model.ZipCodeEntry;

import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcels;

public class ZipCodeDetailsActivity extends AppCompatActivity {

    private ZipCodeEntry zipCodeEntry;

    // tag for logging purposes
    private static final String TAG = ZipCodeDetailsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zip_code_details);

        // bundle variables
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        zipCodeEntry = Parcels.unwrap(bundle.getParcelable("zipCodeEntry"));

        // render view of zip code details
        TableRow currentRow;
        TextView key, value;

        String zipCode = zipCodeEntry.getZipCode();
        currentRow = (TableRow) findViewById(R.id.row_zip_code);
        if (!zipCode.isEmpty()) {
            key = (TextView) findViewById(R.id.key_zip_code);
            value = (TextView) findViewById(R.id.value_zip_code);

            key.setText("Zip Code");
            value.setText(zipCode);

            currentRow.setVisibility(View.VISIBLE);
        } else {
            currentRow.setVisibility(View.GONE);
        }

        Double lat = zipCodeEntry.getLatitude();
        currentRow = (TableRow) findViewById(R.id.row_latitude);
        if (lat != null) {
            value = (TextView) findViewById(R.id.value_latitude);
            value.setText(lat.toString());

            currentRow.setVisibility(View.VISIBLE);
        } else {
            currentRow.setVisibility(View.GONE);
        }

        Double lon = zipCodeEntry.getLongitude();
        currentRow = (TableRow) findViewById(R.id.row_longitude);
        if (lon != null) {
            value = (TextView) findViewById(R.id.value_longitude);
            value.setText(lon.toString());

            currentRow.setVisibility(View.VISIBLE);
        } else {
            currentRow.setVisibility(View.GONE);
        }

        String city = zipCodeEntry.getCity();
        currentRow = (TableRow) findViewById(R.id.row_city);
        if (city != null && !city.isEmpty()) {
            value = (TextView) findViewById(R.id.value_city);
            value.setText(city);

            currentRow.setVisibility(View.VISIBLE);
        } else {
            currentRow.setVisibility(View.GONE);
        }

        String state = zipCodeEntry.getState();
        currentRow = (TableRow) findViewById(R.id.row_state);
        if (!state.isEmpty()) {
            value = (TextView) findViewById(R.id.value_state);
            value.setText(state);

            currentRow.setVisibility(View.VISIBLE);
        } else {
            currentRow.setVisibility(View.GONE);
        }

        String county = zipCodeEntry.getCounty();
        currentRow = (TableRow) findViewById(R.id.row_county);
        if (!StringUtils.isEmpty(county)) {
            value = (TextView) findViewById(R.id.value_county);
            value.setText(county);

            currentRow.setVisibility(View.VISIBLE);
        } else {
            currentRow.setVisibility(View.GONE);
        }

        String type = zipCodeEntry.getType();
        currentRow = (TableRow) findViewById(R.id.row_type);
        if (type != null && !type.isEmpty()) {
            value = (TextView) findViewById(R.id.value_type);
            value.setText(type);

            currentRow.setVisibility(View.VISIBLE);
        } else {
            currentRow.setVisibility(View.GONE);
        }

        Boolean preferred = zipCodeEntry.getPreferred();
        currentRow = (TableRow) findViewById(R.id.row_preferred);
        if (preferred != null) {
            value = (TextView) findViewById(R.id.value_preferred);
            if (preferred) value.setText("Yes");
            else value.setText("No");

            currentRow.setVisibility(View.VISIBLE);
        } else {
            currentRow.setVisibility(View.GONE);
        }

        String worldRegion = zipCodeEntry.getWorldRegion();
        currentRow = (TableRow) findViewById(R.id.row_world_region);
        if (!StringUtils.isEmpty(worldRegion)) {
            value = (TextView) findViewById(R.id.value_world_region);
            value.setText(worldRegion);

            currentRow.setVisibility(View.VISIBLE);
        } else {
            currentRow.setVisibility(View.GONE);
        }

        String country = zipCodeEntry.getCountry();
        currentRow = (TableRow) findViewById(R.id.row_country);
        if (!StringUtils.isEmpty(country)) {
            value = (TextView) findViewById(R.id.value_country);
            value.setText(country);

            currentRow.setVisibility(View.VISIBLE);
        } else {
            currentRow.setVisibility(View.GONE);
        }

        String locationText = zipCodeEntry.getLocationText();
        currentRow = (TableRow) findViewById(R.id.row_location_text);
        if (!StringUtils.isEmpty(locationText)) {
            value = (TextView) findViewById(R.id.value_location_text);
            value.setText(locationText);

            currentRow.setVisibility(View.VISIBLE);
        } else {
            currentRow.setVisibility(View.GONE);
        }

        String location = zipCodeEntry.getLocation();
        currentRow = (TableRow) findViewById(R.id.row_location);
        if (!StringUtils.isEmpty(location)) {
            value = (TextView) findViewById(R.id.value_location);
            value.setText(location);

            currentRow.setVisibility(View.VISIBLE);
        } else {
            currentRow.setVisibility(View.GONE);
        }

        Integer population = zipCodeEntry.getPopulation();
        currentRow = (TableRow) findViewById(R.id.row_population);
        if (population != null) {
            value = (TextView) findViewById(R.id.value_population);
            value.setText(population.toString());

            currentRow.setVisibility(View.VISIBLE);
        } else {
            currentRow.setVisibility(View.GONE);
        }

        Integer housingUnits = zipCodeEntry.getHousingUnits();
        currentRow = (TableRow) findViewById(R.id.row_housing_units);
        if (housingUnits != null) {
            value = (TextView) findViewById(R.id.value_housing_units);
            value.setText(housingUnits.toString());

            currentRow.setVisibility(View.VISIBLE);
        } else {
            currentRow.setVisibility(View.GONE);
        }

        Integer income = zipCodeEntry.getIncome();
        currentRow = (TableRow) findViewById(R.id.row_income);
        if (income != null) {
            value = (TextView) findViewById(R.id.value_income);
            value.setText(income.toString());

            currentRow.setVisibility(View.VISIBLE);
        } else {
            currentRow.setVisibility(View.GONE);
        }

        Double landArea = zipCodeEntry.getLandArea();
        currentRow = (TableRow) findViewById(R.id.row_land_area);
        if (landArea != null) {
            value = (TextView) findViewById(R.id.value_land_area);
            value.setText(landArea.toString());

            currentRow.setVisibility(View.VISIBLE);
        } else {
            currentRow.setVisibility(View.GONE);
        }

        Double waterArea = zipCodeEntry.getWaterArea();
        currentRow = (TableRow) findViewById(R.id.row_water_area);
        if (waterArea != null) {
            value = (TextView) findViewById(R.id.value_water_area);
            value.setText(waterArea.toString());

            currentRow.setVisibility(View.VISIBLE);
        } else {
            currentRow.setVisibility(View.GONE);
        }

        Boolean decommissioned = zipCodeEntry.getDecommissioned();
        currentRow = (TableRow) findViewById(R.id.row_decommissioned);
        if (decommissioned != null) {
            value = (TextView) findViewById(R.id.value_decommissioned);
            if (decommissioned) value.setText("Yes");
            else value.setText("No");

            currentRow.setVisibility(View.VISIBLE);
        } else {
            currentRow.setVisibility(View.GONE);
        }

        String militaryRestriction = zipCodeEntry.getMilitaryRestrictionCodes();
        currentRow = (TableRow) findViewById(R.id.row_military_restriction);
        if (!StringUtils.isEmpty(militaryRestriction)) {
            value = (TextView) findViewById(R.id.value_military_restriction);
            value.setText(militaryRestriction);

            currentRow.setVisibility(View.VISIBLE);
        } else {
            currentRow.setVisibility(View.GONE);
        }

    }

}
