package com.example.apps2.bbclite.presenters;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.apps2.bbclite.helpers.CustomAdapter.NoticeData;
import com.example.apps2.bbclite.helpers.VolleyLibrary.AppController;
import com.example.apps2.bbclite.views.fragments.home.HomeView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by apps2 on 2/8/2017.
 */
public class HomePresenter {
    private HomeView view;

    private static final String TAG = "REQ";
    public static String SERVICE_STATUS = "";
    private static final String TAG_CITY = "articles";
    JSONArray user;
    JSONObject e,f;
    JSONArray city;

    public HomePresenter(HomeView view) {
        this.view = view;
    }

    public void getNotice(String url) {

        StringRequest req = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d(TAG, response.toString());

                        String str_response = response;

                        SERVICE_STATUS = "TRUE";

                        NoticeData.noticelist_notice_date.clear();
                        NoticeData.noticelist_notice_title.clear();
                        NoticeData.noticelist_notice_detail.clear();

                        try {
                            JSONObject jsonObj = new JSONObject(str_response);
                            city = jsonObj.getJSONArray(TAG_CITY);
                            for (int i = 0; i < city.length(); i++) {
                                e = city.getJSONObject(i);

                                String Pick_Id = e.getString("author");
                                Log.i("Author is", Pick_Id);

                                String date = e.getString("publishedAt");
                                String title = e.getString("title");
                                String detail = e.getString("description");
                                String imageurl = e.getString("urlToImage");
                                NoticeData.noticelist_notice_date.add(date);
                                NoticeData.noticelist_notice_title.add(title);
                                NoticeData.noticelist_notice_detail.add(detail);
                                NoticeData.noticelist_notice_imageurl.add(imageurl);
                            }
                            view.startGetNotice();
                            return;
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }




                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                view.errorGetNotice("error response");
                return;
            }
        });

        AppController.getInstance().addToRequestQueue(req);

    }


}
