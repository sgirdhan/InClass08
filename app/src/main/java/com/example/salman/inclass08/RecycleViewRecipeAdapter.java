/*
// In Class 08
// Sharan Girdhani     - 800960333
// Salman Mujtaba   - 800969897
*/

package com.example.salman.inclass08;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by sharangirdhani on 10/30/17.
 */

public class RecycleViewRecipeAdapter extends RecyclerView.Adapter<RecycleViewRecipeAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Recipe> recipeArrayList;

    public RecycleViewRecipeAdapter(Context mContext, ArrayList<Recipe> appArrayList) {
        this.mContext = mContext;
        this.recipeArrayList = appArrayList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView title;
        public ImageView image ;
        public TextView ingreds;
        public TextView url;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.textViewTitle);
            image = (ImageView) itemView.findViewById(R.id.imageView);
            ingreds = (TextView) itemView.findViewById(R.id.textViewIngredient);
            url = (TextView) itemView.findViewById(R.id.textViewURL);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View itemView = layoutInflater.inflate(R.layout.recipe_horizontal_recycleview,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Recipe recipe = recipeArrayList.get(position);
        holder.title.setText(recipe.getTitle());
        String thumbnail = recipe.getThumbnail();
        if(!thumbnail.isEmpty())
            Picasso.with(mContext).load(thumbnail).into(holder.image);

        holder.ingreds.setText(recipe.getIngredients());
        holder.url.setText(recipe.getUrl());
    }

    @Override
    public int getItemCount() {
        return recipeArrayList.size();
    }
}
