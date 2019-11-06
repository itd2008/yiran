/**
 * 
 */
package com.yiran.member.utils;

import com.yiran.member.enums.ContactTypeEnum;
import com.yiran.member.request.ContactQuery;
import com.yiran.member.request.QueryContactRequest;

/**
 * <p>联系信息领域对象工具类</p>
 */
public class ContactDomainUtil {
   
    /**
     * 联系查询查询请求对象转换成查询对象
     * @param request
     * @return
     */
    public static ContactQuery convertReqToQuery(QueryContactRequest request){
        ContactQuery query = new ContactQuery();
        query.setObjectId(request.getTargetId());
        if(null != request.getContactType()){
            query.setContactType(ContactTypeEnum.getByCode(request.getContactType()));
        }
        return query;
    }

}
