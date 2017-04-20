package orange.com.todayhistory.base;

import java.io.Serializable;

/**
 * Created by Orange on 2017/4/15.
 */

public class BaseEntity<T> implements Serializable{

    private String reason;
    private int err_code;
    private T result;

    public BaseEntity() {
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getErr_code() {
        return err_code;
    }

    public void setErr_code(int err_code) {
        this.err_code = err_code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
