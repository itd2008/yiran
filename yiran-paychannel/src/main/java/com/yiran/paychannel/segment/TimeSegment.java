package com.yiran.paychannel.segment;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * <p>负责生成时间部分</p>
 */
public class TimeSegment implements Segment{

    private String format;

    public TimeSegment(String format) {
        this.format = format;
    }

    @Override
    public String get() {
        SimpleDateFormat sdf = new SimpleDateFormat(this.format);
        return sdf.format(new Date());
    }

    public String get(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(this.format);
        return sdf.format(date);
    }

}
