package com.example.fpm0322.myfirstapp.fragment.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.fpm0322.myfirstapp.R;
import com.example.fpm0322.myfirstapp.adapter.GridLayoutRecyclerAdapter;
import com.example.fpm0322.myfirstapp.adapter.MPagerAdapter;
import com.example.fpm0322.myfirstapp.bean.MenuItemBean;
import com.example.fpm0322.myfirstapp.fragment.home.GameFragment;
import com.example.fpm0322.myfirstapp.fragment.home.JokeFragment;
import com.example.fpm0322.myfirstapp.fragment.home.NearByFragment;
import com.example.fpm0322.myfirstapp.fragment.home.RecommandFragment;
import com.example.fpm0322.myfirstapp.fragment.home.SocietyFragment;
import com.example.fpm0322.myfirstapp.fragment.home.SportsFragment;
import com.example.fpm0322.myfirstapp.fragment.home.TechnologyFragment;
import com.example.fpm0322.myfirstapp.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends Fragment {

    @BindView(R.id.tab_layout)
    TabLayout tableLayout;

    @BindView(R.id.iv_more)
    ImageView ivMore;

    @BindView(R.id.viewPager)
    ViewPager mPager;

    private List<MenuItemBean> myClasses = new ArrayList<>();
    private List<MenuItemBean> otherClasses = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,view);
        initView();
        initData();
        return view;
    }

    public void initView(){
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new RecommandFragment());
        fragmentList.add(new NearByFragment());
        fragmentList.add(new GameFragment());
        fragmentList.add(new JokeFragment());
        fragmentList.add(new SocietyFragment());
        fragmentList.add(new SportsFragment());
        fragmentList.add(new TechnologyFragment());

        List<String> mTitles = new ArrayList<>();
        mTitles.add("推荐");
        mTitles.add("附近");
        mTitles.add("游戏");
        mTitles.add("搞笑");
        mTitles.add("社会");
        mTitles.add("运动");
        mTitles.add("科技");
        tableLayout.addTab(tableLayout.newTab().setText(mTitles.get(0)),true);
        tableLayout.addTab(tableLayout.newTab().setText(mTitles.get(1)));
        tableLayout.addTab(tableLayout.newTab().setText(mTitles.get(2)));
        tableLayout.addTab(tableLayout.newTab().setText(mTitles.get(3)));
        tableLayout.addTab(tableLayout.newTab().setText(mTitles.get(4)));
//        tableLayout.addTab(tableLayout.newTab().setText(mTitles.get(5)));
//        tableLayout.addTab(tableLayout.newTab().setText(mTitles.get(6)));
        FragmentManager fragmentManager = getChildFragmentManager();
        MPagerAdapter fragmentPagerAdapter = new MPagerAdapter(fragmentManager,fragmentList,mTitles);
        mPager.setAdapter(fragmentPagerAdapter);
        mPager.setOffscreenPageLimit(7);
        mPager.setCurrentItem(0);
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        tableLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //将TabLayout和ViewPager关联起来
        tableLayout.setupWithViewPager(mPager);
    }

    public void initData(){
        myClasses.add(new MenuItemBean("推荐",false));
        myClasses.add(new MenuItemBean("附近",false));
        myClasses.add(new MenuItemBean("游戏",false));
        myClasses.add(new MenuItemBean("搞笑",false));
        myClasses.add(new MenuItemBean("社会",false));

        otherClasses.add(new MenuItemBean("推荐1",false));
        otherClasses.add(new MenuItemBean("推荐2",false));
        otherClasses.add(new MenuItemBean("推荐3",false));
        otherClasses.add(new MenuItemBean("推荐4",false));
        otherClasses.add(new MenuItemBean("推荐5",false));
        otherClasses.add(new MenuItemBean("推荐6",false));
        otherClasses.add(new MenuItemBean("推荐7",false));
        otherClasses.add(new MenuItemBean("推荐8",false));
        otherClasses.add(new MenuItemBean("推荐9",false));
    }

    /**
     * 点击显示更多菜单
     */
    @OnClick(R.id.iv_more)
    public void moreMenu(){
        CommonUtils.backgroundHoleAlpha(0.5f,getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.all_menu_item,null);
        TextView tvEdit = view.findViewById(R.id.btn_edit);
        ImageView ivCalcel = view.findViewById(R.id.btn_cancel);
        RecyclerView ryMyClasses = view.findViewById(R.id.ry_my_classes);
        RecyclerView ryOtherClasses = view.findViewById(R.id.ry_other_classes);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),4);
        GridLayoutManager layoutManager1 = new GridLayoutManager(getActivity(),4);
        ryMyClasses.setLayoutManager(layoutManager);
        ryOtherClasses.setLayoutManager(layoutManager1);
        final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,true);
        popupWindow.setTouchable(false);
        popupWindow.showAtLocation(view,Gravity.TOP|Gravity.END,0,0);

        final GridLayoutRecyclerAdapter myClassesAdapter = new GridLayoutRecyclerAdapter();
        myClassesAdapter.setDatas(myClasses);
        ryMyClasses.setAdapter(myClassesAdapter);


        GridLayoutRecyclerAdapter otherClassesAdapter = new GridLayoutRecyclerAdapter();
        otherClassesAdapter.setDatas(otherClasses);
        ryOtherClasses.setAdapter(otherClassesAdapter);
        tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 1; i < myClasses.size(); i++) {
                    myClasses.get(i).setItemState(true);
                    myClassesAdapter.notifyDataSetChanged();
                }
            }
        });
        ivCalcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                CommonUtils.backgroundHoleAlpha(1.0f,getActivity());
            }
        });

    }


    public class GridLayoutRecyclerAdapter extends RecyclerView.Adapter<GridLayoutRecyclerAdapter.ViewHolder>{

        private List<MenuItemBean> menuItems;

        public void setDatas(List<MenuItemBean> menuItems){
            this.menuItems = menuItems;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_grid_menu,viewGroup,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {
            if (null != menuItems && menuItems.size()>0){
                MenuItemBean itemBean = menuItems.get(i);
                holder.tvMenuItem.setText(itemBean.getItemName());
                if(itemBean.isItemState()){
                    //如果处于编辑状态，则在左上角显示小叉号
                    holder.fmDelete.setVisibility(View.VISIBLE);
                    holder.fmDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            myClasses.remove(menuItems.get(i));
                            otherClasses.add(menuItems.get(i));
                            menuItems.get(i).setItemState(false);
                            notifyDataSetChanged();
                        }
                    });
                }else{
                    holder.fmDelete.setVisibility(View.GONE);
                }
            }
        }

        @Override
        public int getItemCount() {
            return menuItems.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            TextView tvMenuItem;

            FrameLayout fmDelete;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tvMenuItem = itemView.findViewById(R.id.tv_menu_item);
                fmDelete = itemView.findViewById(R.id.fm_delete);
            }
        }
    }
}
