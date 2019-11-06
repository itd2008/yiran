package com.yiran.paychannel.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.util.CollectionUtils;

import com.yiran.common.base.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * 资金渠道接口表 tm_fund_channel_api
 * 
 * @author yiran
 * @date 2019-04-19
 */
public class TmFundChannelApi extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 接口代码 */
	private String apiCode;
	/** 资金源编码 */
	private String fundChannelCode;
	/** 接口类型：SP（单笔支付），BP（批量支付），SQ（单笔查询），BQ（批量查询），SR（单笔退款），BR（批量退款） */
	private String apiType;
	/** 接口版本 */
	private String apiVersion;
	/** 接口方法 */
	private String apiMethod;
	/** 接口URI */
	private String apiUri;
	/** 优先级 */
	private Integer apiPriority;
	/** 是否需要密钥证书 */
	private String secure;
	/** 最大笔数 */
	private Integer maxItem;
	/** 通信标题模板，可用作文件名模板 */
	private String commTitleTemplate;
	/** 通信内容模板 */
	private String commContentTemplate;
	/** 复核解析脚本 */
	private String checkParseScript;
	/** 结果解析脚本 */
	private String resultParseScript;
	/** 是否需要审核：Y（是）；N（否） */
	private String needConfirm;
	/** 是否可用：Y（可用），N（不可用） */
	private String enables;
	/** 创建时间 */
	private Date gmtCreate;
	/** 最后修改时间 */
	private Date gmtModified;
	/** 备注 */
	private String memo;
	/** 订单号生成模式 */
	private Integer apiNoModeId;
	/** 渠道接口签名，加密方式 */
	private Integer apiSecurityId;
	/** 渠道耗时，单位小时 */
	private Double consumeTime;
	/** 文件类型txt，excel */
	private String fileType;
	/** excel文件模板路径 */
	private String filePath;
	/** API名称 */
	private String apiName;
	/** Y需要同步流水, N不需要同步流水 */
	private String needSyncGlide;
	/** 扩展信息 */
    private String extension;
	
	/** 特性 */
    private List<TmFundChannelExt>      exts;

    /** 如何生产机构订单号码 */
    private TmApiNoMode                 apiNoMode;
	/**
	 * 接口参数
	 */
	List<TmFundChannelApiParam> paramList;
	
	/** api金额限制 */
    private List<ApiAmountLimit>      amountLimits;

    public List<ApiAmountLimit> getAmountLimits() {
        return amountLimits;
    }

    public void setAmountLimits(List<ApiAmountLimit> amountLimits) {
        this.amountLimits = amountLimits;
    }
    
    public ApiAmountLimit getAmountLimit() {
        if (CollectionUtils.isEmpty(amountLimits)) {
            return null;
        }
        return amountLimits.get(0);
    }
	public void setApiCode(String apiCode) 
	{
		this.apiCode = apiCode;
	}

	public String getApiCode() 
	{
		return apiCode;
	}
	public void setFundChannelCode(String fundChannelCode) 
	{
		this.fundChannelCode = fundChannelCode;
	}

	public String getFundChannelCode() 
	{
		return fundChannelCode;
	}
	public void setApiType(String apiType) 
	{
		this.apiType = apiType;
	}

	public String getApiType() 
	{
		return apiType;
	}
	public void setApiVersion(String apiVersion) 
	{
		this.apiVersion = apiVersion;
	}

	public String getApiVersion() 
	{
		return apiVersion;
	}
	public void setApiMethod(String apiMethod) 
	{
		this.apiMethod = apiMethod;
	}

	public String getApiMethod() 
	{
		return apiMethod;
	}
	public void setApiUri(String apiUri) 
	{
		this.apiUri = apiUri;
	}

	public String getApiUri() 
	{
		return apiUri;
	}
	public void setApiPriority(Integer apiPriority) 
	{
		this.apiPriority = apiPriority;
	}

	public Integer getApiPriority() 
	{
		return apiPriority;
	}
	public void setSecure(String secure) 
	{
		this.secure = secure;
	}

	public String getSecure() 
	{
		return secure;
	}
	public void setMaxItem(Integer maxItem) 
	{
		this.maxItem = maxItem;
	}

	public Integer getMaxItem() 
	{
		return maxItem;
	}
	public void setCommTitleTemplate(String commTitleTemplate) 
	{
		this.commTitleTemplate = commTitleTemplate;
	}

	public String getCommTitleTemplate() 
	{
		return commTitleTemplate;
	}
	public void setCommContentTemplate(String commContentTemplate) 
	{
		this.commContentTemplate = commContentTemplate;
	}

	public String getCommContentTemplate() 
	{
		return commContentTemplate;
	}
	public void setCheckParseScript(String checkParseScript) 
	{
		this.checkParseScript = checkParseScript;
	}

	public String getCheckParseScript() 
	{
		return checkParseScript;
	}
	public void setResultParseScript(String resultParseScript) 
	{
		this.resultParseScript = resultParseScript;
	}

	public String getResultParseScript() 
	{
		return resultParseScript;
	}
	public void setNeedConfirm(String needConfirm) 
	{
		this.needConfirm = needConfirm;
	}

	public String getNeedConfirm() 
	{
		return needConfirm;
	}
	public void setEnables(String enables) 
	{
		this.enables = enables;
	}

	public String getEnables() 
	{
		return enables;
	}
	public void setGmtCreate(Date gmtCreate) 
	{
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtCreate() 
	{
		return gmtCreate;
	}
	public void setGmtModified(Date gmtModified) 
	{
		this.gmtModified = gmtModified;
	}

	public Date getGmtModified() 
	{
		return gmtModified;
	}
	public void setMemo(String memo) 
	{
		this.memo = memo;
	}

	public String getMemo() 
	{
		return memo;
	}
	public void setApiNoModeId(Integer apiNoModeId) 
	{
		this.apiNoModeId = apiNoModeId;
	}

	public Integer getApiNoModeId() 
	{
		return apiNoModeId;
	}
	public void setApiSecurityId(Integer apiSecurityId) 
	{
		this.apiSecurityId = apiSecurityId;
	}

	public Integer getApiSecurityId() 
	{
		return apiSecurityId;
	}
	public void setConsumeTime(Double consumeTime) 
	{
		this.consumeTime = consumeTime;
	}

	public Double getConsumeTime() 
	{
		return consumeTime;
	}
	public void setFileType(String fileType) 
	{
		this.fileType = fileType;
	}

	public String getFileType() 
	{
		return fileType;
	}
	public void setFilePath(String filePath) 
	{
		this.filePath = filePath;
	}

	public String getFilePath() 
	{
		return filePath;
	}
	public void setApiName(String apiName) 
	{
		this.apiName = apiName;
	}

	public String getApiName() 
	{
		return apiName;
	}
	public void setNeedSyncGlide(String needSyncGlide) 
	{
		this.needSyncGlide = needSyncGlide;
	}

	public String getNeedSyncGlide() 
	{
		return needSyncGlide;
	}
	
    
	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public List<TmFundChannelExt> getExts() {
		return exts;
	}

	public void setExts(List<TmFundChannelExt> exts) {
		this.exts = exts;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("apiCode", getApiCode())
            .append("fundChannelCode", getFundChannelCode())
            .append("apiType", getApiType())
            .append("apiVersion", getApiVersion())
            .append("apiMethod", getApiMethod())
            .append("apiUri", getApiUri())
            .append("apiPriority", getApiPriority())
            .append("secure", getSecure())
            .append("maxItem", getMaxItem())
            .append("commTitleTemplate", getCommTitleTemplate())
            .append("commContentTemplate", getCommContentTemplate())
            .append("checkParseScript", getCheckParseScript())
            .append("resultParseScript", getResultParseScript())
            .append("needConfirm", getNeedConfirm())
            .append("enables", getEnables())
            .append("gmtCreate", getGmtCreate())
            .append("gmtModified", getGmtModified())
            .append("memo", getMemo())
            .append("apiNoModeId", getApiNoModeId())
            .append("apiSecurityId", getApiSecurityId())
            .append("consumeTime", getConsumeTime())
            .append("fileType", getFileType())
            .append("filePath", getFilePath())
            .append("apiName", getApiName())
            .append("needSyncGlide", getNeedSyncGlide())
            .append("extension", getExtension())
            .append("paramList", getParamList())
            .toString();
    }

	public List<TmFundChannelApiParam> getParamList() {
		return paramList;
	}

	public void setParamList(List<TmFundChannelApiParam> paramList) {
		this.paramList = paramList;
	}

	public TmApiNoMode getApiNoMode() {
		return apiNoMode;
	}

	public void setApiNoMode(TmApiNoMode apiNoMode) {
		this.apiNoMode = apiNoMode;
	}

}
