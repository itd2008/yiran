package com.yiran.paychannel.segment;
/**
 * 
 * <p>固定字符串字段</p>
 */
public class FixedSegment implements Segment{
    
    private String fixed;
    
    public FixedSegment(String fixed) {
        this.fixed = fixed;
    }

    @Override
    public String get() {
        return this.fixed;
    }

}
