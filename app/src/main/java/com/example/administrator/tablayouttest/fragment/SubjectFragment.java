package com.example.administrator.tablayouttest.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.tablayouttest.R;
import com.example.administrator.tablayouttest.activity.MyApplication;
import com.example.administrator.tablayouttest.adapter.SubjectRecyclerAdapter;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * Created by Administrator on 2017/10/30.
 */

public class SubjectFragment extends BaseFragment {
    //专题
    private SubjectRecyclerAdapter adapter;
    private String[] mstr = {"a","b","c","d","e","f","g"};
    private int[] mint = {R.drawable.baetman,R.drawable.baetman,R.drawable.baetman,R.drawable.baetman,R.drawable.baetman,
            R.drawable.baetman,R.drawable.baetman};
    private RecyclerView subject_recycler;
    private BGARefreshLayout subject_refresh;

    @Override
    protected void loadData() {
    }

    protected View initView(ViewGroup container, LayoutInflater inflater) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.subject_item01, null, false);
        subject_recycler = (RecyclerView) view.findViewById(R.id.subject_recycler);
        subject_recycler = new RecyclerView(MyApplication.getContext());
        adapter = new SubjectRecyclerAdapter(mstr, mint,getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        subject_recycler.setLayoutManager(manager);
        subject_recycler.setAdapter(adapter);
        return view;
    }
    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }
}
