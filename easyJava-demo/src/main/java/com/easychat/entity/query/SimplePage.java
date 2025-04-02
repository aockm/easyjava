package com.easychat.entity.query;

import com.easychat.enums.PageSize;
public class SimplePage {
    private int pageNo;
    private int pageSize;
    private int totalCount;
    private int totalPage;
    private int startIndex;
    private int endIndex;
    public SimplePage() {}

    public SimplePage(Integer pageNo,int totalCount, int pageSize) {
        if (null == pageNo) {
            pageNo = 1;
        }
        this.pageNo = pageNo;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        action();

    }
    public SimplePage(int startIndex, int endIndex) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    private void action() {
        if (this.pageSize < 0) {
            this.pageSize = PageSize.SIZE20.getSize();
        }
        if (this.totalCount > 0) {
            this.totalPage = this.totalCount % this.pageSize==0?this.totalCount/this.pageSize:this.totalCount/this.pageSize+1;
        }else {
            totalCount = 1;
        }
        if (this.pageNo < 1) {
            pageNo = 1;
        }
        if (this.pageNo > this.totalPage) {
            this.pageNo = this.totalPage;
        }
        this.startIndex = (pageNo - 1) * pageSize;
        this.endIndex = this.pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        this.action();
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }
}
