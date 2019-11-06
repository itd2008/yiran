package com.yiran.payorder.mapper;

import java.util.List;

import com.yiran.payorder.domaindo.VInstOrder;


public interface VInstOrderMapper {

	public List<VInstOrder> selectInstOrderList(VInstOrder vInstOrder);
}
