package com.example.administrator.tablayouttest.fragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.tablayouttest.R;
import com.example.administrator.tablayouttest.flow.FlowAdapter;
import com.example.administrator.tablayouttest.flow.FlowLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * Created by Administrator on 2017/10/30.
 */

public class HotFragment extends BaseFragment {
    private List<String> mDatas;
    ;
    private final String arrays[] = new String[]{
            "QQ",
            "视频",
            "放开那三国",
            "机器",
            "电子书",
            "酒店",
            "单机",
            "小说",
            "斗地主",
            "魔兽",
            "网游",
            "机器",
            "游戏",
            "WIFI万能钥匙",
            "播放器",
            "机器",
            "捕鱼达人2",
            "机器",
            "游戏",
            "机器",
            "游戏",
            "熊出没之熊大快跑",
            "机器",
            "美图秀秀",
            "浏览器",
            "单机游戏",
            "我的世界",
            "机器",
            "电影电视",
            "QQ空间",
            "旅游",
            "免费游戏",
            "2048",
            "刀塔传奇",
            "王者荣耀",
            "机器",
            "地下城与勇士",
            "机器",
            "游戏",
            "虫虫大作战",
            "爱奇艺",
            "优酷",
            "简书",
            "携程旅游",
            "蓝墨云课堂",
            "CSDN",
            "微信",
            "酷狗音乐",
            "掘金",
            "完美校园",
            "英雄联盟",
            "机器",
            "游戏",};

    private ProgressBar pb;
    private Handler handler = new Handler();

    @Override
    protected void loadData() {
        search_bga.beginRefreshing();
    }

    protected View initView(ViewGroup container, LayoutInflater inflater) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.activity_loaddata, null, false);
        FlowLayout re = (FlowLayout) view.findViewById(R.id.flow);
        pb = (ProgressBar)view. findViewById(R.id.load);
        final ScrollView sv = (ScrollView)view. findViewById(R.id.sv);
        final List<String> list = new ArrayList<>();
        //声明并为流式布局re设置适配器
        final MyFlowAdapter myFlowAdapter = new MyFlowAdapter(getContext(), list);
        re.setAdapter(myFlowAdapter);
        //模拟网络(这个部分不太懂~~~)
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<String> strings = Arrays.asList(arrays);
                list.addAll(strings);
               /*notifyDataSetChanged方法通过一个外部的方法控制如果适配器的内容改变时需要强制调用getView来刷新每个Item的内容。
                notifyDataSetChanged()可以在修改适配器绑定的数组后，不用重新刷新Activity，通知Activity更新ListView。*/
                myFlowAdapter.notifyDataChanged();
                sv.setVisibility(View.VISIBLE);
                pb.setVisibility(View.GONE);      //设置滑动条可见
            }
        }, 2000);
        return  view;
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }

    class MyFlowAdapter extends FlowAdapter<String> {

        public MyFlowAdapter(Context context, List<String> list) {
            super(context, list);
        }

        @Override
        protected int generateLayout(int position) {       //生成布局
            return R.layout.flow_item;
        }

        @Override
        protected void getView(final String o, View parent) {
            TextView text = (TextView) parent.findViewById(R.id.flow_text);
            text.setText(o);
            //item的点击事件
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), o, Toast.LENGTH_SHORT).show();
                }
            });
            text.setBackgroundDrawable(getBack());
        }

        private Drawable getBack() {
            GradientDrawable drawable = new GradientDrawable();      //GradientDrawable类可以设置不同组件的背景渐变色
            drawable.setCornerRadius(28);                                      //设置圆角
            drawable.setColor(Color.rgb(new Random().nextInt(255),
                    new Random().nextInt(255), new Random().nextInt(255)));    //设置随机背景颜色
            return drawable;
        }
    }
}
