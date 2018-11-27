package com.anddev.pm.pizzame;

import com.anddev.pm.pizzame.api.model.Rating;
import com.anddev.pm.pizzame.api.model.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * {
 * "id": "204808642",
 * "xmlns": "urn:yahoo:lcl",
 * "Title": "I Fratelli Pizza North - Austin",
 * "Address": "10001 Research Blvd, Ste 160",
 * "City": "Austin",
 * "State": "TX",
 * "Phone": "(512) 795-8744",
 * "Latitude": "30.39223",
 * "Longitude": "-97.7456",
 * "Rating": {
 * "AverageRating": "NaN",
 * "TotalRatings": "0",
 * "TotalReviews": "0",
 * "LastReviewDate": null,
 * "LastReviewIntro": null
 * },
 * "Distance": "0.67",
 * "Url": "https://local.yahoo.com/info-204808642-i-fratelli-pizza-north-austin-austin",
 * "ClickUrl": "https://local.yahoo.com/info-204808642-i-fratelli-pizza-north-austin-austin",
 * "MapUrl": "https://local.yahoo.com/info-204808642-i-fratelli-pizza-north-austin-austin?viewtype=map",
 * "BusinessUrl": "http://www.ifratelli.net/",
 * "BusinessClickUrl": "http://www.ifratelli.net/",
 * "Categories": {
 * "Category": [
 * {
 * "id": "96926243",
 * "content": "Pizza"
 * },
 * {
 * "id": "96926148",
 * "content": "Food Delivery Service"
 * }
 * ]
 * }
 * }
 */
public class SampleResults {

    private static final String ID = "204808642";
    private static final String XMLNS = "urn:yahoo:lcl";
    private static final String TITLE = "I Fratelli Pizza North - Austin";
    private static final String ADDRESS = "10001 Research Blvd, Ste 160";
    private static final String CITY = "Austin";
    private static final String STATE = "TX";
    private static final String PHONE = "(512) 795-8744";
    private static final String LATITUDE = "30.39223";
    private static final String LONGITUDE = "-97.7456";
    private static final String AVG_RATING = "NaN";
    private static final String TOTAL_RATING = "0";
    private static final String TOTAL_REVIEWS = "0";
    private static final String LAST_REVIEW_DATE = "null";
    private static final String LAST_REVIEW_INTRO = "null";
    private static final String DISTANCE = "0.67";
    private static final String URL = "https://local.yahoo.com/info-204808642-i-fratelli-pizza-north-austin-austin";
    private static final String CLICK_URL = "https://local.yahoo.com/info-204808642-i-fratelli-pizza-north-austin-austin";
    private static final String MAP_URL = "https://local.yahoo.com/info-204808642-i-fratelli-pizza-north-austin-austin?viewtype=map";
    private static final String BUSINESS_URL = "https://local.yahoo.com/info-204808642-i-fratelli-pizza-north-austin-austin";
    private static final String BUSINESS_CLICK_URL = "https://local.yahoo.com/info-204808642-i-fratelli-pizza-north-austin-austin";

    public static Result getResult() {
        Result result = new Result();
        result.id = ID;
        result.xmlns = XMLNS;
        result.title = TITLE;
        result.address = ADDRESS;
        result.city = CITY;
        result.state = STATE;
        result.phone = PHONE;
        result.latitude = LATITUDE;
        result.longitude = LONGITUDE;

        Rating rating = new Rating();
        rating.averageRating = AVG_RATING;
        rating.totalRatings = TOTAL_RATING;
        rating.totalReviews = TOTAL_REVIEWS;
        rating.lastReviewDate = LAST_REVIEW_DATE;
        rating.lastReviewIntro = LAST_REVIEW_INTRO;

        result.rating = rating;
        result.distance = DISTANCE;
        result.url = URL;
        result.clickUrl = CLICK_URL;
        result.mapUrl = MAP_URL;
        result.businessUrl = BUSINESS_URL;
        result.businesslickUrl = BUSINESS_CLICK_URL;

        return result;
    }
}
