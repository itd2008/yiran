package com.yiran.wechat.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiran.wechat.mapper.BsAreaMapper;
import com.yiran.wechat.mapper.BsCityMapper;
import com.yiran.wechat.mapper.BsProvinceMapper;
import com.yiran.wechat.domain.Area;
import com.yiran.wechat.domain.BsArea;
import com.yiran.wechat.domain.BsCity;
import com.yiran.wechat.domain.BsProvince;
import com.yiran.wechat.domain.City;
import com.yiran.wechat.domain.Province;
import com.yiran.wechat.service.IBsProvinceService;
import com.alibaba.fastjson.JSON;
import com.yiran.common.support.Convert;

/**
 * 省份设置 服务层实现
 * 
 * @author yiran
 * @date 2019-07-06
 */
@Service
public class BsProvinceServiceImpl implements IBsProvinceService 
{
	@Autowired
	private BsProvinceMapper bsProvinceMapper;
	
	@Autowired
	private BsCityMapper bsCityMapper;
	
	@Autowired
	private BsAreaMapper bsAreaMapper;

	/**
     * 查询省份设置信息
     * 
     * @param provinceId 省份设置ID
     * @return 省份设置信息
     */
    @Override
	public BsProvince selectBsProvinceById(Integer provinceId)
	{
	    return bsProvinceMapper.selectBsProvinceById(provinceId);
	}
	
	/**
     * 查询省份设置列表
     * 
     * @param bsProvince 省份设置信息
     * @return 省份设置集合
     */
	@Override
	public List<BsProvince> selectBsProvinceList(BsProvince bsProvince)
	{
	    return bsProvinceMapper.selectBsProvinceList(bsProvince);
	}
	
    /**
     * 新增省份设置
     * 
     * @param bsProvince 省份设置信息
     * @return 结果
     */
	@Override
	public int insertBsProvince(BsProvince bsProvince)
	{
	    return bsProvinceMapper.insertBsProvince(bsProvince);
	}
	
	/**
     * 修改省份设置
     * 
     * @param bsProvince 省份设置信息
     * @return 结果
     */
	@Override
	public int updateBsProvince(BsProvince bsProvince)
	{
	    return bsProvinceMapper.updateBsProvince(bsProvince);
	}

	/**
     * 删除省份设置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteBsProvinceByIds(String ids)
	{
		return bsProvinceMapper.deleteBsProvinceByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<BsProvince> selectBsProvinceLists() {
		return bsProvinceMapper.selectBsProvinceLists();
	}

	@Override
	public List<Province> findProvinceList() {
		List<Province> pList = new ArrayList<Province>();
		List<BsProvince> provinceLists = selectBsProvinceLists();
		for (BsProvince bp : provinceLists) {
			Province p = new Province();
			p.setCode(bp.getProvinceCode());
			p.setName(bp.getProvinceName());
			List<City> cList = new ArrayList<City>();
			List<BsCity> bsCityList = bsCityMapper.selectBsCityListByProvinceCode(bp.getProvinceCode());
			for (BsCity bc : bsCityList) {
				City c = new City();
				c.setCode(bc.getCityCode());
				c.setName(bc.getCityName());
				List<BsArea> areaList= bsAreaMapper.selectBsAreaListByCityCode(bc.getCityCode());
				List<Area> aList = new ArrayList<Area>();
				for (BsArea ba : areaList) {
					Area a = new Area();
					a.setCode(ba.getAreaCode());
					a.setName(ba.getAreaName());
					aList.add(a);
				}
				c.setSub(aList);
				cList.add(c);
			}
			p.setSub(cList);
			pList.add(p);
		}
		System.out.println(JSON.toJSONString(pList));
		return pList;
	}
	
}
