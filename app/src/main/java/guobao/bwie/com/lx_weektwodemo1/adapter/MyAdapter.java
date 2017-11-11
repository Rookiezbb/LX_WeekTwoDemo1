package guobao.bwie.com.lx_weektwodemo1.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import guobao.bwie.com.lx_weektwodemo1.R;
import guobao.bwie.com.lx_weektwodemo1.bean.MyBean;

/**
 * Created by Zhang on 2017/11/4.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<MyBean.DataBean> list;
    private Context context;

    public MyAdapter(Context context) {
        list = new ArrayList<>();
        this.context = context;
    }

    public void setData(List<MyBean.DataBean> list, int pages) {
        if (pages == 1) {
            this.list.clear();
        }
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {
        MyBean.DataBean db = list.get(position);
        holder.tv.setText(db.getTitle());
        if (db.getImg() != null) {
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setUri(db.getImg())
                    .setAutoPlayAnimations(true)
                    .build();
            holder.img.setController(controller);
        }

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        SimpleDraweeView img;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.item_tv);
            img = (SimpleDraweeView) itemView.findViewById(R.id.item_img);
        }
    }
}
