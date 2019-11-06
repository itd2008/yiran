/**
 * 
 */
package com.yiran.member.request;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.member.base.Request;

/**
 * <p>验证登录密码是否设置与锁定</p>
 */
public class ValidateLoginPwdRequest extends Request {
    
    /**
     * 
     */
    private static final long serialVersionUID = -4104644460551712224L;

    private String operatorId;

    private Integer validateMode; //0: 是否设置登录密码，密码是否锁定，密码是否是初始状态 1：是否设置登录密码 (默认)，2：登录密码是否被锁定,3：登录密码是否是初状态

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getValidateMode() {
        return validateMode;
    }

    public void setValidateMode(Integer validateMode) {
        this.validateMode = validateMode;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
