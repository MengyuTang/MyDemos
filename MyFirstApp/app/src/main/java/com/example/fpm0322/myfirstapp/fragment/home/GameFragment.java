package com.example.fpm0322.myfirstapp.fragment.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fpm0322.myfirstapp.R;

import butterknife.ButterKnife;

public class GameFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        mView = view;
        ButterKnife.bind(view);
        return view;
    }

    public void initView(){

    }

    public View mView;
    @Override
    public View getView(){
        return mView;
    }
}
