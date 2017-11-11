package guobao.bwie.com.lx_weektwodemo1.presenter;

import java.util.List;

import guobao.bwie.com.lx_weektwodemo1.ListConstract;
import guobao.bwie.com.lx_weektwodemo1.bean.MyBean;
import guobao.bwie.com.lx_weektwodemo1.model.ListModel;

/**
 * Created by Zhang on 2017/11/11.
 */

public class ListPresenter implements ListConstract.IPresenter {
    ListConstract.IView iView;
    ListConstract.IModel iModel;

    public ListPresenter(ListConstract.IView iView) {
        this.iView = iView;
        iModel = new ListModel();
    }

    @Override
    public void loadList(String url,int pages) {
        iModel.RequestData(url,pages,new ListConstract.OnRequsetListener() {
            @Override
            public void OnSuccess(List<MyBean.DataBean> list) {
                iView.ShowList(list);
            }

            @Override
            public void OnError(String e) {
                iView.ShowError(e);
            }

            @Override
            public void OnFinish() {
                iView.RefreshFinish();
            }
        });
    }
}
