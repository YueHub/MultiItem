package com.freelib.multiitem.demo;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.freelib.multiitem.adapter.BaseItemAdapter;
import com.freelib.multiitem.adapter.BaseViewHolder;
import com.freelib.multiitem.demo.bean.TextBean;
import com.freelib.multiitem.demo.viewholder.TextViewManager;
import com.freelib.multiitem.listener.OnItemClickListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.layout_recycler)
public class MainActivity extends AppCompatActivity {
    @ViewById(R.id.recyclerView)
    protected RecyclerView recyclerView;


    @AfterViews
    protected void initViews() {
        setTitle(R.string.main_title);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        BaseItemAdapter adapter = new BaseItemAdapter();
        //为TextBean数据源注册TextViewManager管理类
        adapter.register(TextBean.class, new TextViewManager());
        recyclerView.setAdapter(adapter);
        adapter.addDataItem(new TextBean(getString(R.string.multi_item_title)));
        adapter.addDataItem(new TextBean(getString(R.string.item_click_title)));

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseViewHolder viewHolder) {
                //通过viewHolder获取需要的数据
                switch (viewHolder.getAdapterPosition()) {
                    case 0:
                        MultiItemActivity.startMultiItemActivity(MainActivity.this);
                        break;
                    case 1:
                        ItemClickActivity.startItemClickActivity(MainActivity.this);
                        break;
                    default:
                        break;
                }

            }
        });

    }
}