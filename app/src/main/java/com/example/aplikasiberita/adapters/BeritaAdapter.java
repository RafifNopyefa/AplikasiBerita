package com.example.aplikasiberita.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aplikasiberita.R;
import com.example.aplikasiberita.activities.DetailActivity;
import com.example.aplikasiberita.models.Berita;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BeritaAdapter extends RecyclerView.Adapter<BeritaAdapter.HotelViewHolder> {

    List<Berita> data = new ArrayList<>();
    Context context;

    public BeritaAdapter(Context ct) {
        this.context = ct;
    }

    public void setBatikList(List<Berita> beritas) {
        this.data = beritas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_view, parent, false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {

        Berita currentBerita = data.get(position);

        holder.bindTo(currentBerita);

    }

    @Override
    public int getItemCount() {
        if (this.data != null) {
            return data.size();
        }
        return 0;
    }

    public class HotelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView judulBerita, sumberBerita, tanggalRilis;
        ImageView myImage;

        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);

            judulBerita = itemView.findViewById(R.id.judulBerita);
            sumberBerita = itemView.findViewById(R.id.sumberBerita);
            tanggalRilis = itemView.findViewById(R.id.tanggalRilis);
            myImage = itemView.findViewById(R.id.gambar);

            itemView.setOnClickListener(this);

        }

        public void bindTo(Berita currentBerita) {
            judulBerita.setText(currentBerita.getTitle());
            sumberBerita.setText(currentBerita.getName());

            Date date = null;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(currentBerita.getPublishedAt());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String formattedDate = new SimpleDateFormat("dd MMMM yyyy, K a").format(date);


            tanggalRilis.setText(formattedDate);

            Glide.with(context)
                 .load(currentBerita.getUrlToImage())
                 .skipMemoryCache(true)
                 .placeholder(R.drawable.image_loading)
                 .centerCrop()
                 .error(R.drawable.image_loading)
                 .into(myImage);
        }

        @Override
        public void onClick(View view) {
            Berita currentBerita = data.get(getAdapterPosition());
            Intent detailIntent = new Intent(context, DetailActivity.class);
            detailIntent.putExtra("id", currentBerita.getId().toString());
            detailIntent.putExtra("name", currentBerita.getName());
            detailIntent.putExtra("author", currentBerita.getAuthor());
            detailIntent.putExtra("title", currentBerita.getTitle());
            detailIntent.putExtra("description", currentBerita.getDescription());
            detailIntent.putExtra("url", currentBerita.getUrl());
            detailIntent.putExtra("urlToImage", currentBerita.getUrlToImage());
            detailIntent.putExtra("publishedAt", currentBerita.getPublishedAt());
            detailIntent.putExtra("content", currentBerita.getContent());

            context.startActivity(detailIntent);
        }
    }
}
