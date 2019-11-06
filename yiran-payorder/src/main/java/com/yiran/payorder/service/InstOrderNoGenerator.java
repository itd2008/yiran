package com.yiran.payorder.service;

import java.util.Date;
import java.util.List;

import com.yiran.paychannel.domain.TmApiNoMode;
import com.yiran.paychannel.segment.Segment;


/**
 *
 * <p>机构订单号生成</p>
 * 根据不同的渠道生成相应长度和格式的定单号码
 */
public interface InstOrderNoGenerator {
    /**
     * 根据Mode获取机构订单号
     * @param
     * @return
     */
    public String genInstOrderNo(TmApiNoMode apiNoMode);
    
    /**
     * 获取segment
     * @param fields
     * @param seqName
     * @return
     */
    public List<Segment> getSegmentList(TmApiNoMode apiNoMode);
    
    /**
     * 依据segments 时间,日期,产生定单号
     * @param segments
     * @param date
     * @param sourceInstOrderNo
     * @return
     */
    public String populateInstOrderNo(List<Segment> segments, Date date, String sourceInstOrderNo);

}
