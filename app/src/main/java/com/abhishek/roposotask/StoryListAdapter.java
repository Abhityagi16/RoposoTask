package com.abhishek.roposotask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.abhishek.roposotask.Model.Author;
import com.abhishek.roposotask.Model.Story;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by abhishektyagi on 03/04/16.
 */
public class StoryListAdapter extends RecyclerView.Adapter<StoryListAdapter.ViewHolder>{

    public static final String FOLLOW_TAG = "follow.button";

    Context mContext;
    private ArrayList<Story> mStoryList;
    private HashMap<String, Author> mAuthorMap;
    private OnItemClickListener mItemClickListener;

    public StoryListAdapter(Context context, ArrayList<Story> storyList, HashMap<String, Author>
            map) {
        this.mContext = context;
        this.mAuthorMap = map;
        this.mStoryList = storyList;
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public RelativeLayout mainHolder;
        public RelativeLayout authorNameHolder;
        public TextView title, verb, description, authorName;
        public ImageView storyImage;
        public Button follow;



        public ViewHolder(View itemView) {
            super(itemView);
            mainHolder = (RelativeLayout) itemView.findViewById(R.id.main_holder);
            authorName = (TextView) itemView.findViewById(R.id.author_name);
            title = (TextView) itemView.findViewById(R.id.story_title);
            verb = (TextView) itemView.findViewById(R.id.verb);
            description = (TextView) itemView.findViewById(R.id.description);
            follow = (Button) itemView.findViewById(R.id.follow_button);
            follow.setTag(FOLLOW_TAG);
            authorNameHolder = (RelativeLayout) itemView.findViewById(R.id.authorNameHolder);

            storyImage = (ImageView) itemView.findViewById(R.id.story_image);

            mainHolder.setOnClickListener(this);
            follow.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            if(v.getId() == follow.getId()) {
                mItemClickListener.onItemClick(follow, getAdapterPosition(), FOLLOW_TAG);
            }
            else {
                if(mItemClickListener != null) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), null);
                }
            }



        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, String tag);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public StoryListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_activity_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final StoryListAdapter.ViewHolder holder, int position) {
        Story story = mStoryList.get(position);

        String authorId = story.getAuthorId();
        final Author author = mAuthorMap.get(authorId);

        String followText = author.isFollowing() ? "Following" : "Follow";
        holder.title.setText(story.getTitle());
//        holder.follow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                author.toggleFollowing();
//
//                holder.follow.setText(followText);
//
//            }
//        });
        holder.follow.setText(followText);
        holder.verb.setText(story.getVerb());
        holder.authorName.setText(author.getUserName());
        holder.description.setText(story.getDescription());

        Uri uri = Uri.parse(story.getImageUrl());
        Picasso.with(mContext).load(uri).into(holder.storyImage);
    }

    @Override
    public int getItemCount() {
        return mStoryList.size();
    }

    public void toggleFollowing(String authorId) {
        mAuthorMap.get(authorId).toggleFollowing();
    }
}
