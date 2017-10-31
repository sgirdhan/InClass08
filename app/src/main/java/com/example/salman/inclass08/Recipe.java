// In Class 05
// ContactListActivity.java
// Sharan Girdhani     - 800960333
// Salman Mujtaba   - 800969897
//
// In Class 06
// Salman Mujtaba   - 800969897
//

package com.example.salman.inclass08;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Recipe implements Serializable{
    private String title, url, ingredients, thumbnail;

    public static Recipe createRecipe(JSONObject js) throws JSONException {
        Recipe re = new Recipe();
        re.setTitle(js.getString("title"));
        re.setUrl(js.getString("href"));
        re.setIngredients(js.getString("ingredients"));

        return re;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
