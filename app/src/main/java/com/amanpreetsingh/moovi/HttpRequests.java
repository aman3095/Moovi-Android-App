package com.amanpreetsingh.moovi;

import okhttp3.Request;

/**
 * Created by Aman on 1/29/2016.
 */
public class HttpRequests {

    public static final String API_KEY = "api_key";

    public static final String API_SUFFIX = "http://api.themoviedb.org/3/";

    public static final String BASE_URL = "http://image.tmdb.org/t/p/";

    public static final String SLASH = "/";

    public static final String MOVIE = "movie";

    public static final String QUESTION_MARK = "?";

    public static final String EQUALS = "=";

    public static final String TOP_RATED = "top_rated";

    public static final String PAGE = "page";

    public static final String AMPERSAND = "&";

    public static final String DISCOVER = "discover/";

    public static final String SORT_BY = "sort_by";

    public static final String POPULARITY = "popularity.desc";

    public static String getMovieURL(int id){
        return API_SUFFIX + MOVIE + SLASH + id + QUESTION_MARK + API_KEY + EQUALS + Utils.KEY;
    }

    public static String getPopularMoviesURL(){
        return API_SUFFIX + DISCOVER + MOVIE + QUESTION_MARK + SORT_BY + EQUALS + POPULARITY + AMPERSAND + API_KEY + EQUALS + Utils.KEY;
    }

    public static String getPopularMoviesURL(int pageNo){
        return API_SUFFIX + DISCOVER + MOVIE + QUESTION_MARK + SORT_BY + EQUALS + POPULARITY + AMPERSAND + PAGE + EQUALS + pageNo + AMPERSAND + API_KEY + EQUALS + Utils.KEY;
    }

    public static String getPosterURL(String posterPath, String size){
        return BASE_URL + size + posterPath;
    }

    public static Request getRequestFromURL(String url){
        return new Request.Builder()
                .url(url)
                .build();
    }

    public static Request getMovieRequest(int id){
        return getRequestFromURL(getMovieURL(id));
    }

    public static Request getPopularMoviesRequest(){
        return getPopularMoviesRequest(1);
    }

    public static Request getPopularMoviesRequest(int pages){
        return getRequestFromURL(getPopularMoviesURL(pages));
    }
}
