package com.anddev.pm.pizzame.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * "Rating": {
 * "AverageRating": "NaN",
 * "TotalRatings": "0",
 * "TotalReviews": "0",
 * "LastReviewDate": null,
 * "LastReviewIntro": null
 * },
 */
public class Rating implements Parcelable {

    @SerializedName("AverageRating")
    public String averageRating;

    @SerializedName("TotalRatings")
    public String totalRatings;

    @SerializedName("TotalReviews")
    public String totalReviews;

    @SerializedName("LastReviewDate")
    public String lastReviewDate;

    @SerializedName("LastReviewIntro")
    public String lastReviewIntro;

    public Rating() {}

    private Rating(Parcel in) {
        averageRating = in.readString();
        totalRatings = in.readString();
        totalReviews = in.readString();
        lastReviewDate = in.readString();
        lastReviewIntro = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(averageRating);
        dest.writeString(totalRatings);
        dest.writeString(totalReviews);
        dest.writeString(lastReviewDate);
        dest.writeString(lastReviewIntro);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Rating> CREATOR = new Creator<Rating>() {
        @Override
        public Rating createFromParcel(Parcel in) {
            return new Rating(in);
        }

        @Override
        public Rating[] newArray(int size) {
            return new Rating[size];
        }
    };
}
