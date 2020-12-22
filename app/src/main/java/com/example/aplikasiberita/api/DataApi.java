package com.example.aplikasiberita.api;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.aplikasiberita.models.Berita;
import com.example.aplikasiberita.database.BeritaViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataApi {

    BeritaViewModel mBeritaViewModel;

    public DataApi(BeritaViewModel mBeritaViewModel) {
        this.mBeritaViewModel = mBeritaViewModel;
    }

    public void getBerita() {
        List<Berita> dataBerita = new ArrayList<>();
        AndroidNetworking.get("http://newsapi.org/v2/top-headlines?country=id&apiKey=e502347eabfb4c17b772f561205a8361")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("articles");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject data = jsonArray.getJSONObject(i);
                                Berita item = new Berita(
                                        data.getJSONObject("source").getString("name"),
                                        data.getString("author"),
                                        data.getString("title"),
                                        data.getString("description"),
                                        data.getString("url"),
                                        data.getString("urlToImage"),
                                        data.getString("publishedAt"),
                                        data.getString("content")
                                );

                                dataBerita.add(item);
                            }

                            mBeritaViewModel.insertBerita(dataBerita);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }
}
