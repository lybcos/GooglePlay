package com.example.administrator.tablayouttest.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.tablayouttest.R;
import com.example.administrator.tablayouttest.adapter.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

//import static com.example.administrator.tablayouttest.fragment.AppFragment.Demoinfo.demos;

/**
 * Created by Administrator on 2017/10/30.
 */

public class AppFragment extends BaseFragment {
    private RecyclerAdapter adapter;
    private List<String> mlist;
    private RecyclerView recyclerView;

    @Override
    protected void loadData() {
        Toast.makeText(getContext(), "Fragment头条加载数据", Toast.LENGTH_SHORT).show();
    }
    protected View initView(ViewGroup container, LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.app_fragment,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.app_recycler);
//        recyclerView = new RecyclerView(getActivity().getApplicationContext());
        mlist = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mlist.add("这是");
        }
        search_bga.beginRefreshing();
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
//            ViewHodler hodler;
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
//    }
}



