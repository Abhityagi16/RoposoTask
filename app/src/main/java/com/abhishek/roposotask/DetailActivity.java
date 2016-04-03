package com.abhishek.roposotask;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhishek.roposotask.Model.Author;
import com.abhishek.roposotask.Model.Story;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    public static final String FOLLOW_PRESSED = "follow.pressed";


    private Author mAuthor;
    private Story mStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        toolbar.setTitle(getString(R.string.app_name));
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuthor = (Author) getIntent().getSerializableExtra(MainActivity.AUTHOR_EXTRA_KEY);
        mStory = (Story) getIntent().getSerializableExtra(MainActivity.STORY_EXTRA_KEY);

        ImageView storyImage = (ImageView) findViewById(R.id.detail_story_image);
        TextView storyTitle = (TextView) findViewById(R.id.detail_story_title);
        ImageView profileImage = (ImageView) findViewById(R.id.profile_image);
        TextView authorName = (TextView) findViewById(R.id.author_name);
        TextView handle = (TextView) findViewById(R.id.handle_name);
        final TextView numOfFollowers = (TextView) findViewById(R.id.num_of_followers);
        TextView numOfFollowing = (TextView) findViewById(R.id.num_of_followings);
        TextView aboutAuthor = (TextView) findViewById(R.id.about_author);
        TextView description = (TextView) findViewById(R.id.detail_description);
        final Button followButton = (Button) findViewById(R.id.detail_follow_button);

        Picasso.with(this).load(mStory.getImageUrl()).into(storyImage);
        Picasso.with(this).load(mAuthor.getImageUrl()).into(profileImage);
        storyTitle.setText(mStory.getTitle());
        description.setText(mStory.getDescription());
        authorName.setText(mAuthor.getUserName());
        handle.setText(mAuthor.getHandle());
        numOfFollowers.setText(mAuthor.getFollowers() + " Followers");
        numOfFollowing.setText(mAuthor.getFollowing() + " Following");
        aboutAuthor.setText(mAuthor.getAbout());

        String followText = mAuthor.isFollowing() ? "Following" : "Follow";
        followButton.setText(followText);

        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuthor.toggleFollowing();
                String followText = mAuthor.isFollowing() ? "Following" : "Follow";
                followButton.setText(followText);
                numOfFollowers.setText(mAuthor.getFollowers() + " Followers");

                Intent intent = new Intent(FOLLOW_PRESSED);
                intent.putExtra("author", mAuthor.getId());
                sendBroadcast(intent);


            }
        });


    }

}
