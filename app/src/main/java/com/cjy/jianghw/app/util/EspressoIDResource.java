package com.cjy.jianghw.app.util;

/**
 * <b>@Description:</b>
 * Contains a static reference to {@link }, only available in the 'mock' build type. <br/>
 * <b>@Author:</b>Administrator<br/>
 * <b>@Since:</b>2016/4/18 0018<br/>
 */
public class EspressoIDResource {

    private static final String RESOURCE = "GLOBAL";
    private static SimpleCountingIDResource sSimpleCountingIDResource = new SimpleCountingIDResource(RESOURCE);

    /**
     *定期增加
     */
    public static void increment(){
        sSimpleCountingIDResource.increment();
    }
}
