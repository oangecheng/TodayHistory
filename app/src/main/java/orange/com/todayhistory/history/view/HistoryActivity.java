package orange.com.todayhistory.history.view;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import java.util.List;

import orange.com.todayhistory.R;
import orange.com.todayhistory.api.ApiLoader;
import orange.com.todayhistory.base.BaseWebObserver;
import orange.com.todayhistory.databinding.ActivityHistoryBinding;
import orange.com.todayhistory.history.bean.HistoryBean;


public class HistoryActivity extends AppCompatActivity {

    private ActivityHistoryBinding binding;
    private List<HistoryBean> list;
    private ApiLoader mLoader;
    private ListAdapter adapter;
    private ProgressDialog dialog;
    private RecyclerAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(HistoryActivity.this, R.layout.activity_history);
        dialog = new ProgressDialog(this);
        mLoader = new ApiLoader(HistoryActivity.this, dialog);

        test("5/12");

    }


    private void test(String date) {
        mLoader.getHistory(date)
                .subscribe(new BaseWebObserver<List<HistoryBean>>(HistoryActivity.this, dialog) {
                    @Override
                    public void handleSuccess(List<HistoryBean> list) {
//                        list = l;
//                        adapter = new ListAdapter(HistoryActivity.this, list);
//                        binding.setAdapter(adapter);
//                        binding.setHistory(list.get(0));
//                        binding.setViewModel(new HistoryViewModel(binding, list));

                        binding.lv.setLayoutManager(new LinearLayoutManager(HistoryActivity.this, LinearLayoutManager.VERTICAL, false));
                        myAdapter = new RecyclerAdapter(list);
                        binding.setAdapter(myAdapter);

                    }
                });

    }


}
