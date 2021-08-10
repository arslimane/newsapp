package com.example.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends BaseAdapter {
    public Context mcontext;
    public List<News> list;
    public LayoutInflater inflater;

    public Adapter(Context c, List<News> l) {
        this.mcontext = c;
        list = new ArrayList<>();
        list.addAll(l);
        inflater = LayoutInflater.from(c);

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_newsitem, null);
        CardView cardView = convertView.findViewById(R.id.carte);
        ImageView image = convertView.findViewById(R.id.image);


        Picasso.with(mcontext).load(list.get(position).getImage()).resize(100, 100).centerCrop().into(image);


        TextView author = convertView.findViewById(R.id.author);
        TextView title = convertView.findViewById(R.id.title);
        TextView type = convertView.findViewById(R.id.type);
        TextView date = convertView.findViewById(R.id.date);
        author.setText(list.get(position).getAuthor());
        title.setText(list.get(position).getWebTitle());
        date.setText(list.get(position).getWebPublicationDate());
        type.setText(list.get(position).getSectionName());
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(list.get(position).getWebUrl()));
                mcontext.startActivity(intent);
            }
        });

        return convertView;
    }

    public void clearAll() {
        list.clear();
        notifyDataSetChanged();
    }

    /**
     * Update RecyclerView data
     *
     * @param news is the data source of the adapter.
     */
    public void addAll(List<News> news) {
        list.clear();
        list.addAll(news);
        notifyDataSetChanged();
    }
}
