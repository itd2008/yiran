package com.yiran.common.base;

import java.io.Serializable;

/**
 * ActEntity基类
 * 
 * @author yiran
 *
 */
public class BaseActDto implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 当前记录起始索引 */
    private Integer pageNum;

    /** 每页显示记录数 */
    private Integer pageSize;

    /** 总记录数 */
    private Integer total;

    public Integer getPageNum()
    {
        if (pageNum == null)
        {
            pageNum = 0;
        }
        pageNum = (pageNum - 1) * getPageSize();
        if (pageNum < 0)
        {
            pageNum = 0;
        }
        return pageNum;
    }

    public void setPageNum(Integer pageNum)
    {
        this.pageNum = pageNum;
    }

    public Integer getPageSize()
    {
        if (pageSize == null)
        {
            pageSize = 10;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
    }

    public Integer getTotal()
    {
        return total;
    }

    public void setTotal(Integer total)
    {
        this.total = total;
    }
}
