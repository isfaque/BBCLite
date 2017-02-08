package com.example.apps2.bbclite.views.fragments.home;

import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.apps2.bbclite.R;
import com.example.apps2.bbclite.helpers.CustomAdapter.NoticeAdapter;
import com.example.apps2.bbclite.helpers.CustomAdapter.NoticeData;
import com.example.apps2.bbclite.presenters.HomePresenter;
import com.example.apps2.bbclite.utils.UrlUtil;
import com.example.apps2.bbclite.views.fragments.homedetail.HomeDetailFragment;

/**
 * Created by apps2 on 2/8/2017.
 */
public class HomeFragment extends android.support.v4.app.Fragment implements HomeView {

    ListView list;
    private HomePresenter presenter;

    public static String user_notice_date, user_notice_title, user_notice_detail, user_notice_imageurl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        list=(ListView) v.findViewById(R.id.list);

        presenter = new HomePresenter(this);

        if(isNetworkAvailable()){
            presenter.getNotice(UrlUtil.GET_NEWS_URL);
        }else{
          Toast.makeText(getActivity(), "Internet connectivity not available", Toast.LENGTH_SHORT).show();
        }


        return v;
    }

    @Override
    public void startGetNotice() {
        NoticeAdapter adapter=new NoticeAdapter(getActivity(), NoticeData.noticelist_notice_date, NoticeData.noticelist_notice_title, NoticeData.noticelist_notice_detail, NoticeData.noticelist_notice_imageurl);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem1= NoticeData.noticelist_notice_date.get(position);

                user_notice_date = NoticeData.noticelist_notice_date.get(position);
                user_notice_title = NoticeData.noticelist_notice_title.get(position);
                user_notice_detail = NoticeData.noticelist_notice_detail.get(position);
                user_notice_imageurl = NoticeData.noticelist_notice_imageurl.get(position);

                Toast.makeText(getActivity(),Slecteditem1,Toast.LENGTH_SHORT).show();
                startHomeDetailFragment();

            }
        });
    }

    public void startHomeDetailFragment() {
        android.support.v4.app.Fragment fr;
        fr = new HomeDetailFragment();
        FragmentManager fm = getFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.main_content, fr);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void errorGetNotice(String msg){
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
