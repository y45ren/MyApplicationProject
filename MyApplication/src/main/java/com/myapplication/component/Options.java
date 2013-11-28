package com.myapplication.component;

/**
 * Created with IntelliJ IDEA.
 * User: Yi
 * Date: 13-4-5
 * Time: 下午12:03
 * To change this template use File | Settings | File Templates.
 */
public class Options {
    public enum GUIDELINES{BOX, LINE_2, LINE_4};
    GUIDELINES guidelines;
    Boolean realtime;

    public Options(){
        guidelines = GUIDELINES.BOX;
        realtime = false;
    }

    public void setOptions(GUIDELINES guidelines, boolean realtime){
        this.realtime = realtime;
        this.guidelines = guidelines;
    }
}
