package com.yadevapp.listviewtutorial.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.yadevapp.listviewtutorial.R;
import com.yadevapp.listviewtutorial.element.Movie;

import java.util.ArrayList;

/**
 * Created by abdoulaye on 7/7/16.
 */
public class MovieAdapter extends BaseAdapter implements ListAdapter {
    private final String TAG = getClass().getSimpleName();
    private ArrayList<Movie> mMovieArrayList;
    private Context mContext;

    /**
     * contructor
     * @param context
     * @param movieArrayList
     */
    public MovieAdapter(Context context, ArrayList<Movie> movieArrayList) {
        mContext = context;
        mMovieArrayList = movieArrayList;
    }

    /**
     *
     * @return the lengh of the list
     * here the lengh equal the number of movie
     */
    @Override
    public int getCount() {
        return mMovieArrayList.size();
    }

    /**
     *
     * @param i
     * @return the movie at that position
     */
    @Override
    public Object getItem(int i) {
        return mMovieArrayList.get(i);
    }

    /**
     *
     * @param i
     * @returnthe movie id in the listview
     * here the movie id is equel to his position in the listview
     * */
    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Log.d(TAG, "getView position = " + i);
        MovieAdapterViewHolder movieAdapterViewHolder;

        if (view == null) {
            //if view null inflate the view
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.item_listview, viewGroup, false);
            //instanciate the view holder
            movieAdapterViewHolder = new MovieAdapterViewHolder(view);
            //set the view holder has view's tag
            view.setTag(movieAdapterViewHolder);
        } else {
            //the view is not null, we reuse (no need to inflate)
            //we get the view holder from the view's tag
            movieAdapterViewHolder = (MovieAdapterViewHolder) view.getTag();
        }
        //get the movie of that position
        Movie movie = mMovieArrayList.get(i);
        //get the row textviews
        //we don't need to do the findviewById anymore
        //TextView titleTextView = (TextView) view.findViewById(R.id.item_listview_title);
        //TextView yearTextView = (TextView) view.findViewById(R.id.item_listview_year);
        //TextView typeTextView = (TextView) view.findViewById(R.id.item_listview_type);

        //update the textviews
        //the textviews retrieved from the viewHolder
        movieAdapterViewHolder.getTitleTextView().setText(movie.getmName());
        movieAdapterViewHolder.getYearTextView().setText(String.valueOf(movie.getmYear()));//we must convert the  year (int) to string
        movieAdapterViewHolder.getTypetextView().setText(movie.getmType());
        
        //return the view
        return view;
    }


    /**
     * here is the viewholder class
     * the goal of this class is to prevent
     * the findViewById call in the getView (findviewbyid takes time because of parsing)
     */
    public class MovieAdapterViewHolder {
        private View listViewRow;
        private TextView titleTextView;
        private TextView yearTextView;
        private TextView typetextView;

        public MovieAdapterViewHolder (View row) {
            listViewRow = row;
            titleTextView = (TextView) row.findViewById(R.id.item_listview_title);
            yearTextView = (TextView) row.findViewById(R.id.item_listview_year);
            typetextView = (TextView) row.findViewById(R.id.item_listview_type);
        }

        //getters
        public TextView getTitleTextView() {
            return titleTextView;
        }

        public TextView getYearTextView() {
            return yearTextView;
        }

        public TextView getTypetextView() {
            return typetextView;
        }
    }
}
