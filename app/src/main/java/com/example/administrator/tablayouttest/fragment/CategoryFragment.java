package com.example.administrator.tablayouttest.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.tablayouttest.R;
import com.example.administrator.tablayouttest.activity.MyApplication;
import com.example.administrator.tablayouttest.adapter.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * Created by Administrator on 2017/10/30.
 */

public class CategoryFragment extends BaseFragment {
    private RecyclerAdapter adapter;
    private List<String> mlist;
    private RecyclerView recyclerView;
    @Override
    protected void loadData() {
        Toast.makeText(getContext(),"Fragment头条加载数据",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected View initView(ViewGroup container, LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.category_fragment,container,false);

        recyclerView = (RecyclerView) view.findViewById(R.id.category_recycler);
//       ViewGroup  parent= (ViewGroup) view.getParent();
//        if (parent != null) {
//            parent.removeAllViews();
//        }
        //       parent.addView(recyclerView);

        recyclerView = new RecyclerView(MyApplication.getContext());
        mlist = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mlist.add("这是");
        }
        adapter = new RecyclerAdapter(mlist, getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        return view;
    }
    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }
//    @Override
//    protected View initView() {
//
//    }
}
