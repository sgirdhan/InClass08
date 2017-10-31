/*
// In Class 08
// Sharan Girdhani     - 800960333
// Salman Mujtaba   - 800969897
*/

package com.example.salman.inclass08;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecipeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class RecipeFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private ArrayList<Recipe> recipeArrayList = null;
    View mainView;
    RecyclerView recycleViewList;

    public ArrayList<Recipe> getRecipeArrayList() {
        return recipeArrayList;
    }

    public void setRecipeArrayList(ArrayList<Recipe> recipeArrayList) {
        this.recipeArrayList = recipeArrayList;
    }

    public RecipeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainView = inflater.inflate(R.layout.fragment_recipe, container, false);

        mainView.findViewById(R.id.buttonFinish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.closeDetailFragment();
            }
        });
        hideMostData();
        return mainView;
    }

    public void loadRecyclerView(ArrayList<Recipe> recipeArrayList) {
        if(recipeArrayList.size() == 0) {
            Toast.makeText(getContext(),"No recipe found",Toast.LENGTH_LONG).show();
            mListener.closeDetailFragment();
        }
        this.recipeArrayList = recipeArrayList;
        showMostData();
        RecycleViewRecipeAdapter recycleAdapter = new RecycleViewRecipeAdapter(getContext(),recipeArrayList);
        recycleAdapter.notifyDataSetChanged();
        recycleViewList = (RecyclerView) mainView.findViewById(R.id.recipeDetail);
        recycleViewList.setAdapter(recycleAdapter);
        recycleViewList.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
    }

    public void hideMostData() {
        mainView.findViewById(R.id.recipeDetail).setVisibility(View.INVISIBLE);
        mainView.findViewById(R.id.buttonFinish).setVisibility(View.INVISIBLE);
        mainView.findViewById(R.id.textViewLoad).setVisibility(View.VISIBLE);
        mainView.findViewById(R.id.progressBar2).setVisibility(View.VISIBLE);
    }

    public void showMostData() {
        mainView.findViewById(R.id.recipeDetail).setVisibility(View.VISIBLE);
        mainView.findViewById(R.id.buttonFinish).setVisibility(View.VISIBLE);
        mainView.findViewById(R.id.textViewLoad).setVisibility(View.INVISIBLE);
        mainView.findViewById(R.id.progressBar2).setVisibility(View.INVISIBLE);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
        void closeDetailFragment();
    }
}
