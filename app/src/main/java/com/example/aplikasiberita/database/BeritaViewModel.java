package com.example.aplikasiberita.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.aplikasiberita.models.Berita;

import java.util.List;

public class BeritaViewModel extends AndroidViewModel {

    private BeritaRepository mRepository;
    private LiveData<List<Berita>> mSemuaBerita;

    public BeritaViewModel(@NonNull Application application) {
        super(application);
        mRepository = new BeritaRepository(application);
        mSemuaBerita = mRepository.getBerita();
    }

    public LiveData<List<Berita>> getBerita() {
        return mSemuaBerita;
    }

    public void insertBerita(List<Berita> beritas) {
        mRepository.insertBerita(beritas);
    }

}
