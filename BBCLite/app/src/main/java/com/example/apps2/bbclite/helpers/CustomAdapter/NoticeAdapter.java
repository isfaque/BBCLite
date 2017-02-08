package com.example.apps2.bbclite.helpers.CustomAdapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.apps2.bbclite.R;
import com.example.apps2.bbclite.helpers.VolleyLibrary.AppController;

import java.util.List;

/**
 * Created by apps2 on 2/8/2017.
 */
public class NoticeAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final List<String> noticelist_notice_date;
    private final List<String> noticelist_notice_title;
    private final List<String> noticelist_notice_detail;
    private final List<String> noticelist_notice_imageurl;

    public NoticeAdapter(Activity context, List<String> noticelist_notice_date, List<String> noticelist_notice_title, List<String> noticelist_notice_detail, List<String> noticelist_notice_imageurl) {
        super(context, R.layout.mylist, noticelist_notice_date);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.noticelist_notice_date=noticelist_notice_date;
        this.noticelist_notice_title=noticelist_notice_title;
        this.noticelist_notice_detail=noticelist_notice_detail;
        this.noticelist_notice_imageurl=noticelist_notice_imageurl;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist, null,true);



        TextView txt_noticelist_notice_date = (TextView) rowView.findViewById(R.id.txt_user_notice_date);
        TextView txt_noticelist_notice_title = (TextView) rowView.findViewById(R.id.txt_user_notice_title);
        TextView txt_noticelist_notice_detail = (TextView) rowView.findViewById(R.id.txt_user_notice_detail);
        final ImageView notice_image = (ImageView) rowView.findViewById(R.id.icon);

        txt_noticelist_notice_date.setText(noticelist_notice_date.get(position));
        txt_noticelist_notice_title.setText(noticelist_notice_title.get(position));
        txt_noticelist_notice_detail.setText(noticelist_notice_detail.get(position));

        ImageLoader imageLoader = AppController.getInstance().getImageLoader();
        imageLoader.get(noticelist_notice_imageurl.get(position), new ImageLoader.ImageListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Image Load Error: ",error.getMessage());
            }

            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                if (response.getBitmap() != null) {
                    notice_image.setImageBitmap(response.getBitmap());
                }
            }
        });

        return rowView;

    };
}
