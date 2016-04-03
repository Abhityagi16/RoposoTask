package com.abhishek.roposotask;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.abhishek.roposotask.Model.Author;
import com.abhishek.roposotask.Model.Story;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private HashMap<String, Author> mAuthorMap;
    private ArrayList<Author> mAuthorList;
    private ArrayList<Story> mStoryList;

    private RecyclerView mRecyclerView;
    private StoryListAdapter mAdapter;
    private StaggeredGridLayoutManager mStaggerLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuthorMap = new HashMap<>();
        mStoryList = new ArrayList<>();


        String json = loadJSONFromAsset();
        Log.d("MainActivity", ""+json.length());
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

        mRecyclerView = (RecyclerView) findViewById(R.id.story_list);
        mAdapter = new StoryListAdapter(MainActivity.this, mStoryList, mAuthorMap);
        mStaggerLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager
                .VERTICAL);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mStaggerLayoutManager);

        StoryListAdapter.OnItemClickListener onItemClickListener = new StoryListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, String tag) {
                Story story = mStoryList.get(position);
                String authorId = story.getAuthorId();
                Author author = mAuthorMap.get(authorId);

                Log.d("OnClick", "click at pos " + position + " " + tag);

                if(tag != null && tag.equals(StoryListAdapter.FOLLOW_TAG)) {
                    if(author.isFollowing()) {
                        author.decrementFollower();
                    }
                    else {
                        author.incrementFollower();
                    }
                    author.toggleFollowing();
//                    mAdapter.toggleFollowing(authorId);

                    //This is the best option I can think of to avoid notifydatasetchanged() call
                    mAdapter.notifyItemRangeChanged(position-1, 4);
                }
                else {
                    Toast.makeText(MainActivity.this, "Clicked on card " + position, Toast
                            .LENGTH_LONG);
                }
            }
        };
        mAdapter.setOnItemClickListener(onItemClickListener);

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
                boolean isFollowing = Boolean.parseBoolean(obj.getString("is_following"));
                long followers = Long.parseLong(obj.getString("followers"));
                long following = Long.parseLong(obj.getString("following"));

                Author author = new Author(id, userName, about, imageUrl, profileUrl, handle,
                        followers, following, isFollowing);
                mAuthorMap.put(id, author);
//                mAuthorList.add(author);

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

                Story story = new Story(id, title, authodId, description, imageUrl, verb,
                        likesCount,
                        commentCount);
                mStoryList.add(story);

            }
        }
    }

}
