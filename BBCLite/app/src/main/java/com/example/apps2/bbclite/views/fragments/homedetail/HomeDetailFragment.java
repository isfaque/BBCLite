package com.example.apps2.bbclite.views.fragments.homedetail;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.apps2.bbclite.R;
import com.example.apps2.bbclite.helpers.VolleyLibrary.AppController;
import com.example.apps2.bbclite.views.fragments.home.HomeFragment;

/**
 * Created by apps2 on 2/8/2017.
 */
public class HomeDetailFragment extends android.support.v4.app.Fragment {

    TextView title, detail;
    ImageView imageurl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_detail, container, false);
        title = (TextView) v.findViewById(R.id.fragment_home_detail_tv_title);
        detail = (TextView) v.findViewById(R.id.fragment_home_detail_tv_detail);
        imageurl = (ImageView) v.findViewById(R.id.fragment_home_detail_iv_image);

        title.setText(HomeFragment.user_notice_title);
        detail.setText(HomeFragment.user_notice_detail);

        ImageLoader imageLoader = AppController.getInstance().getImageLoader();
        imageLoader.get(HomeFragment.user_notice_imageurl, new ImageLoader.ImageListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Image Load Error: ",error.getMessage());
            }

            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                if (response.getBitmap() != null) {
                    imageurl.setImageBitmap(response.getBitmap());
                }
            }
        });
        return v;
    }
}
