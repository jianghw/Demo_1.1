package com.cjy.jianghw.app.util;

import java.util.concurrent.atomic.AtomicInteger;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * <b>@Description:</b>TODO<br/>
 * <b>@Author:</b>Administrator<br/>
 * <b>@Since:</b>2016/4/18 0018<br/>
 */
public class SimpleCountingIDResource {
    /**
     * 原子操作的Integer的类
     */
    private final AtomicInteger mAtomicInteger = new AtomicInteger(0);
    private final String mResourceName;

    public SimpleCountingIDResource(String resource) {
        mResourceName = checkNotNull(resource);
    }

    /**
     * 获取当前的值，并自增
     */
    public void increment() {
        mAtomicInteger.getAndIncrement();
    }

    public void decrement() {
        int decrement = mAtomicInteger.decrementAndGet();
    }
}
