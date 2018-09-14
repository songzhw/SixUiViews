package cn.six.open.view.rv.OneBindingAdapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by songzhw on 2017-02-21
 */

public class OneBindingAdapter<T> extends RecyclerView.Adapter<OneBindingViewHolder> {

    private final Context ctx;
    private final int layoutId;
    private final int brId;
    private final List<T> data;

    public OneBindingAdapter(Context ctx, @LayoutRes int itemLayoutId, int brId, List<T> data) {
        this.ctx = ctx;
        this.layoutId = itemLayoutId;
        this.brId = brId;
        this.data = data;
    }

    @Override
    public OneBindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OneBindingViewHolder(View.inflate(ctx, layoutId, null));
    }

    @Override
    public void onBindViewHolder(OneBindingViewHolder holder, int position) {
        ViewDataBinding binding = DataBindingUtil.bind(holder.itemView);
        if(data != null && data.size() > position){
            T t = data.get(position);
            binding.setVariable(brId, t);
            binding.executePendingBindings();
        }
    }

    @Override
    public int getItemCount() {
        return data == null? 0 : data.size();
    }
}