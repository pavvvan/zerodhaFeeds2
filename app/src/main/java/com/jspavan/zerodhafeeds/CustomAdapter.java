package com.jspavan.zerodhafeeds;

/**
 * Created by pavan on 7/10/2018.
 */
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import  com.jspavan.zerodhafeeds.R;
import com.jspavan.zerodhafeeds.Article;


import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    Context c;
    ArrayList<Article> articles;

    public CustomAdapter(Context c, ArrayList<Article> articles) {
        this.c = c;
        this.articles = articles;
    }

    @Override
    public int getCount() {
        return articles.size();
    }

    @Override
    public Object getItem(int position) {
        return articles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            convertView= LayoutInflater.from(c).inflate(R.layout.model,parent,false);
        }

        TextView titleTxt= (TextView) convertView.findViewById(R.id.titleTxt);
        TextView descTxt= (TextView) convertView.findViewById(R.id.descTxt);
        TextView dateTxt= (TextView) convertView.findViewById(R.id.dateTxt);

        Article article= (Article) this.getItem(position);

        final String title=article.getTitle();
        final String desc=article.getDescription();
        final String date=article.getDate();
        final String guid=article.getGuid();
        final String link=article.getLink();

        titleTxt.setText(title);
        descTxt.setText(Html.fromHtml(desc));
        dateTxt.setText(date);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openDetailActivity(title,desc,date,guid,link);
            }
        });

        return convertView;
    }
    private void openDetailActivity(String...details)
    {
        Intent i=new Intent(c, DetailActivity.class);

        i.putExtra("TITLE_KEY",details[0]);
        i.putExtra("DESC_KEY",details[1]);
        i.putExtra("DATE_KEY",details[2]);
        i.putExtra("GUID_KEY",details[3]);
        i.putExtra("LINK_KEY",details[4]);

        c.startActivity(i);
    }
}