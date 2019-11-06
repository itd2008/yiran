package com.yiran.payorder.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiran.payorder.domaindo.VInstOrder;
import com.yiran.payorder.domaindo.VInstOrderResult;
import com.yiran.payorder.domaindo.VOrder;
import com.yiran.payorder.domaindo.VPayOrder;
import com.yiran.payorder.mapper.VInstOrderMapper;
import com.yiran.payorder.mapper.VInstOrderResultMapper;
import com.yiran.payorder.mapper.VOrderMapper;
import com.yiran.payorder.mapper.VPayOrderMapper;
import com.yiran.payorder.service.IJointQueryService;
@Service
public class JointQueryServiceImpl implements IJointQueryService{

	@Autowired
	private VPayOrderMapper vPayOrderMapper;
	@Autowired
	private VInstOrderMapper vInstOrderMapper;
	
	@Autowired
	private VInstOrderResultMapper vInstOrderResultMapper;
	@Autowired
	private VOrderMapper vOrderMapper;
	
	@Override
	public List<VPayOrder> selectChannelPayOrderList(VPayOrder vPayOrder) {
		
		return vPayOrderMapper.selectChannelPayOrderList(vPayOrder);
	}

	@Override
	public List<VInstOrder> selectInstOrderList(VInstOrder vInstOrder) {
		
		return vInstOrderMapper.selectInstOrderList(vInstOrder);
	}

	@Override
	public List<VInstOrderResult> selectInstOrderResultList(VInstOrderResult vInstOrderResult) {
		
		return vInstOrderResultMapper.selectInstOrderResultList(vInstOrderResult);
	}

	@Override
	public List<VOrder> selectInstOrderList(VOrder order) {
		
		return vOrderMapper.selectOrderList(order);
	}

}
