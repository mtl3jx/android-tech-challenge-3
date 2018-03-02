package com.captechventures.techchallenge3;

import android.app.Application;

/**
 * Created by mluansing on 10/2/17.
 */

public class MainApplication extends Application {

    private static final String DATABASE_NAME = "TechChallengeDB";
    private static final String TAG = MainApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * OUTSIDE OF APP
     * use SQL db by default if exists, else -->
     * convert CSV to SQL database (only on first run)
     * null handling / imputations
     *
     * WITHIN APP
     * load data from SQL db all at once -- no lazy loading / pagination
     *
     * search by zip / locality name to find all matches
     * search by partial zip / locality
     *
     * TODO: potential drop down search selection?? pending size of list<zip> and list<locality>
     *
     * TODO: do not need free text query (user types in text to search for similar / containing phrases)
     * ^ aka do an exact match of that city name / zip
     *
     * view for each entry is zipcode, city, state, (will be using lat, long)
     * grab all columns of CSV just for funsies
     *
     * click on entry --> new view with map centered on locality
     * map must have pin at lat / lon of location
     * click on pin --> new page with full demographic data for locality
     *
     * The zip code data has a many-to-many relationship between zip code and locality name.
     * One locality may have multiple zip codes and one zip code may have multiple localities.
     *
     * bugs
     * TODO: landArea and waterArea show in DetailsActivity as 0.0 when null
     *
     **/

}

