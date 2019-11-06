package com.yiran.payorder.mapper;

import java.util.List;

import com.yiran.payorder.domaindo.VPayOrder;


public interface VPayOrderMapper {
	
	public List<VPayOrder> selectChannelPayOrderList(VPayOrder vPayOrder);

}
