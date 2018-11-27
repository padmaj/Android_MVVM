package com.anddev.pm.pizzame.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * "query": {
 * "count": 10,
 * "created": "2018-11-21T03:56:12Z",
 * "lang": "en-us",
 * "diagnostics": {}
 * "results": {}
 * }
 */
public class Query {

    @SerializedName("count")
    public Integer count;

    @SerializedName("created")
    public String created;

    @SerializedName("lang")
    public String lang;

    @Expose
    @SerializedName("diagnostics")
    Object diagnostics;

    @SerializedName("results")
    public Results results;

}
