package orange.com.todayhistory.history.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import java.util.List;

import orange.com.todayhistory.BR;
import orange.com.todayhistory.databinding.ListItemBinding;
import orange.com.todayhistory.history.bean.HistoryBean;


/**
 * Created by Orange on 2017/2/27.
 */

public class ListAdapter extends BaseAdapter {

    public ObservableField<Drawable> observableImage;

    private List<HistoryBean> beanList;
    private Context context;

    public ListAdapter(Context context, List<HistoryBean> beanList) {
        this.beanList = beanList;
        this.context = context;
        observableImage = new ObservableField<>();
    }

    @Override
    public int getCount() {
        return beanList.size();
    }

    @Override
    public Object getItem(int i) {
        return beanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ListItemBinding itemBinding;

        HistoryBean bean = beanList.get(i);
        if (view==null){
            itemBinding = ListItemBinding.inflate(LayoutInflater.from(context), viewGroup, false);
            view = itemBinding.getRoot();
        }else {
            itemBinding = DataBindingUtil.getBinding(view);
        }

        itemBinding.setVariable(BR.history, bean);
        itemBinding.tvTitle.setOnClickListener(new click());
        itemBinding.tvTitle.setTag(i);


        return view;
    }


    private class click implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            int j = (int) view.getTag();
            Toast.makeText(context, j+"", Toast.LENGTH_SHORT).show();
            beanList.get(j).setTitle("辉少66");
            beanList.get(j).setBoy(false);
        }
    }

}
