/**
 * 
 */
package com.yiran.member.domain;

/**
 * <p>认证信息查询条件</p>
 */
public class VerifyQuery {
    private String  memberId;

    private String  verifyEntity;

    private Integer verifyType;

    /**
     * @return the memberId
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * @param memberId the memberId to set
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    /**
     * @return the verifyEntity
     */
    public String getVerifyEntity() {
        return verifyEntity;
    }

    /**
     * @param verifyEntity the verifyEntity to set
     */
    public void setVerifyEntity(String verifyEntity) {
        this.verifyEntity = verifyEntity;
    }

    /**
     * @return the verifyType
     */
    public Integer getVerifyType() {
        return verifyType;
    }

    /**
     * @param verifyType the verifyType to set
     */
    public void setVerifyType(Integer verifyType) {
        this.verifyType = verifyType;
    }

}
