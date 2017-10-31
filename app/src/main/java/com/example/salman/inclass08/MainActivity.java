/*
// In Class 08
// Sharan Girdhani     - 800960333
// Salman Mujtaba   - 800969897
*/

package com.example.salman.inclass08;

import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.salman.inclass08.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecipePuppyFragment.OnFragmentInteractionListener, RecipeFragment.OnFragmentInteractionListener, GetRecipeAsyncTask.IData {

    private ActivityMainBinding binding;
    RecipeFragment rf;
    RecipePuppyFragment recipePuppyFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        recipePuppyFragment = new RecipePuppyFragment();
        rf = new RecipeFragment();
        getSupportActionBar().setTitle("Recipe Puppy");

        getSupportFragmentManager().beginTransaction()
                .add(R.id.mainLayout, recipePuppyFragment)
                .commit();
    }

    @Override
    public void setUpRequestParam(RequestParams requestParam) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainLayout, rf)
                .addToBackStack(null)
                .commit();
        getSupportActionBar().setTitle("Recipe");
        new GetRecipeAsyncTask(MainActivity.this).execute(requestParam);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public void closeDetailFragment() {
        onBackPressed();
    }

    @Override
    public void updateData(ArrayList<Recipe> recipeList) {
        //Toast.makeText(this, recipeList.toString(), Toast.LENGTH_SHORT).show();
        rf.loadRecyclerView(recipeList);
    }
}
