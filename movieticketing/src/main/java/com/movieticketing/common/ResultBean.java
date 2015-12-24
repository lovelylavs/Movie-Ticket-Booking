package com.movieticketing.common;

import java.io.Serializable;
import java.util.List;

/**
 * Created by nagal_000 on 12/2/2015.
 */
public class ResultBean implements Serializable {

    List rows;
    int  status;
    String exception;

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "rows=" + rows +
                ", status=" + status +
                ", exception='" + exception + '\'' +
                '}';
    }
}
