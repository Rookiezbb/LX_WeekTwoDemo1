package guobao.bwie.com.lx_weektwodemo1.model;

import java.util.List;

import guobao.bwie.com.lx_weektwodemo1.ListConstract;
import guobao.bwie.com.lx_weektwodemo1.bean.MyBean;
import guobao.bwie.com.lx_weektwodemo1.utils.ApiSerVice;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Zhang on 2017/11/11.
 */

public class ListModel implements ListConstract.IModel {
    @Override
    public void RequestData(String url,int pages ,final ListConstract.OnRequsetListener onRequsetListener) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ApiSerVice apiSerVice = retrofit.create(ApiSerVice.class);
        Observable<List<MyBean>> getdatas = apiSerVice.getdatas(pages);
        getdatas.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<MyBean>>() {
                    @Override
                    public void onCompleted() {
                        onRequsetListener.OnFinish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        onRequsetListener.OnError(e.getMessage().toString());
                    }

                    @Override
                    public void onNext(List<MyBean> myBeen) {
                        List<MyBean.DataBean> data = myBeen.get(0).getData();
                        onRequsetListener.OnSuccess(data);
                    }
                });
    }
}
