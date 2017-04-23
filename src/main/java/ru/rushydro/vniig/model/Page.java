package ru.rushydro.vniig.model;

import java.util.List;

/**
 * Created by nikolay on 01.11.15.
 */
public class Page<T> {

    Long page;

    int size;

    Long count;

    List<T> content;

    public Page(Long page, int size, Long count, List<T> content) {
        this.page = page;
        this.size = size;
        this.count = count;
        this.content = content;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public long getPageCount() {
        return count % (long) size == 0 ? count / (long) size : count / (long) size + 1l;
    }
}
