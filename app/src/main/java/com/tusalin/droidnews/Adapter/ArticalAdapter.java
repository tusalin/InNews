package com.tusalin.droidnews.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tusalin.droidnews.Bean.GankNews;
import com.tusalin.droidnews.FragmentType;
import com.tusalin.innews.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tusalin on 9/6/2016.
 */

public class ArticalAdapter extends RecyclerView.Adapter<ArticalAdapter.ArticalViewHolder> {

    private FragmentType fragmentType;
    private List<GankNews.Results> articalResults = new ArrayList<>();
    private List<GankNews.Results> girlResults = new ArrayList<>();
    private Context context;

    public ArticalAdapter(FragmentType type, Context context){
        fragmentType = type;
        this.context = context;
    }

    public void setArticalResults(GankNews gankNews){
        articalResults.clear();
        articalResults.addAll(gankNews.getResults());
    }

    public void setGirlResults(GankNews gankNews){
        girlResults.clear();
        girlResults.addAll(gankNews.getResults());
    }

    @Override
    public int getItemCount() {
       return articalResults.size();
//        return 20;
    }

    @Override
    public void onBindViewHolder(ArticalViewHolder holder, int position) {
        GankNews.Results artical = articalResults.get(position);
        GankNews.Results girl = girlResults.get((int) (Math.random()*girlResults.size()));
       /* holder.textViewTitle.setText("a_grid_text");
        holder.textViewDate.setText("grid_date");
        holder.imageView.setImageResource(R.drawable.ic_menu_user);*/
        holder.textViewDate.setText(artical.getCreatedAt().substring(0,10));
        holder.textViewTitle.setText(artical.getDesc());
        String girlsUrl = girl.getUrl();
        Picasso.with(context)
                .load(girlsUrl)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .fit()
                .centerCrop()
                .tag(holder.imageView.getContext())
                .into(holder.imageView);
    }

    @Override
    public ArticalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (fragmentType){
            case Android:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artical_item_linear,
                        parent,false);
                break;
            case xiatuijian:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artical_item_grid,
                        parent,false);
                break;
            case tuozhanziyuan:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artical_item_grid,
                        parent,false);
                break;
        }


        return new ArticalViewHolder(view);
    }

    public class ArticalViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView textViewTitle;
        private TextView textViewDate;

        public ArticalViewHolder(View view){
            super(view);
            imageView = (ImageView) view.findViewById(R.id.cardview_artical_image);
            textViewDate = (TextView) view.findViewById(R.id.cardview_artical_date);
            textViewTitle = (TextView) view.findViewById(R.id.cardview_artical_title);
        }
    }
}
