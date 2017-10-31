package com.example.salman.inclass08;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sharangirdhani on 10/30/17.
 */

public class RecipeUtil {
    static public class RecipesJSONParser {
        static ArrayList<Recipe> parseRecipies(String in) throws JSONException {
            ArrayList<Recipe> recipeList = new ArrayList<>();
            JSONObject root = new JSONObject(in);
            JSONArray recipeJSONArray = root.getJSONArray("results");

            for (int i = 0; i < recipeJSONArray.length(); i++) {
                JSONObject recipeObject = recipeJSONArray.getJSONObject(i);
                recipeList.add(Recipe.createRecipe(recipeObject));
            }
            return recipeList;
        }
    }
}