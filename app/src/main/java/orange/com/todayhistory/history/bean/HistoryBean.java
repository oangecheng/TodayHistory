package orange.com.todayhistory.history.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.io.Serializable;

import orange.com.todayhistory.BR;

/**
 * Created by Orange on 2017/2/25.
 */

public class HistoryBean extends BaseObservable implements Serializable{


    /**
     * day  当前日期 1/1
     * date 历史上的日期  "1948年01月01日"
     * title 标题   "国民党中常会决定开除汪精卫党籍",
     * e_id 事件id  可以根据这个查询该事件的详细信息
     */

    private String day;
    private String date;
    private String title;
    private String e_id;
    private boolean boy;

    public HistoryBean() {
        this.boy = true;
    }



    public HistoryBean(String day, String date, String title, String e_id) {
        this.day = day;
        this.date = date;
        this.title = title;
        this.e_id = e_id;
        this.boy = true;

    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Bindable
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
        notifyPropertyChanged(BR.date);
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);

    }

    public String getE_id() {
        return e_id;
    }

    public void setE_id(String e_id) {
        this.e_id = e_id;
    }

    @Bindable
    public boolean isBoy() {
        return boy;
    }

    public void setBoy(boolean isBoy) {
        boy = isBoy;
        notifyPropertyChanged(BR.boy);
    }
}
