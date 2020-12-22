package com.example.aplikasiberita.activities;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.aplikasiberita.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD;

public class DetailActivity extends AppCompatActivity {


    TextView judul_berita, sumber_berita, penulis, deskripsi, tanggal_rilis, content, link_berita;
    ImageView gambar_berita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        gambar_berita = findViewById(R.id.gambar_berita);
        judul_berita = findViewById(R.id.judul_berita);
        sumber_berita = findViewById(R.id.sumber_berita);
        tanggal_rilis = findViewById(R.id.tanggal_rilis);
        link_berita = findViewById(R.id.url);
        penulis = findViewById(R.id.penulis);
        deskripsi = findViewById(R.id.deskripsi);
        content = findViewById(R.id.content);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            content.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
        }

        Glide.with(DetailActivity.this)
                .load(getIntent().getStringExtra("urlToImage"))
                .placeholder(R.drawable.image_loading)
                .into(gambar_berita);

        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(getIntent().getStringExtra("publishedAt"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDate = new SimpleDateFormat("dd MMMM yyyy, K a").format(date);

        judul_berita.setText(getIntent().getStringExtra("title"));
        sumber_berita.setText(getIntent().getStringExtra("name"));
        link_berita.setText(getIntent().getStringExtra("url"));
        tanggal_rilis.setText( "Tanggal terbit : " + formattedDate);
        penulis.setText( "Penulis : " + getIntent().getStringExtra("author"));
        deskripsi.setText( "Deskripsi : " + getIntent().getStringExtra("description"));
        content.setText(getIntent().getStringExtra("content"));
    }
}
