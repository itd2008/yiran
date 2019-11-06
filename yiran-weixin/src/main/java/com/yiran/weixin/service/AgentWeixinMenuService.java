/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *//*
package com.yiran.project.weixin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.weixin.entity.AgentWeixinMenu;
import com.thinkgem.jeesite.modules.weixin.entity.WeixinMenuDto;
import com.thinkgem.jeesite.modules.weixin.utils.JsonUtil;
import com.thinkgem.jeesite.modules.weixin.dao.AgentWeixinMenuDao;

*//**
 * 微信菜单Service
 * @author panda
 * @version 2017-09-25
 *//*
@Service
@Transactional(readOnly = true)
public class AgentWeixinMenuService extends TreeService<AgentWeixinMenuDao, AgentWeixinMenu> {

	public AgentWeixinMenu get(String id) {
		return super.get(id);
	}
	
	public List<AgentWeixinMenu> findList(AgentWeixinMenu agentWeixinMenu) {
		if (StringUtils.isNotBlank(agentWeixinMenu.getParentIds())){
			agentWeixinMenu.setParentIds(","+agentWeixinMenu.getParentIds()+",");
		}
		return super.findList(agentWeixinMenu);
	}
	
	@Transactional(readOnly = false)
	public void save(AgentWeixinMenu agentWeixinMenu) {
		if(agentWeixinMenu.getType().equals("click")){
			agentWeixinMenu.setMenuKey("KEY_"+System.currentTimeMillis());
		}else{
			agentWeixinMenu.setMenuKey("");
		}
			
		super.save(agentWeixinMenu);
	}
	
	@Transactional(readOnly = false)
	public void delete(AgentWeixinMenu agentWeixinMenu) {
		super.delete(agentWeixinMenu);
	}
	
	public AgentWeixinMenu loadByKey(String key) {
		return this.dao.loadByKey(key);
	}
	
	public List<WeixinMenuDto> generateWeixinMenuDto(){
		List<AgentWeixinMenu> menus = this.dao.listAll();
		List<WeixinMenuDto> menuDtos = new ArrayList<WeixinMenuDto>();
		System.out.println("从数据库获取所有的菜单："+JsonUtil.getInstance().obj2json(menus));
		WeixinMenuDto wmd = null;
		for(AgentWeixinMenu wm:menus) {
			wmd = new WeixinMenuDto();
			wmd.setKey(wm.getMenuKey());
			wmd.setName(wm.getName());
			wmd.setType(wm.getType());
			wmd.setId(wm.getId());
			wmd.setUrl(wm.getUrl());
			if("0".equals(wm.getPid())) {
				if(wmd.getSub_button()==null) {
					wmd.setSub_button(new ArrayList<WeixinMenuDto>());
				}
				menuDtos.add(wmd);
			} else {
				WeixinMenuDto twmd = findById(wm.getPid(), menuDtos);
				if(twmd!=null) {
					twmd.getSub_button().add(wmd);
				}
			}
		}
		return menuDtos;
	}
	
	private WeixinMenuDto findById(String pid,List<WeixinMenuDto> wmds) {
		if(wmds!=null){
			for(WeixinMenuDto wmd:wmds) {
				if(wmd.getId().equals(pid)) {
					return wmd;
				}
			}
		}
		return null;
	}
}*/