package cn.encore.demo.ui.list;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import cn.encore.demo.R;
import cn.encore.demo.ui.base.BaseFragment;
import cn.encore.framecommon.log.jlog.JLog;

/**
 * Created by：Encore
 * Created Time：16/7/7 14:42
 */
public class ListFragment extends BaseFragment {


    @Bind(R.id.recycle)
    RecyclerView mRecycleView;
    @Bind(R.id.springview)
    SpringView mSpringview; //用到上下拉刷新控件

    private List<String> mDatas = new ArrayList<String>();

    private ListAdapter mRecyclerViewAdapter;

    private int mScrollPosition = 0;

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_list;
    }

    @Override
    public void initViews(View contentView) {

        initData();


        mRecycleView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerViewAdapter = new ListAdapter(mDatas);
        mRecycleView.setAdapter(mRecyclerViewAdapter);
        mRecycleView.setItemAnimator(new DefaultItemAnimator());

        mSpringview.setType(SpringView.Type.FOLLOW);
        mSpringview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                JLog.d("onRefresh");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSpringview.onFinishFreshAndLoad();
                    }
                }, 1000);
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSpringview.onFinishFreshAndLoad();
                    }
                }, 1000);
            }
        });


        mSpringview.setHeader(new DefaultHeader(getApplicationContext()));
        mRecycleView.scrollToPosition(mScrollPosition);

        mRecyclerViewAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                Toast.makeText(getApplicationContext(), "Click index: " + i, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initAdapter() {
    }

    //初始化数据
    private void initData() {
        mDatas.clear();
        for (int i = 0; i < 20; i++) {
            mDatas.add("Test List ->" + i);
        }
    }

    public static class ListAdapter extends BaseQuickAdapter<String> {
        public ListAdapter(List<String> datas) {
            super(R.layout.adapter_list_item, datas);
        }

        public ListAdapter(int dataSize,List<String> datas) {
            super(R.layout.adapter_list_item, datas);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            TextView tvTitle = helper.getView(R.id.tvTitle);
            tvTitle.setText(item);
            int position = helper.getAdapterPosition();
            if (position % 2 == 1) {
                tvTitle.setBackgroundColor(Color.parseColor("#e3f1fc"));
                tvTitle.setTextColor(Color.parseColor("#9dd2fc"));
            } else {
                tvTitle.setBackgroundColor(Color.parseColor("#ffffff"));
                tvTitle.setTextColor(Color.parseColor("#cccccc"));
            }
        }


    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //切换 Tab 时保存最后显示的 position 位置,便于还原
        if (mRecycleView != null) {
            LinearLayoutManager layoutManager = (LinearLayoutManager) mRecycleView.getLayoutManager();
            mScrollPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }
    }


}
