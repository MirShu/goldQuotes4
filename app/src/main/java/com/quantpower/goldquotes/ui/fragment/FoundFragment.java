package com.quantpower.goldquotes.ui.fragment;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.quantpower.goldquotes.R;
import com.quantpower.goldquotes.adapter.recycler.RecyclerAdapter;
import com.quantpower.goldquotes.adapter.recycler.RecyclerViewHolder;
import com.quantpower.goldquotes.ui.activity.CollectionActivity;
import com.quantpower.goldquotes.ui.activity.ProductIntroductionActivity;
import com.quantpower.goldquotes.utils.Constants;
import com.quantpower.goldquotes.utils.UIHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by linlin.1016@qq.com on 2017/04/25.
 * Description:
 */

public class FoundFragment extends Fragment {
    RecyclerView recyclerView;
    private RecyclerAdapter myOrderReycAdapter;
    private List<String> myOrderReycList;
    XRefreshView xrefreshview;
    private View rootView;

    public static FoundFragment newInstance(String s) {
        FoundFragment homeFragment = new FoundFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGS, s);
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_found, container, false);
        this.xrefreshview = (XRefreshView) rootView.findViewById(R.id.xrefreshview);
        this.recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        this.bindRecycleView();
        myOrderReycAdapter.notifyDataSetChanged();
        this.xRefreshView();
        return rootView;
    }

    private void bindRecycleView() {
        this.getData();
        this.myOrderReycAdapter = new RecyclerAdapter<String>(getActivity(), myOrderReycList,
                R.layout.item_found) {
            @Override
            public void convert(RecyclerViewHolder helper, String item, int position) {

                Log.e("--------position--",""+position);
                TextView tvState = helper.getView(R.id.tv_name);
                RelativeLayout rlItemBg = helper.getView(R.id.rl_item_bg);
                TextView tvProductLabel = helper.getView(R.id.tv_product_label);
                ImageView ivItem = helper.getView(R.id.iv_item);
                if (position == 0) {
                    tvState.setText("谈股论银");
                    tvProductLabel.setText("左手股票,右手白银");
                    Resources resources = getContext().getResources();
                    ivItem.setImageResource(R.mipmap.bg_hang_index);
                    Drawable btnDrawable = resources.getDrawable(R.mipmap.bg_hang_index);
//                    rlItemBg.setBackgroundDrawable(btnDrawable);
                } else if (position == 1) {
                    tvState.setText("银定天下");
                    tvProductLabel.setText("服务VIP投资者,引领走向财富自由!");
                    Resources resources = getContext().getResources();
                    ivItem.setImageResource(R.mipmap.bg_crude_index);
                    Drawable btnDrawable = resources.getDrawable(R.mipmap.bg_crude_index);
//                    rlItemBg.setBackgroundDrawable(btnDrawable);
                } else if (position == 2) {
                    tvState.setText("期货分析");
                    tvProductLabel.setText("资深讲师,24小时专业讲解");
                    Resources resources = getContext().getResources();
                    ivItem.setImageResource(R.mipmap.bg_three_index);

                    Drawable btnDrawable = resources.getDrawable(R.mipmap.bg_three_index);
//                    rlItemBg.setBackgroundDrawable(btnDrawable);
                } else if (position == 3) {
                    tvState.setText("金银天丰");
                    tvProductLabel.setText("贵金属强势分析!");
                    Resources resources = getContext().getResources();
                    ivItem.setImageResource(R.mipmap.bg_silver_index);

                    Drawable btnDrawable = resources.getDrawable(R.mipmap.bg_silver_index);
//                    rlItemBg.setBackgroundDrawable(btnDrawable);
                }
            }
        };

        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1,
                LinearLayoutManager.VERTICAL, false));
        this.recyclerView.setAdapter(this.myOrderReycAdapter);
        this.myOrderReycAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onClick(View parent, int position) {
                UIHelper.startActivity(getActivity(), CollectionActivity.class);
            }
        });

    }

    private void getData() {
        myOrderReycList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            myOrderReycList.add("" + i);
        }
//        Log.e("--------list--",""+myOrderReycList);


//        myOrderReycAdapter.notifyDataSetChanged();

    }

    /**
     * 刷新机制
     */
    private void xRefreshView() {
        this.xrefreshview.setAutoRefresh(true);
        this.xrefreshview.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(() -> xrefreshview.stopRefresh(), 1000);
            }

            @Override
            public void onLoadMore(boolean isSlience) {

            }
        });
    }

}
