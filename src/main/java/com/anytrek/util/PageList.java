package com.anytrek.util;

import java.util.List;

public class PageList<T> {

    private int totalRows;  
    private List<T> list;  
  
    public int getTotalRows() {  
        return totalRows;  
    }  
    public void setTotalRows(int totalRows) {  
        this.totalRows = totalRows;  
    }  
    public List<T> getList() {  
        return list;  
    }  
    public void setList(List<T> list) {  
        this.list = list;  
    }  
}


