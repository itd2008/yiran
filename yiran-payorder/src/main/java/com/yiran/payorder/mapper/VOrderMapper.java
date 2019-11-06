package com.yiran.payorder.mapper;

import java.util.List;

import com.yiran.payorder.domaindo.VOrder;

public interface VOrderMapper {

	public List<VOrder> selectOrderList(VOrder order) ;
	
	

}
