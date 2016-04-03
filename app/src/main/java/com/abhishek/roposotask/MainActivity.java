package com.abhishek.roposotask;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.abhishek.roposotask.Model.Author;
import com.abhishek.roposotask.Model.Story;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Author> mAuthorList;
    private ArrayList<Story> mStoryList;

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String json = loadJSONFromAsset();
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            createStoryAndAuthorList(jsonArray);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("jsonData.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void createStoryAndAuthorList(JSONArray jsonArray) throws JSONException{
        for(int i=0; i<jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            if(obj.has("username")) {
                String userName = obj.getString("username");
                String handle = obj.getString("handle");
                String about = obj.getString("about");
                String id = obj.getString("id");
                String imageUrl = obj.getString("image");
                String profileUrl = obj.getString("url");
                long followers = Long.parseLong(obj.getString("followers"));
                long following = Long.parseLong(obj.getString("following"));

                Author author = new Author(id, userName, about, imageUrl, profileUrl, handle,
                        followers, following);
                mAuthorList.add(author);

            }
            else {
                String description = obj.getString("description");
                String id = obj.getString("id");
                String verb = obj.getString("verb");
                String title = obj.getString("title");
                String imageUrl = obj.getString("si");
                String authodId = obj.getString("db");
                long likesCount = Long.parseLong(obj.getString("likes_count"));
                long commentCount = Long.parseLong(obj.getString("comment_count"));

                Story story = new Story(id, title, authodId, imageUrl, verb, likesCount, commentCount);
                mStoryList.add(story);

            }
        }
    }

}
