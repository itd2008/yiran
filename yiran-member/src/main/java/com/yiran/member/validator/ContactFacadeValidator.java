/**
 * 
 */
package com.yiran.member.validator;

import com.netfinworks.common.lang.StringUtil;
import com.yiran.member.constant.FieldLength;
import com.yiran.member.domain.MemberTmContact;
import com.yiran.member.enums.ContactTypeEnum;
import com.yiran.member.exception.MaIllegalArgumentException;
import com.yiran.member.request.ContactChangeRequest;
import com.yiran.member.request.ContactRequest;
import com.yiran.member.request.QueryContactRequest;
import com.yiran.member.utils.Utils;

/**
 * <p>ContactFacade 入参验证</p>
 */
public class ContactFacadeValidator {

    /**
     * 新增联系信息时验证入参
     * @param request
     */
    public static void validator(ContactRequest request) {
        if (request == null) {
            throw new MaIllegalArgumentException("联系信息请求未提供");
        }
        
        checkTargetId(request.getTargetId());
        checkContactType(request.getContactType());
        validatorBody(request.getContactInfo());
        checkContactName(request.getContactInfo().getContactName(), true);
    }
    
    /**
     * 修改联系信息时验证请求参数
     * @param request
     */
    public static void validator(ContactChangeRequest request) {
        if (request == null) {
            throw new MaIllegalArgumentException("联系信息请求未提供");
        }

        validatorBody(request.getContactInfo());
        checkContactName(request.getContactInfo().getContactName(), false);
        checkContactId(String.valueOf(request.getContactInfo().getContactId()));
    }
    
    /**
     * 修改联系信息时验证请求参数
     * @param request
     */
    public static void validator(QueryContactRequest request) {
        if (request == null) {
            throw new MaIllegalArgumentException("联系信息查询请求未提供");
        }

        checkTargetId(request.getTargetId());
        checkContactType(request.getContactType());
    }
    
    /*
     * 联系信息的目的对象编号
     */
    private static void checkTargetId(String targetId) {
        if (StringUtil.isBlank(targetId)) {
            throw new MaIllegalArgumentException("联系信息的目的对象编号未提供");
        }
    }
    
    /*
     * 联系信息类型
     */
    private static void checkContactType(String contactType) {
        if (StringUtil.isBlank(contactType)) {
            throw new MaIllegalArgumentException("联系信息类型未提供");
        }
        try {
            checkContactType(Long.valueOf(contactType));
        } catch (NumberFormatException e) {
            throw new MaIllegalArgumentException("联系信息类型错误");
        }
    }
    
    /*
     * 联系信息类型
     */
    private static void checkContactType(Long contactType) {
        if (contactType == null) {
            throw new MaIllegalArgumentException("联系信息类型未提供");
        }
        if (ContactTypeEnum.getByCode(contactType) == null) {
            throw new MaIllegalArgumentException("联系信息类型错误");
        }
    }

    /*
     * 验证联系人姓名
     */
    private static void checkContactName(String contactName, boolean isMust) {
        if (isMust && StringUtil.isEmpty(contactName)) {
            throw new MaIllegalArgumentException("联系人名称不能为空");
        }
        if (Utils.getByteLen(contactName) > FieldLength.CONTACT_NAME) {
            throw new MaIllegalArgumentException("联系人名称长度不能超过" + FieldLength.CONTACT_NAME + "位");
        }

    }

    /**
     * 验证联系信息ID
     * @param contactId
     */
    public static void checkContactId(String contactId) {
        if (StringUtil.isBlank(contactId)) {
            throw new MaIllegalArgumentException("联系信息ID不能为空");
        } else if (Utils.getByteLen(contactId) > FieldLength.CONTACT_ID) {
            throw new MaIllegalArgumentException("联系信息ID不能超过" + FieldLength.CONTACT_ID + "位");
        }
    }

    /*
     * 验证联系信息body其它部分
     */
    private static void validatorBody(MemberTmContact contact) {
        if (contact == null) {
            throw new MaIllegalArgumentException("联系信息未提供");
        }

        /*
         * 判断非必填字段的长度
         */
        if (!StringUtil.isEmpty(contact.getTown())
            && Utils.getByteLen(contact.getTown()) > FieldLength.TOWN) {
            throw new MaIllegalArgumentException("城镇/区/街信息长度不能大于" + FieldLength.TOWN + "位");
        }

        if (!StringUtil.isEmpty(contact.getAddress())
            && Utils.getByteLen(contact.getAddress()) > FieldLength.ADDRESS) {
            throw new MaIllegalArgumentException("地址信息长度不能大于" + FieldLength.ADDRESS + "位");
        }

        if (!StringUtil.isEmpty(contact.getPostcode())
            && Utils.getByteLen(contact.getPostcode()) > FieldLength.POSTCODE) {
            throw new MaIllegalArgumentException("邮政编码长度不能大于" + FieldLength.POSTCODE + "位");
        }

        if (!StringUtil.isEmpty(contact.getWebsite())
            && Utils.getByteLen(contact.getWebsite()) > FieldLength.WEBSITE) {
            throw new MaIllegalArgumentException("网站长度不能大于" + FieldLength.WEBSITE + "位");
        }

        if (!StringUtil.isEmpty(contact.getFax())
            && Utils.getByteLen(contact.getFax()) > FieldLength.FAX) {
            throw new MaIllegalArgumentException("传真长度不能大于" + FieldLength.FAX + "位");
        }

        if (!StringUtil.isEmpty(contact.getMobile())
            && Utils.getByteLen(contact.getMobile()) > FieldLength.MOBILE) {
            throw new MaIllegalArgumentException("手机长度不能大于" + FieldLength.MOBILE + "位");
        }

        if (!StringUtil.isEmpty(contact.getTel())
            && Utils.getByteLen(contact.getTel()) > FieldLength.TEL) {
            throw new MaIllegalArgumentException("电话长度不能大于" + FieldLength.TEL + "位");
        }

        if (!StringUtil.isEmpty(contact.getEmail())
            && Utils.getByteLen(contact.getEmail()) > FieldLength.EMAIL) {
            throw new MaIllegalArgumentException("邮箱长度不能大于" + FieldLength.EMAIL + "位");
        }

        if (!StringUtil.isEmpty(contact.getQq())
            && Utils.getByteLen(contact.getQq()) > FieldLength.QQ) {
            throw new MaIllegalArgumentException("QQ长度不能大于" + FieldLength.QQ + "位");
        }

        if (!StringUtil.isEmpty(contact.getPosition())
            && Utils.getByteLen(contact.getPosition()) > FieldLength.POSITION) {
            throw new MaIllegalArgumentException("职位长度不能大于" + FieldLength.POSITION + "位");
        }

        if (!StringUtil.isEmpty(contact.getDept())
            && Utils.getByteLen(contact.getDept()) > FieldLength.DEPT) {
            throw new MaIllegalArgumentException("部门长度不能大于" + FieldLength.DEPT + "位");
        }

        //国家，省，市只验证长度
        if (!StringUtil.isEmpty(contact.getCountry())
            && Utils.getByteLen(contact.getCountry()) > FieldLength.COUNTRY) {
            throw new MaIllegalArgumentException("国家长度不能大于" + FieldLength.COUNTRY + "位");
        }
        if (!StringUtil.isEmpty(contact.getProvince())
            && Utils.getByteLen(contact.getProvince()) > FieldLength.PROVINCE) {
            throw new MaIllegalArgumentException("省份长度不能大于" + FieldLength.PROVINCE + "位");
        }
        if (!StringUtil.isEmpty(contact.getCity())
            && Utils.getByteLen(contact.getCity()) > FieldLength.CITY) {
            throw new MaIllegalArgumentException("市长度不能大于" + FieldLength.CITY + "位");
        }
    }

}
