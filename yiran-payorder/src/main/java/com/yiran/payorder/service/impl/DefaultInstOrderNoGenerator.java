package com.yiran.payorder.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiran.paychannel.domain.TmApiNoMode;
import com.yiran.paychannel.segment.FixedSegment;
import com.yiran.paychannel.segment.Segment;
import com.yiran.paychannel.segment.SequenceSegment;
import com.yiran.paychannel.segment.TimeSegment;
import com.yiran.paychannel.service.ISequenceService;
import com.yiran.payorder.service.InstOrderNoGenerator;


/**
 *
 * <p>机构定单生成</p>
 */
@Service("instOrderNoGenerator")
public class DefaultInstOrderNoGenerator implements InstOrderNoGenerator {

	@Autowired
	private ISequenceService sequenceService;
	
    private static final String fixed    = "f";
    private static final String time     = "t";
    private static final String sequence = "s";


    @Override
    public String genInstOrderNo(TmApiNoMode apiNoMode) {

        List<Segment> segments = getSegmentList(apiNoMode);

        String id = getId(segments);

        return id;
    }

    private String getId(List<Segment> segments) {
        String id = "";
        for (Segment segment : segments) {
            id = id + segment.get();
        }
        return id;
    }

    @Override
    public List<Segment> getSegmentList(TmApiNoMode apiNoMode) {
        List<Segment> result = new ArrayList<Segment>();

        String format = apiNoMode.getGenPattern();

        String[] segments = format.split("\\|");
        for (String segment : segments) {
            String[] fields = segment.split(":");
            Segment s = getSegment(fields, apiNoMode.getSeqName());
            result.add(s);
        }
        return result;
    }

    /**
     * 获取短号
     * @param fields
     * @param seqName
     * @return
     */
    private Segment getSegment(String[] fields, String seqName) {
        String prefix = fields[0];
        String length = fields[1];

        if (fixed.equals(prefix)) {
            return new FixedSegment(length);
        } else if (time.equals(prefix)) {
            return new TimeSegment(length);
        } else if (sequence.equals(prefix)) {
            return new SequenceSegment(length, getSequence(seqName));
        }

        return new FixedSegment("");
    }

    /**
     * 获取流水号
     * @param seqName
     * @return
     */
    private long getSequence(String seqName) {
    	return sequenceService.nextval(seqName);
    }

    @Override
    public String populateInstOrderNo(List<Segment> segments, Date date, String sourceInstOrderNo) {
        String id = "";
        for (Segment segment : segments) {
            if (segment instanceof SequenceSegment) {
                //依据seq长度截取后面的位数
                id = id
                     + sourceInstOrderNo.substring(sourceInstOrderNo.length()
                                                   - ((SequenceSegment) segment).getLength());
            } else if (segment instanceof TimeSegment) {
                id = id + ((TimeSegment) segment).get(date);
            } else {
                id = id + segment.get();
            }
        }
        return id;
    }

}
