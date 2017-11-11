package guobao.bwie.com.lx_weektwodemo1;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import guobao.bwie.com.lx_weektwodemo1.adapter.MyAdapter;
import guobao.bwie.com.lx_weektwodemo1.bean.MyBean;
import guobao.bwie.com.lx_weektwodemo1.presenter.ListPresenter;
import guobao.bwie.com.lx_weektwodemo1.utils.Api;

public class MainActivity extends AppCompatActivity implements ListConstract.IView ,SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.rcv)
    RecyclerView rcv;
    @BindView(R.id.srl)
    SwipeRefreshLayout srl;
    private LinearLayoutManager linearLayoutManager;
    private int pages = 1;
    private MyAdapter myAdapter;
    private ListPresenter listPresenter;
    private int lastVisibleItemPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initview();
        listPresenter = new ListPresenter(this);
        listPresenter.loadList(Api.PATH,pages);
    }

    private void initview() {
        myAdapter = new MyAdapter(this);
        rcv.setAdapter(myAdapter);
        linearLayoutManager = new LinearLayoutManager(this);
        rcv.setLayoutManager(linearLayoutManager);
        srl.setOnRefreshListener(this);
        getloadmore();
    }

    private void getloadmore() {
        rcv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE&& lastVisibleItemPosition +1==myAdapter.getItemCount()){

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            pages++;
                            listPresenter.loadList(Api.PATH,pages);
                        }
                    },500);

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
            }
        });
    }

    @Override
    public void ShowList(List<MyBean.DataBean> list) {
        myAdapter.setData(list,pages);
    }

    @Override
    public void ShowError(String e) {
        Log.e("哈哈哈哈哈哈哈哈哈哈哈哈",e);
        Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void RefreshFinish() {
        srl.setRefreshing(false);

    }

    @Override
    public void CommitDb() {

    }

    @Override
    public void onRefresh() {
        pages = 1;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listPresenter.loadList(Api.PATH,pages);
                Toast.makeText(MainActivity.this, "刷新成功", Toast.LENGTH_SHORT).show();
            }
        },2000);
    }
}
