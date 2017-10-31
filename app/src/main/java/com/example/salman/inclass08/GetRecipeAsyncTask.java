/*
// In Class 08
// Sharan Girdhani     - 800960333
// Salman Mujtaba   - 800969897
*/

package com.example.salman.inclass08;

import android.os.AsyncTask;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * Created by sharangirdhani on 10/30/17.
 */

public class GetRecipeAsyncTask extends AsyncTask<RequestParams, Integer, ArrayList<Recipe>> {
    final Integer MAX_PROGRESS = 3;
    final Integer PROGRESS_START = 1;
    final Integer PROGRESS_MID = 2;

    IData activity;
    RequestParams requesParams;

    public GetRecipeAsyncTask(IData activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

//        pb.setMax(MAX_PROGRESS);
//        pb.setProgress(0);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        //pb.setProgress(values[0]);
    }

    @Override
    protected ArrayList<Recipe> doInBackground(RequestParams... param) {
        BufferedReader reader = null;
        try {
            String url = param[0].getEncodedUrl();

            HttpURLConnection con = param[0].setupConnection();
            con.connect();
            int statusCode = con.getResponseCode();
            if(statusCode == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb = new StringBuilder();
                publishProgress(PROGRESS_START);
                String line = reader.readLine();
                while(line != null) {
                    sb.append(line);
                    line = reader.readLine();
                }
                publishProgress(PROGRESS_MID);
                return RecipeUtil.RecipesJSONParser.parseRecipies(sb.toString());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Recipe> result) {
        super.onPostExecute(result);
        activity.updateData(result);
    }

    static public interface IData{
        void updateData(ArrayList<Recipe> recipeList);
    }

}