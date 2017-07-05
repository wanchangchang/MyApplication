package com.example.administrator.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.commenttool.XListView;


public class AbleFragment extends Fragment implements  XListView.IXListViewListener,AdapterView.OnItemClickListener{
    private static final String ARG_PARAM1 = "param1";
    private XListView list;
    private String mParam1;
    private Context context;

    @SuppressLint("HandlerLeak")



    public AbleFragment() {
        // Required empty public constructor
    }

    public static AbleFragment newInstance(String param1) {
        AbleFragment fragment = new AbleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
        this.context = getActivity();
           }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_able, container, false);
        list = (XListView)mView.findViewById(R.id.test_fragment);
        list.setXListViewListener(this);
        list.setOnItemClickListener(this);
        AdapterList adapterList = new AdapterList(context,mParam1);
        list.setAdapter(adapterList);
        return mView;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent();
        intent.setClass(context,DetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onRefresh() {

    }


   }
