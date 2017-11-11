package com.example.administrator.tablayouttest.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.tablayouttest.R;
import com.example.administrator.tablayouttest.activity.MyApplication;
import com.example.administrator.tablayouttest.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * Created by Administrator on 2017/10/30.
 */

public class GameFragment extends BaseFragment implements  BGARefreshLayout.BGARefreshLayoutDelegate{
    private RecyclerViewAdapter adapter;
    private List<String> list;
    private RecyclerView recyclerView;
    private int ALLSUM = 0;
    private BGARefreshLayout mSearchBgaRefresh;;
    private Context mContext;
    private static int THRESHOLD_OFFSET = 10;
//    private TextView mTitle;
    /** 数据 */
    private List<String> mListData = new ArrayList<String>();
    /** 一次加载数据的条数 */
    private int DATASIZE = 10;
    private List<String>mlist = new ArrayList<>();
    private String[] mStr = {"a","b","c","d","e","f"};
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    mListData.clear();
                    setData();
                    mSearchBgaRefresh.endRefreshing();
                    break;
                case 1:
                    setData();
                    mSearchBgaRefresh.endLoadingMore();
                default:
                    break;
            }
        }
    };

    @Override
    protected void loadData() {
        Toast.makeText(getContext(),"Fragment头条加载数据",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected View initView(ViewGroup container, LayoutInflater inflater) {
     View view = LayoutInflater.from(getContext()).inflate(R.layout.game_fragment, null, false);
    recyclerView = (RecyclerView) view.findViewById(R.id.game_recycler);
       recyclerView = new RecyclerView(MyApplication.getContext());
        list = new ArrayList<>();
        for (int i = 0; i <mStr.length; i++) {
        list.add(mStr[i]);
    }
        setRecyclerCommadapter();
//        setBgaRefreshLayout();
        mSearchBgaRefresh = new BGARefreshLayout(getContext());
        mSearchBgaRefresh.setDelegate(this);
    LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
    recyclerView.setLayoutManager(manager);
    recyclerView.setAdapter(adapter);
    return view;
        }
public void setData() {
    for (int i = 0; i < DATASIZE; i++) {
        mListData.add("第" + (ALLSUM * 10 + i) + "条数据");
    }
    if (ALLSUM == 0) {
        setRecyclerCommadapter();
    } else {
        adapter.notifyDataSetChanged();
    }
}

    private void setRecyclerCommadapter() {
        adapter = new RecyclerViewAdapter( MyApplication.getContext(),list);
        recyclerView.setAdapter(adapter);
    }

//    public void setBgaRefreshLayout() {
//        mSearchBgaRefresh.setRefreshViewHolder(new DefineBAGRefreshView(getContext(),true,true));
//    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadData();//初始化数据。
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
//    class listAdapter extends BaseAdapter {
//        private List<String> mlist;
//
//
//        public listAdapter(List<String> mlist) {
//            this.mlist = mlist;
//        }
//
//
//        public int getCount() {
//            return mlist.size();
//        }
//
//
//        public Object getItem(int position) {
//            return mlist.get(position);
//        }
//
//
//        public long getItemId(int position) {
//            return position;
//        }
//
//
//        public View getView(int position, View convertView, ViewGroup parent) {
//           ViewHodler hodler;
//            AppInfo info = new AppInfo();
//            if (convertView == null) {
//                convertView = LayoutInflater.from(getContext()).inflate(R.layout.app_list_item, parent, false);
//                hodler = new ViewHodler();
//                hodler.item_icon = (ImageView) convertView.findViewById(R.id.item_icon);
//                hodler.action_progress = (FrameLayout) convertView.findViewById(R.id.action_progress);
//                hodler.action_txt = (TextView) convertView.findViewById(R.id.action_txt);
//                hodler.item_title = (TextView) convertView.findViewById(R.id.item_title);
//                hodler.item_size = (TextView) convertView.findViewById(R.id.item_size);
//                hodler.item_bottom = (TextView) convertView.findViewById(R.id.item_bottom);
//                hodler.item_ratig = (RatingBar) convertView.findViewById(R.id.item_rating);
//                convertView.setTag(hodler);
//            } else {
//                hodler = (ViewHodler) convertView.getTag();
//            }
//            hodler.item_icon.setImageResource(R.drawable.google_play);
//            hodler.item_title.setText(info.getName());
////            hodler.item_size.setText((int) info.getSize());
//            hodler.action_txt.setText("下载");
//            hodler.item_bottom.setText(info.getDes());
//            return convertView;
//        }
//    }
//
//    class ViewHodler {
//        ImageView item_icon;
//        FrameLayout action_progress;
//        TextView action_txt, item_title, item_size, item_bottom;
//        RatingBar item_ratig;
//
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);

    }
    //    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//        Intent intent = new Intent(getActivity(), demos[position].demoClass);
//        startActivity(intent);
//    }
    public static  class  Demoinfo{
        private final  Class <?extends Activity>demoClass;
        public Demoinfo(Class<? extends Activity> demoClass){
            this.demoClass=demoClass;
        }
//        private static  final Demoinfo[]demos={
//
//        };
    }
}
