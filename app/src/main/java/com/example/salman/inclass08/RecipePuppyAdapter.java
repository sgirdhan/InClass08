/*
// In Class 08
// Sharan Girdhani     - 800960333
// Salman Mujtaba   - 800969897
*/

package com.example.salman.inclass08;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;

/**
 * Created by KashishSyeda on 10/30/2017.
 */


public class RecipePuppyAdapter extends RecyclerView.Adapter<RecipePuppyAdapter.MusicRecyclerViewHolder> {

//    ArrayList<Integer> contacts;
    Context context;
    IMusicAdapter iMusicAdapter ;
    int mresource;
    ArrayList<String> recipeMenu;
    int count=0;
    LinearLayoutManager layoutManager;

    RecyclerView myRecyclerView;

    public RecipePuppyAdapter(ArrayList<String> recipeMenu, Context context,IMusicAdapter iMusicAdapter) {

        //mresource is row inflator
        this.context = context;
        this.recipeMenu = recipeMenu;
        this.iMusicAdapter = iMusicAdapter;
    }

    @Override
    public MusicRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row,parent,false);
        MusicRecyclerViewHolder musicRecyclerViewHolder = new MusicRecyclerViewHolder(view);
        return musicRecyclerViewHolder;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.myRecyclerView = recyclerView;
    }

    @Override
    public void onBindViewHolder(final MusicRecyclerViewHolder holder, final int position) {


        final String ingr = recipeMenu.get(position);

        holder.edtTextIngredient.setText(ingr);

        holder.imageButtonDynamic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recipeMenu.remove(position);
                //iMusicAdapter.setAdapterAndNotify();

                RecipePuppyAdapter contactListAdapterRecyclerVIew = new RecipePuppyAdapter(recipeMenu, context, new RecipePuppyFragment());
                myRecyclerView.setAdapter(contactListAdapterRecyclerVIew);
                layoutManager = new LinearLayoutManager(context);
                myRecyclerView.setLayoutManager(layoutManager);
                contactListAdapterRecyclerVIew.notifyDataSetChanged();
            }
        });



//        holder.textViewName.setText(contact.getFirstName()+" "+contact.getLastName());
//        holder.textViewPhone.setText(contact.getPhone());
//        Picasso.with(context).load(contact.getAvatar()).into(holder.avatar);

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                iMusicAdapter.goToDetailsFragment(position);
//            }
//        });


    }



    @Override
    public int getItemCount() {
        return recipeMenu.size();
    }

    public static  class MusicRecyclerViewHolder extends RecyclerView.ViewHolder{

//        TextView textViewName;
//        TextView textViewPhone;
//        ImageView avatar;
        ImageButton imageButtonPermanent, imageButtonDynamic;
        EditText edtTextIngredient;

        public MusicRecyclerViewHolder(View itemView) {
            super(itemView);

            imageButtonPermanent = (ImageButton) itemView.findViewById(R.id.imageButtonAddPermanent);
            imageButtonDynamic = (ImageButton) itemView.findViewById(R.id.imageButtonDynamic);

            edtTextIngredient = (EditText) itemView.findViewById(R.id.editTextMenuDynamic);
//            textViewPhone = (TextView) itemView.findViewById(R.id.textPhone);
//            avatar = (ImageView) itemView.findViewById(R.id.imageView2);
        }
    }

    interface IMusicAdapter
    {
        void setAdapterAndNotify();
    }
}
