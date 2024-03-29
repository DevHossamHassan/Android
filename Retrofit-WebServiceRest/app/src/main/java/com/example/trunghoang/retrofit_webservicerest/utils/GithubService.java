package com.example.trunghoang.retrofit_webservicerest.utils;

import com.example.trunghoang.retrofit_webservicerest.model.Repo;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by trunghoang on 7/28/15.
 */
public interface GithubService {
    public static final String ENDPOINT = "https://api.github.com";

    @Headers("Cache-Control: max-age=640000")
    @GET("/users/{user}/repos")
    List<Repo> listRepos(@Path("user") String user);

    @GET("/users/{user}/repos")
    void listReposAsync(@Path("user") String user, Callback<List<Repo>> callback);

    @GET("/search/repositories")
    List<Repo> searchRepos(@Query("q") String query);
}
