package com.yiran.paychannel.segment;


/**
 * 
 * <p>负责生成sequence部分</p>
 */
public class SequenceSegment implements Segment {
    /** 长度 */
    private int                 length;
    /** 流水号 */
    private long                sequence;
    
    public int getLength() {
        return length;
    }

    private static final String append = "000000000000000000";

    public SequenceSegment(String length, long sequence) {
        this.length = Integer.valueOf(length);
        this.sequence = sequence;
    }

    @Override
    public String get() {
        return getSegment(sequence);
    }

    private String getSegment(long seq) {
        String temp = append + seq;
        return temp.substring(temp.length() - this.length, temp.length());
    }
}
