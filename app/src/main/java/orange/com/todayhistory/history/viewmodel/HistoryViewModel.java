package orange.com.todayhistory.history.viewmodel;

import android.view.View;
import android.widget.AdapterView;

import java.util.List;

import orange.com.todayhistory.databinding.ActivityHistoryBinding;
import orange.com.todayhistory.history.bean.HistoryBean;

/**
 * Created by Orange on 2017/2/25.
 */

public class HistoryViewModel {

    private ActivityHistoryBinding binding;
    private List<HistoryBean> list;

    public HistoryViewModel(ActivityHistoryBinding binding, List<HistoryBean> list) {
        this.binding = binding;
        this.list = list;
        //binding.lv.setOnItemClickListener(new ItemClick());
    }


    public void click(View view){
    }


    private class ItemClick implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            HistoryBean bean = binding.getHistory();
            list.remove(i);
            binding.getAdapter().notifyDataSetChanged();
        }
    }
}
