package com.captechventures.techchallenge3.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.captechventures.techchallenge3.model.ZipCodeEntry;
import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mluansing on 10/2/17.
 */

public class ZipCodeOpenHelper extends SQLiteOpenHelper {

    // Logging tag
    private static final String TAG = ZipCodeOpenHelper.class.getSimpleName();

    // SQL db
    private SQLiteDatabase database;

    // CSV filename
    private Context context;
    private static final String CSV_FILE = "free_zipcode_database.csv";

    // SQLite DB variables
    private static final String DATABASE_NAME = "TechChallenge";
    private static final int DATABASE_VERSION = 1;
    public static final String ZIPCODE_TABLE_NAME = "US Zip Codes";

    // DB columns
    private static final int KEY_ZIPCODE = 0,
            KEY_LATITUDE = 1,
            KEY_LONGITUDE = 2,
            KEY_CITY = 3,
            KEY_STATE = 4,
            KEY_COUNTY = 5,
            KEY_TYPE = 6,
            KEY_PREFERRED = 7,
            KEY_WORLDREGION = 8,
            KEY_COUNTRY = 9,
            KEY_LOCATIONTEXT = 10,
            KEY_LOCATION = 11,
            KEY_POPULATION = 12,
            KEY_HOUSINGUNITS = 13,
            KEY_INCOME = 14,
            KEY_LANDAREA = 15,
            KEY_WATERAREA = 16,
            KEY_DECOMMISSIONED = 17,
            KEY_MILITARYRESTRICTION = 18,
            COL_COUNT = 19;

    public ZipCodeOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // only called on app's first run time on that device
        Log.v(TAG, "calling onCreate in OpenHelper");

        this.database = db;

        // create database
        createDatabase(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // called every time new DB version
        Log.v(TAG, "calling onUpgrade in OpenHelper");

        this.database = db;

        // drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS '" + ZIPCODE_TABLE_NAME + "'");

        // create tables again
        createDatabase(db);
    }

    // creates DB and tables
    private void createDatabase(SQLiteDatabase db) {

        final String ZIPCODE_TABLE_CREATE =
                "CREATE TABLE IF NOT EXISTS '" + ZIPCODE_TABLE_NAME + "' (" +
                        " ZipCode CHAR(5)," +
                        " Latitude DOUBLE(10,6)," +
                        " Longitude DOUBLE(10,6)," +
                        " City VARCHAR(50)," +
                        " State CHAR(2)," +
                        " County VARCHAR(40)," +
                        " Type VARCHAR(30)," +
                        " Preferred BIT," +
                        " WorldRegion CHAR(2)," +
                        " Country CHAR(2)," +
                        " LocationText VARCHAR(50)," +
                        " Location VARCHAR(50)," +
                        " Population INT," +
                        " HousingUnits INT," +
                        " Income INT," +
                        " LandArea DOUBLE(10,6)," +
                        " WaterArea DOUBLE(10,6)," +
                        " Decommissioned BIT," +
                        " MilitaryRestrictionCodes VARCHAR(50)" +
                        " );";

        // create table within
        db.execSQL(ZIPCODE_TABLE_CREATE);

        Log.v(TAG, "Created table...");

        InputStream inputStream;
        try {
            // open CSV file
            inputStream = context.getAssets().open(CSV_FILE);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            CSVReader csvReader = new CSVReader(inputStreamReader);
            Log.v(TAG, "Reading CSV...");

            // build insert query
            String columns = "ZipCode, Latitude, Longitude, City, State"
                    + ", County, Type, Preferred, WorldRegion, Country, LocationText, Location, Population, HousingUnits, Income, LandArea, WaterArea, Decommissioned, MilitaryRestrictionCodes";
            String insert_query = "INSERT INTO '" + ZIPCODE_TABLE_NAME + "' (" + columns + ") values (";
            String end_query = ");";

            // begin db transaction
            db.beginTransaction();

            String[] line;
            while ((line = csvReader.readNext()) != null) {
                // validate number of columns read in
                if (line.length != COL_COUNT) Log.e(TAG, "Line read incorrectly");

                StringBuffer values = new StringBuffer("");

                values.append("\"" + line[KEY_ZIPCODE]+ "\"");

                // handle null values within line and insert null to query
                String lat = line[KEY_LATITUDE];
                if (!lat.equals("")) {
                    values.append(", " + lat);
                } else {
                    values.append(", NULL");
                }

                String lon = line[KEY_LONGITUDE];
                if (!lon.equals("")) {
                    values.append(", " + lon);
                } else {
                    values.append(", NULL");
                }

                // string
                values.append(", \"" + line[KEY_CITY] + "\"");
                values.append(", \"" + line[KEY_STATE] + "\"");

                // string
                String county = line[KEY_COUNTY].trim();
                if (!county.equals("")) {
                    values.append(", \"" + county + "\"");
                } else values.append(", NULL");

                // string
                values.append(", \"" + line[KEY_TYPE] + "\"");

                // boolean
                if (line[KEY_PREFERRED].equals("No")) {
                    values.append(", 0");
                } else {
                    values.append(", 1");
                }

                // string
                String worldRegion = line[KEY_WORLDREGION];
                if (!worldRegion.equals("")) {
                    values.append(", \"" + worldRegion + "\"");
                } else values.append(", NULL");

                String country = line[KEY_COUNTRY];
                if (!country.equals("")) {
                    values.append(", \"" + country + "\"");
                } else values.append(", NULL");

                String locationtext = line[KEY_LOCATIONTEXT];
                if (!locationtext.equals("")) {
                    values.append(", \"" + locationtext + "\"");
                } else values.append(", NULL");

                String location = line[KEY_LOCATION];
                if (!location.equals("")) {
                    values.append(", \"" + location + "\"");
                } else values.append(", NULL");

                // integer
                String population = line[KEY_POPULATION];
                if (!population.equals("")) {
                    values.append(", " + population + "");
                } else values.append(", NULL");

                // integer
                String housingUnits = line[KEY_HOUSINGUNITS];
                if (!housingUnits.equals("")) {
                    values.append(", " + housingUnits);
                } else values.append(", NULL");

                // integer
                String income = line[KEY_INCOME];
                if (!income.equals("")) {
                    values.append(", " + income + "");
                } else values.append(", NULL");

                // double
                String landarea = line[KEY_LANDAREA];
                if (!landarea.equals("")) {
                    values.append(", " + landarea + "");
                } else values.append(", NULL");

                // double
                String waterarea = line[KEY_WATERAREA];
                if (!waterarea.equals("")) {
                    values.append(", " + waterarea + "");
                } else values.append(", NULL");

                if (line[KEY_DECOMMISSIONED].equals("No")) {
                    values.append(", 0");
                } else {
                    values.append(", 1");
                }

                if (!line[KEY_MILITARYRESTRICTION].equals("")) {
                    values.append(", \"" + line[KEY_MILITARYRESTRICTION] + "\"");
                } else values.append(", NULL");

                // insert each row into DB
                String query = insert_query + values + end_query;
//                Log.d(TAG, query);
                db.execSQL(query);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // end db transaction
        db.setTransactionSuccessful();
        db.endTransaction();

        Log.v(TAG, "SQL database initialized!");

   }

    // TODO: checkDatabase -- looks to find DB
    // do sqlite open and if db returned then don't need to create a new one, just move on
    // if no db returned then create new one

    // search by zip code and locality
    public List<ZipCodeEntry> search(String zipSearch, String localitySearch) {

        List<ZipCodeEntry> list = new ArrayList<>();

        // error grabbing -- should not happen
        if (zipSearch.isEmpty() && localitySearch.isEmpty()) return null;

        // set up database
        if (this.database == null) {
            database = getReadableDatabase();
        }

        // set up search query
        StringBuilder searchQuery = new StringBuilder("SELECT * FROM '" + ZIPCODE_TABLE_NAME
                + "' WHERE ");

        StringBuilder searchParams = new StringBuilder();

        if (!zipSearch.isEmpty()) {
            searchParams.append("ZipCode LIKE '%" + zipSearch + "%' ");
        }

        if (!localitySearch.isEmpty()) {
            if (!searchParams.toString().isEmpty()) {
                searchParams.append("AND ");
            }
            searchParams.append("LocationText LIKE '%" + localitySearch + "%'");
        }

        searchQuery.append(searchParams);

        database.beginTransaction();

        // execute query and process results
        Cursor cursor = database.rawQuery(searchQuery.toString(), null);
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    Log.v(TAG, "SQL query returned " + cursor.getCount() + " rows");
                    while (!cursor.isAfterLast()) {
                        // add resulting row to list
                        String zipCode = cursor.getString(KEY_ZIPCODE);

                        Double latitude = cursor.getDouble(KEY_LATITUDE);
                        if (latitude == 0.0) latitude = null;

                        Double longitude = cursor.getDouble(KEY_LONGITUDE);
                        if (longitude == 0.0) longitude = null;

                        String city = cursor.getString(KEY_CITY);
                        String state = cursor.getString(KEY_STATE);
                        String county = cursor.getString(KEY_COUNTY);
                        String type = cursor.getString(KEY_TYPE);
                        Boolean preferred = cursor.getInt(KEY_PREFERRED) == 1;
                        String worldRegion = cursor.getString(KEY_TYPE);
                        String country = cursor.getString(KEY_TYPE);
                        String locationText = cursor.getString(KEY_LOCATIONTEXT);
                        String location = cursor.getString(KEY_LOCATION);

                        Integer population = cursor.getInt(KEY_POPULATION);
                        if (population == 0) population = null;

                        Integer housingUnits = cursor.getInt(KEY_HOUSINGUNITS);
                        if (housingUnits == 0) housingUnits = null;

                        Integer income = cursor.getInt(KEY_INCOME);
                        if (income == 0) income = null;

                        Double landArea = cursor.getDouble(KEY_LANDAREA);
                        Double waterArea = cursor.getDouble(KEY_WATERAREA);
                        Boolean decommissioned = cursor.getInt(KEY_DECOMMISSIONED) == 1;
                        String militaryRestriction = cursor.getString(KEY_MILITARYRESTRICTION);

                        ZipCodeEntry zipCodeEntry = new ZipCodeEntry(zipCode, latitude, longitude, city, state, county, type, preferred, worldRegion, country, locationText, location, population, housingUnits, income, landArea, waterArea, decommissioned, militaryRestriction);

//                        Log.v(TAG, zipCodeEntry.toString());

                        list.add(zipCodeEntry);

                        // go to next row
                        cursor.moveToNext();
                    }
                } else {
                    // no results found
                    Log.v(TAG, "No results found");
                }
            } finally {
                cursor.close();
            }
        }

        database.setTransactionSuccessful();
        database.endTransaction();

        return list;
    }

}
