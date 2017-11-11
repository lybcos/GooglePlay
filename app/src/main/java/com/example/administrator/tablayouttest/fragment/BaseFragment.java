package com.example.administrator.tablayouttest.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.tablayouttest.R;
import com.example.administrator.tablayouttest.activity.DefineBAGRefreshView;
import com.example.administrator.tablayouttest.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * Created by Administrator on 2017/10/30.
 */

public abstract class BaseFragment extends Fragment implements BGARefreshLayout.BGARefreshLayoutDelegate{
//    protected Context mContent;
    public BGARefreshLayout search_bga;
    private Context mContext;
    private int ALLSUM = 0;
    private static int THRESHOLD_OFFSET = 10;
    private int DATASIZE = 10;
    public RecyclerView base_recycler;
    private RecyclerViewAdapter adapter;
    private List<String> mListData = new ArrayList<String>();
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    mListData.clear();
                   setData();
                    search_bga.endRefreshing();
                    break;
                case 1:
                    setData();
                    search_bga.endLoadingMore();
                default:
                    break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mContent = getContext();//上下文。


    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadData();//初始化数据。
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.basefragment, container, false);
        search_bga = (BGARefreshLayout) view.findViewById(R.id.base_bga_search);
        base_recycler = (RecyclerView) view.findViewById(R.id.base_recycler);
        search_bga.setDelegate(this);
        setBgaRefreshLayout();
        setRecyclerCommadapter();
        setRecyclerView();
        return initView(container,inflater);//初始化布局。
    }

    public void setData() {
        for (int i=0;i<DATASIZE;i++) {
            mListData.add("第" + (ALLSUM * 10 + i) + "条数据");
        }
        if (ALLSUM == 0) {
            setRecyclerCommadapter();
        } else {
            adapter.notifyDataSetChanged();
        }

    }
private void setRecyclerCommadapter() {
    adapter = new RecyclerViewAdapter(getContext(), mListData);
    base_recycler.setAdapter(adapter);
}
    private void setRecyclerView() {
        base_recycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
    }
    private void setBgaRefreshLayout() {
        search_bga.setRefreshViewHolder(new DefineBAGRefreshView(getContext(),true,true));
    }
    /** 刷新 */
    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        ALLSUM = 0;
        handler.sendEmptyMessageDelayed(0 , 2000);
    }
    /** 加载 */
    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        if(ALLSUM == 2){
            Toast.makeText(mContext , "没有更多数据" , Toast.LENGTH_SHORT).show();
            return false;
        }
        ALLSUM++;
        handler.sendEmptyMessageDelayed(1 , 2000);
        return true;
    }

    protected abstract void loadData();

    protected abstract View initView(ViewGroup container, LayoutInflater inflater);
}