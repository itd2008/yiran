package com.yiran.weixin.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.yiran.system.domain.SysMenu;
import com.yiran.weixin.domain.WeixinMenu;


/**
 * 权限数据处理
 * 
 * @author yiran
 */
public class TreeUtils
{

    /**
     * 根据父节点的ID获取所有子节点
     * 
     * @param list 分类表
     * @param typeId 传入的父节点ID
     * @return String
     */
    public static List<SysMenu> getChildPerms(List<SysMenu> list, int parentId)
    {
        List<SysMenu> returnList = new ArrayList<SysMenu>();
        for (Iterator<SysMenu> iterator = list.iterator(); iterator.hasNext();)
        {
        	SysMenu t = (SysMenu) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId() == parentId)
            {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }
    
    public static List<WeixinMenu> getWeixinMenuChildPerms(List<WeixinMenu> list, int parentId)
    {
        List<WeixinMenu> returnList = new ArrayList<WeixinMenu>();
        for (Iterator<WeixinMenu> iterator = list.iterator(); iterator.hasNext();)
        {
        	WeixinMenu t = (WeixinMenu) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId() == parentId)
            {
            	recursionFnWeixinMenu(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     * 
     * @param list
     * @param Menu
     */
    private static void recursionFnWeixinMenu(List<WeixinMenu> list, WeixinMenu t)
    {
        // 得到子节点列表
        List<WeixinMenu> childList = getChildListWeixinMenu(list, t);
        t.setChildren(childList);
        for (WeixinMenu tChild : childList)
        {
            if (hasChildWeixinMenu(list, tChild))
            {
                // 判断是否有子节点
                Iterator<WeixinMenu> it = childList.iterator();
                while (it.hasNext())
                {
                	WeixinMenu n = (WeixinMenu) it.next();
                	recursionFnWeixinMenu(list, n);
                }
            }
        }
    }

    private static void recursionFn(List<SysMenu> list, SysMenu t)
    {
        // 得到子节点列表
        List<SysMenu> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysMenu tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                // 判断是否有子节点
                Iterator<SysMenu> it = childList.iterator();
                while (it.hasNext())
                {
                	SysMenu n = (SysMenu) it.next();
                    recursionFn(list, n);
                }
            }
        }
    }

    
    /**
     * 得到子节点列表
     */
    private static List<WeixinMenu> getChildListWeixinMenu(List<WeixinMenu> list, WeixinMenu t)
    {

        List<WeixinMenu> tlist = new ArrayList<WeixinMenu>();
        Iterator<WeixinMenu> it = list.iterator();
        while (it.hasNext())
        {
        	WeixinMenu n = (WeixinMenu) it.next();
            if (n.getParentId().longValue() == t.getMenuId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }
    
    private static List<SysMenu> getChildList(List<SysMenu> list, SysMenu t)
    {

        List<SysMenu> tlist = new ArrayList<SysMenu>();
        Iterator<SysMenu> it = list.iterator();
        while (it.hasNext())
        {
        	SysMenu n = (SysMenu) it.next();
            if (n.getParentId().longValue() == t.getMenuId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    List<SysMenu> returnList = new ArrayList<SysMenu>();

    /**
     * 根据父节点的ID获取所有子节点
     * 
     * @param list 分类表
     * @param typeId 传入的父节点ID
     * @param prefix 子节点前缀
     */
    public List<SysMenu> getChildPerms(List<SysMenu> list, int typeId, String prefix)
    {
        if (list == null)
        {
            return null;
        }
        for (Iterator<SysMenu> iterator = list.iterator(); iterator.hasNext();)
        {
        	SysMenu node = (SysMenu) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (node.getParentId() == typeId)
            {
                recursionFn(list, node, prefix);
            }
            // 二、遍历所有的父节点下的所有子节点
            /*
             * if (node.getParentId()==0) { recursionFn(list, node); }
             */
        }
        return returnList;
    }

    private void recursionFn(List<SysMenu> list, SysMenu node, String p)
    {
        // 得到子节点列表
        List<SysMenu> childList = getChildList(list, node);
        if (hasChild(list, node))
        {
            // 判断是否有子节点
            returnList.add(node);
            Iterator<SysMenu> it = childList.iterator();
            while (it.hasNext())
            {
            	SysMenu n = (SysMenu) it.next();
                n.setMenuName(p + n.getMenuName());
                recursionFn(list, n, p + p);
            }
        }
        else
        {
            returnList.add(node);
        }
    }

    /**
     * 判断是否有子节点
     */
    private static boolean hasChildWeixinMenu(List<WeixinMenu> list, WeixinMenu t)
    {
        return getChildListWeixinMenu(list, t).size() > 0 ? true : false;
    }
    
    private static boolean hasChild(List<SysMenu> list, SysMenu t)
    {
        return getChildList(list, t).size() > 0 ? true : false;
    }
}
