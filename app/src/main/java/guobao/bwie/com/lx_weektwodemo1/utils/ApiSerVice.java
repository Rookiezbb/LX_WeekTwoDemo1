package guobao.bwie.com.lx_weektwodemo1.utils;

import java.util.List;

import guobao.bwie.com.lx_weektwodemo1.bean.MyBean;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Zhang on 2017/11/11.
 */

public interface ApiSerVice {

    @GET("wap/data/news/txs/page_{pages}.json")
    Observable<List<MyBean>> getdatas(@Path("pages") int pages);
}
