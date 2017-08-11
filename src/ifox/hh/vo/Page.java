package ifox.hh.vo;

import java.util.List;

/**
 * Created by 41988 on 2017/8/10.
 */
public class Page<T> {
    private int pageSize = 8;
    private int pageNum;
    private List<T> list;
    private long totalItemNumber;


    public Page(int pageNum) {
        this.pageNum = pageNum;
    }

    //判断是否有前一页
    public boolean isHasPrev() {
        if (getPageNum() == 1) {
            return false;
        }
        return true;
    }

    //判断是否有后一页
    public boolean isHasNext() {
        if (getPageNum() == getTotalPageNumber()) {
            return false;
        }
        return true;
    }

    public int getPrevPageNum() {
        if (isHasPrev()) {
            return getPageNum() - 1;
        }
        return getPageNum();
    }

    public int getNextPageNum() {
        if (isHasNext()) {
            return getPageNum() + 1;
        }
        return getPageNum();
    }

    //总页数
    private int getTotalPageNumber() {
        int totalPageNumber = (int) (totalItemNumber / pageSize);
        if (totalPageNumber % pageSize != 0) {
            totalPageNumber++;
        }
        return totalPageNumber;
    }

    //setter and getter

    public int getPageSize() {
        return pageSize;
    }

    //校验当前页面的页数
    public int getPageNum() {
        if (pageNum < 0) {
            pageNum = 1;
        }
        if (pageNum > getTotalPageNumber()) {
            pageNum = getTotalPageNumber();
        }
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public long getTotalItemNumber() {
        return totalItemNumber;
    }

    public void setTotalItemNumber(long totalItemNumber) {
        this.totalItemNumber = totalItemNumber;
    }
}
