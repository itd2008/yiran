package com.yiran.member.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

import com.yiran.member.domain.MemberContactInfo;


/**
 * <p>联系人工具类</p>
 */
public class MemberContactUtil {
    /**
     * 本方法返回按照联系人首字母按照英文字母A—Z，默认#分组返回
     * @param contacts
     * @return
     */
    public static Map<String,List<MemberContactInfo>> convertToMapAsFirstLetter(List<MemberContactInfo> contacts){
        Map<String,List<MemberContactInfo>> resMap = new TreeMap<String,List<MemberContactInfo>>();
        if(null == contacts || contacts.size() == 0){
            return null;
        } else {
            // 按拼音排序
            Collections.sort(contacts);
            ArrayList<MemberContactInfo> infos = new ArrayList<MemberContactInfo>();
            ArrayList<MemberContactInfo> defaultInfos = new ArrayList<MemberContactInfo>();
            
            for(int i = 0;i < contacts.size(); i++){
                MemberContactInfo curOne = contacts.get(i);
                if(StringUtils.isEmpty(curOne.getPinyin())){
                    defaultInfos.add(curOne);
                } else {
                    String curkey = curOne.getPinyin().substring(0,1).toUpperCase();
                    //只判断A——Z其他都属于默认
                    if(curkey.compareToIgnoreCase("A") >= 0 && curkey.compareToIgnoreCase("Z") <=0 ){
                        if(!resMap.containsKey(curkey)){
                            // 未赋值
                            infos = new ArrayList<MemberContactInfo>();
                            resMap.put(curkey, infos);
                            infos.add(curOne);
                        } else {
                            //原list存在
                            resMap.get(curkey).add(curOne);
                        }
                    } else {
                        defaultInfos.add(curOne);
                    }
                }
            } 
            if(defaultInfos.size() > 0){
                // 未赋值
                resMap.put("#", defaultInfos);
            }
        }
        return resMap;
    }
}
