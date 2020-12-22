package com.example.aplikasiberita.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.aplikasiberita.models.Berita;

import java.util.List;

@Dao
public interface BeritaDao {

    @Query("SELECT * from berita_table")
    LiveData<List<Berita>> getBerita();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBerita(List<Berita> beritas);

    @Query("DELETE FROM berita_table")
    void deleteBerita();

}
