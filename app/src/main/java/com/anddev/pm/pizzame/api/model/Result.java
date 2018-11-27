package com.anddev.pm.pizzame.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
public class Result implements Parcelable {

    @SerializedName("id")
    public String id;

    @SerializedName("xmlns")
    public String xmlns;

    @SerializedName("Title")
    public String title;

    @SerializedName("Address")
    public String address;

    @SerializedName("City")
    public String city;

    @SerializedName("State")
    public String state;

    @SerializedName("Phone")
    public String phone;

    @SerializedName("Latitude")
    public String latitude;

    @SerializedName("Longitude")
    public String longitude;

    @SerializedName("Rating")
    public Rating rating;

    @SerializedName("Distance")
    public String distance;

    @SerializedName("Url")
    public String url;

    @SerializedName("ClickUrl")
    public String clickUrl;

    @SerializedName("MapUrl")
    public String mapUrl;

    @SerializedName("BusinessUrl")
    public String businessUrl;

    @SerializedName("BusinessClickUrl")
    public String businesslickUrl;

    @Expose
    @SerializedName("Categories")
    public Object categories;

    public Result() {}

    private Result(Parcel in) {
        id = in.readString();
        xmlns = in.readString();
        title = in.readString();
        address = in.readString();
        city = in.readString();
        state = in.readString();
        phone = in.readString();
        latitude = in.readString();
        longitude = in.readString();
        rating = in.readParcelable(Rating.class.getClassLoader());
        distance = in.readString();
        url = in.readString();
        clickUrl = in.readString();
        mapUrl = in.readString();
        businessUrl = in.readString();
        businesslickUrl = in.readString();
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(xmlns);
        parcel.writeString(title);
        parcel.writeString(address);
        parcel.writeString(city);
        parcel.writeString(state);
        parcel.writeString(phone);
        parcel.writeString(latitude);
        parcel.writeString(longitude);
        parcel.writeParcelable(rating, i);
        parcel.writeString(distance);
        parcel.writeString(url);
        parcel.writeString(clickUrl);
        parcel.writeString(mapUrl);
        parcel.writeString(businessUrl);
        parcel.writeString(businesslickUrl);
    }
}
