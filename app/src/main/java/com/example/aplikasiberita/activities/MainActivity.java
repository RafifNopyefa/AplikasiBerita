package com.example.aplikasiberita.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.example.aplikasiberita.api.DataApi;
import com.example.aplikasiberita.R;
import com.example.aplikasiberita.adapters.BeritaAdapter;
import com.example.aplikasiberita.models.Berita;
import com.example.aplikasiberita.database.BeritaViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    BeritaAdapter beritaAdapter;
    BeritaViewModel mBeritaViewModel;
    DataApi dataApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_list);
        AndroidNetworking.initialize(getApplicationContext());

        beritaAdapter = new BeritaAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(beritaAdapter);

        mBeritaViewModel = new ViewModelProvider(this).get(BeritaViewModel.class);
        dataApi = new DataApi(mBeritaViewModel);

        ambilBerita();

        mBeritaViewModel.getBerita().observe(this, new Observer<List<Berita>>() {
            @Override
            public void onChanged(List<Berita> beritas) {
                beritaAdapter.setBatikList(beritas);
            }
        });

    }

    public void ambilBerita() {
        Toast.makeText(MainActivity.this, "Sedang Loading!", Toast.LENGTH_SHORT).show();
        dataApi.getBerita();
    }

}
