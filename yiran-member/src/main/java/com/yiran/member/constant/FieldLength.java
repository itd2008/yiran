package com.yiran.member.constant;

/**
 * <p>接口字段长度限定</p>
 */
public class FieldLength {

    /** 字段长度限定:会员名称 */
    public static final int MEMBER_NAME              = 90;  //数据库长度-预留后缀

    /** 字段长度限定:会员简称 */
    public static final int MEMBER_SHORT_NAME        = 40;  //数据库长度-预留后缀

    /** 字段长度限定:地区码 */
    public static final int DISTRICT_CODE            = 6;

    /** 字段长度限定:组织机构号 */
    public static final int LICENSE_NO               = 128;

    /** 字段长度限定:企业编号 */
    public static final int COMPANY_NO               = 50;

    /** 字段长度限定:企业法人 */
    public static final int LEGAL_PERSON             = 50;

    /** 字段长度限定:会员编号 */
    public static final int MEMBER_ID                = 24;

    /** 字段长度限定:商户名称 */
    public static final int MERCHANT_NAME            = 235;

    /** 字段长度限定：puid*/
    public static final int PUID                     = 50;

    /** 字段长度限定：identity*/
    public static final int IDENTITY                 = 50;

    /** 字段长度限定：identity pid */
    public static final int IDENTITY_PID             = 10;

    /** 字段长度限定：默认登录名 */
    public static final int DEFAULT_LOGIN_NAME       = 50;

    /** 字段长度限定：真实姓名 */
    public static final int TRUE_NAME                = 50;

    /** 字段长度限定： 证件号 */
    public static final int ID_NO                    = 50;

    /** 字段长度限定：生日 */
    public static final int BIRTHDAY                 = 8;

    /** 字段长度限定：机构编号 */
    public static final int INSTITUTION_NO           = 50;

    /** 字段长度限定：集团名称*/
    public static final int ORG_NAME                 = 235;

    /** 字段长度限定：联系人：联系人名称  */
    public static final int CONTACT_NAME             = 50;

    /** 字段长度限定：联系人：城镇/区/街  */
    public static final int TOWN                     = 100;

    /** 字段长度限定：联系人：地址 */
    public static final int ADDRESS                  = 255;

    /** 字段长度限定：联系人：邮政编码 */
    public static final int POSTCODE                 = 6;

    /** 字段长度限定：联系人：网站    */
    public static final int WEBSITE                  = 128;

    /** 字段长度限定：联系人：传真 */
    public static final int FAX                      = 20;

    /** 字段长度限定：联系人：手机 */
    public static final int MOBILE                   = 20;

    /** 字段长度限定：联系人：电话 */
    public static final int TEL                      = 20;

    /** 字段长度限定：联系人：邮箱 */
    public static final int EMAIL                    = 50;

    /** 字段长度限定：联系人：QQ */
    public static final int QQ                       = 50;

    /** 字段长度限定：联系人：MSN */
    public static final int MSN                      = 50;

    /** 字段长度限定：联系人：职位 */
    public static final int POSITION                 = 50;

    /** 字段长度限定：联系人：部门 */
    public static final int DEPT                     = 50;

    /** 字段长度限定：联系人编号 */
    public static final int CONTACT_ID               = 18;

    /** 字段长度限定：联系人国家 */
    public static final int COUNTRY                  = 255;

    /** 字段长度限定：联系人省 */
    public static final int PROVINCE                 = 255;

    /** 字段长度限定：联系人市 */
    public static final int CITY                     = 255;

    /** 字段长度限定：集团编号 */
    public static final int ORG_ID                   = 13;

    /** 字段长度限定：操作员编号 */
    public static final int OPERATOR_ID              = 13;

    /** 字段长度限定：账户编号 */
    public static final int ACCOUNT_ID               = 50;
    
    /** 字段长度限定：账户类型 */
    public static final int ACCOUNT_TYPE             = 32;

    /** 字段长度限定：支付密码最大长度 */
    public static final int PAY_PASSWORD_MAX         = 64;

    /** 字段长度限定：支付密码最小长度 */
    public static final int PAY_PASSWORD_MIN         = 6;

    /** 字段长度限定：商户号 */
    public static final int MERCHANT_ID              = 10;

    /** 字段长度限定：验证实体 */
    public static final int VERIFY_ENTITY            = 50;

    /** 字段长度限定：登录名 */
    public static final int LOGIN_NAME               = 50;

    /** 字段长度限定：uuid唯一编号 */
    public static final int UUID                     = 50;

    /** 字段长度限定：银行编号 */
    public static final int BANK_ID                  = 10;

    /** 字段长度限定：银行名称 */
    public static final int BANK_NAME                = 50;

    /** 字段长度限定：支行名称 */
    public static final int BANK_BRANCH              = 255;

    /** 字段长度限定：支行所在省份 */
    public static final int BANK_PROVINCE            = 128;

    /** 字段长度限定：支行所在城市*/
    public static final int BANK_CITY                = 128;

    /** 字段长度限定：户名*/
    public static final int REAL_NAME                = 90;

    /** 字段长度限定：银行账号*/
    public static final int BANK_ACCOUNT_NO          = 50;

    /** 字段长度限定：协议号 */
    public static final int BANK_AGREEMENT_NO        = 128;

    public static final int BANK_ACCOUNT_NO_MASK     = 50;

    public static final int BANK_ALIAS               = 128;

    public static final int BANK_CARD_SKIN           = 32;

    public static final int BANK_IS_SIGNING          = 1;

    /** 字段长度限定：协议号 */
    public static final int BANK_SIGN_NO             = 64;

    /** 字段长度限定：内部协议号 */
    public static final int BANK_SIGN_ID             = 64;

    /** 字段长度限定：证件号 */
    public static final int BANK_CERT_NO             = 32;

    /** 字段长度限定：cvv2 */
    public static final int BANK_CV_NO               = 16;

    /** 字段长度限定：卡有效期 */
    public static final int BANK_CARD_VALID_DATE     = 32;

    /** 字段长度限定：渠道编号 */
    public static final int BANK_CHANNEL_CODE        = 32;

    /** 字段长度限定：操作员昵称 */
    public static final int NICK_NAME                = 50;

    /** 字段长度限定：IP地址 */
    public static final int FROM_IP                  = 30;

    /** 字段长度限定：请求发起人*/
    public static final int REQUEST_OPERATOR         = 32;

    /** 字段长度限定：账户别名 */
    public static final int ALIAS                    = 200;

    /** 字段长度限定：原始请求序列号 */
    public static final int ORIGINAL_REQUEST_NO      = 64;

    /** 字段长度限定：请求序列号 */
    public static final int REQUEST_NO               = 64;

    /** 字段长度限定：请求编号 */
    public static final int REQUEST_CODE             = 32;

    /** 字段长度限定：来源ID */
    public static final int SOURCE_ID                = 32;

    /** 字段长度限定：MAC地址 */
    public static final int MAC                      = 32;

    /** 字段长度限定：Extension */
    public static final int EXTENSION                = 128;

    public static final int MEMO                     = 255;

    public static final int BANK_MEMO                = 512;
    /** 字段长度限定：登录密码 */
    public static final int LOGIN_PASSWORD           = 64;

    /** 字段长度限定：外部用户ID */
    public static final int USER_EXTERNAL_ID         = 64;

    public static final int SDP_CARD_NO              = 20;

    public static final int TARGET_ID                = 32;

    /** 字段长度限定：图片路径 */
    public static final int IMG_PATH                 = 255;

    /** 字段长度限定：企业名称*/
    public static final int COMPANY_NAME             = 256;

    /** 字段长度限定：企业信息 */
    public static final int BUSINESS_SCOPE           = 1024;
    public static final int CLEARING_ACCOUNT_PATH    = 1024;
    public static final int ICP_LICENSE_PATH         = 1024;
    public static final int INDUSTRY_LICENSE_PATH    = 1024;
    public static final int INSTITUTION_LICENSE_PATH = 1024;
    public static final int LEGAL_PERSON_PHONE       = 20;
    public static final int LICENSE_ADDRESS          = 256;
    public static final int LICENSE_NO_PATH          = 1024;
    public static final int ORGANIZATION_NO          = 128;
    public static final int ORGANIZATION_NO_PATH     = 1024;
    public static final int SUMMARY                  = 2048;
    public static final int TAX_NO_PATH              = 1024;
    public static final int TELEPHONE                = 20;
    public static final int LICENSE_NAME          = 256;
    public static final int BUSINESS_WEBSITE         = 1024;
    public static final int PROXY_PERSON         = 100;
    /** 字段长度限定：会员联系人编号 */
    public static final int MEMBER_CONTACT_ID         = 18;
    /** 字段长度限定：会员联系人最小账户长度 */
    public static final int MEMBER_CONTACT_BANKNO_MIN        = 8;
    /** 字段长度限定：会员联系人最大账户长度 */
    public static final int MEMBER_CONTACT_BANKNO_MAX        = 32;
}
