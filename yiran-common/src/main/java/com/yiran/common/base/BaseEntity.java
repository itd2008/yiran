package com.yiran.common.base;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Entity基类
 * 
 * @author yiran
 */
public class BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 搜索值 */
    private String searchValue;

    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** 备注 */
    private String remark;

    /** 请求参数 */
    private Map<String, Object> params;
    
    /**
     * 分页总数
     */
    private Long count;


    public String getSearchValue()
    {
        return searchValue;
    }

    public void setSearchValue(String searchValue)
    {
        this.searchValue = searchValue;
    }

    public String getCreateBy()
    {
        return createBy;
    }

    public void setCreateBy(String createBy)
    {
        this.createBy = createBy;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public String getUpdateBy()
    {
        return updateBy;
    }

    public void setUpdateBy(String updateBy)
    {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public Map<String, Object> getParams()
    {
        if (params == null)
        {
            params = new HashMap<>();
        }
        return params;
    }

    public void setParams(Map<String, Object> params)
    {
        this.params = params;
    }
    
    public int[] paging() {

        Object pageNum = this.getParams().get("pageNum");
        Object pageSize = this.getParams().get("pageSize");

        int[] paging = new int[2];
        if (pageNum != null) {
            paging[0] = Integer.parseInt(String.valueOf(pageNum));
        }
        if (pageSize != null) {
            paging[1] = Integer.parseInt(String.valueOf(pageSize));
        }
        if (pageNum == null) {
            paging[0] = 0;
        }
        if (pageSize == null) {
            paging[1] = 10;
        }
        paging[0] = (paging[0]-1)* paging[1];
        if (paging[0]<0){
            paging[0]=0;
        }
        return paging;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
