package net.levinh.hasbarintest;

import android.app.Activity;
import android.media.Image;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import net.levinh.hasbarintest.MainActivity;
import net.levinh.hasbarintest.R;
import net.levinh.hasbarintest.datastore.Model.Movie;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Levin on 29/02/2016.
 */
public class ListAdapter extends ArrayAdapter<Movie> implements Filterable{

    Activity context=null;
    List<Movie> listMovie = null;
    int layoutId;
    public ImageLoader imageLoader;

    public ListAdapter(Activity context, int layoutId, List<Movie> arr){
        super(context, layoutId, arr);
        this.context=context;
        this.layoutId=layoutId;
        this.listMovie = arr;
        imageLoader=new ImageLoader(context.getApplicationContext());
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewholder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
            viewholder =  new ViewHolder();
            viewholder.imgRibbon = (ImageView) convertView.findViewById(R.id.imgRibbon);
            viewholder.imgPoster = (ImageView) convertView.findViewById(R.id.imgPoster);
            viewholder.txtTitle=(TextView) convertView.findViewById(R.id.txtTitleMovie);
            viewholder.txtRate = (TextView)convertView.findViewById(R.id.txtRate);
            viewholder.Watch_Now = (LinearLayout)convertView.findViewById(R.id.Watch_Now);
            convertView.setTag(viewholder);
        }else
        {
            viewholder = (ViewHolder)convertView.getTag();
        }
        final Movie m=listMovie.get(position);
        viewholder.txtTitle.setText(m.getOriginalTitle());
        Date release_date = m.getReleaseDate();
        SimpleDateFormat df = new SimpleDateFormat("(yyyy)");
        String year = String.valueOf(df.format(release_date));
        viewholder.txtTitle.setText(Html.fromHtml("<b>" + m.getOriginalTitle() + "</b> <font color=\"#cccccc\">" + year + "</font>"));
        viewholder.txtRate.setText(String.valueOf(m.getVoteAverage()));
        String URL = "http://image.tmdb.org/t/p/w154"+m.getPosterPath();
        imageLoader.DisplayImage(URL, viewholder.imgPoster);
        if(Count_Month(release_date)>3)
            viewholder.Watch_Now.setVisibility(View.VISIBLE);
        else viewholder.Watch_Now.setVisibility(View.INVISIBLE);
        if(m.getTagClick())
            viewholder.imgRibbon.setVisibility(View.VISIBLE);
        else viewholder.imgRibbon.setVisibility(View.INVISIBLE);
        return convertView;
    }

    public long Count_Month(Date d){
        long Months=0;
        Date d2 = new Date();
        try {
            long diff = d2.getTime() - d.getTime();
            long diffDays = diff / (24 * 60 * 60 * 1000);
            Months = diffDays/30;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Months;
    }

    static class ViewHolder {
        ImageView imgRibbon;
       ImageView imgPoster;
        TextView txtTitle;
        TextView txtRate;
        LinearLayout Watch_Now;
    }


}

