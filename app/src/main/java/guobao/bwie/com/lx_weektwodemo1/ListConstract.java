package guobao.bwie.com.lx_weektwodemo1;

import java.util.List;

import guobao.bwie.com.lx_weektwodemo1.bean.MyBean;

/**
 * Created by Zhang on 2017/11/11.
 */

public interface ListConstract {
    interface IView{
        void ShowList(List<MyBean.DataBean> list);
        void ShowError(String e);
        void RefreshFinish();
        void CommitDb();
    }
    interface IModel{
        void RequestData(String url,int pages,OnRequsetListener onRequsetListener);
    }
    interface OnRequsetListener{
        void OnSuccess(List<MyBean.DataBean> list);
        void OnError(String e);
        void OnFinish();
    }
    interface IPresenter{
        void loadList(String url,int pages);
    }
}
