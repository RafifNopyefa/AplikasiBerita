package com.example.aplikasiberita.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.aplikasiberita.models.Berita;

import java.util.List;

public class BeritaRepository {

    private BeritaDao mBeritaDao;
    private LiveData<List<Berita>> mSemuaBerita;

    public BeritaRepository(Application application) {
        BeritaDatabase db = BeritaDatabase.getDatabase(application);
        mBeritaDao = db.hotelDao();
        mSemuaBerita = mBeritaDao.getBerita();
    }

    LiveData<List<Berita>> getBerita() {
        return mSemuaBerita;
    }

    public void insertBerita(List<Berita> beritas) {
        BeritaDatabase.databaseWriteExecutor.execute(() -> {
            mBeritaDao.insertBerita(beritas);
        });
    }

}
