package com.example.fpm0322.myfirstapp.fragment.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;

import com.example.fpm0322.myfirstapp.R;
import com.example.fpm0322.myfirstapp.utils.ViewPagerSlide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecommandFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommand, container, false);
        ButterKnife.bind(view);
        return view;
    }

}
