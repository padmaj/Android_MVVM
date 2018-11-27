package com.anddev.pm.pizzame.api;

/**
 * https://query.yahooapis.com/v1/public/yql?q=select * from local.search where zip='78759' and query='pizza'&format=json&diagnostics=true&callback
 */
final class EndPoints {
    static final String BASE_URL = "https://query.yahooapis.com/";
    static final String VERSION = "v1/public/yql?q=";
    static final String QUERY = "select * from local.search where zip='%s' and query='pizza'&format=json&diagnostics=true&callback";
}
