package com.jianghw.music.bean;

/**
 * @Description: </b>TODO<br/>
 * @Author: </b>Administrator<br/>
 * @Since: </b>2016/4/29 0029<br/>
 * @See {@link}
 * @Github {@https:}
 */
public class DelayedPostBean {
    private int id;
    private Object[] args;

    public DelayedPostBean(int id, Object[] args) {
        this.id = id;
        this.args = args;
    }
}
