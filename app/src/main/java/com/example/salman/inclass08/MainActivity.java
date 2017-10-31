package com.example.salman.inclass08;

import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.salman.inclass08.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecipePuppyFragment.OnFragmentInteractionListener{

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        RecipePuppyFragment recipePuppyFragment = new RecipePuppyFragment();
        getSupportActionBar().setTitle("Recipe Puppy");

        getSupportFragmentManager().beginTransaction()
                .add(R.id.mainLayout,new RecipePuppyFragment())
                .addToBackStack("new")
                .commit();
    }

    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStackImmediate();
            if (!(getSupportFragmentManager().getBackStackEntryCount() > 0)) {
//                findViewById(R.id.activ).setVisibility(View.VISIBLE);
                return;
            }
        }
        else {
            super.onBackPressed();
        }
//        findViewById(R.id.container).setVisibility(View.GONE);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void setUpRequestParam(RequestParams requestParam) {
        new GetRecipeAsyncTask(MainActivity.this).execute(requestParam);
    }

//    Fetch data from database
//    List<Music> fetchdata(){
//        musicList2 = new ArrayList<>();
//        musicList2 = dm.getAll();
//        return musicList2;
//    }
}
