package com.yiran.web.controller.payorder;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yiran.common.annotation.Log;
import com.yiran.common.enums.BusinessType;
import com.yiran.payorder.domain.PayInstOrderResult;
import com.yiran.payorder.domaindo.PayInstOrderResultDO;
import com.yiran.payorder.service.IPayInstOrderResultService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 机构订单结果 信息操作处理
 * 
 * @author yiran
 * @date 2019-07-13
 */
@Controller
@RequestMapping("/payorder/payInstOrderResult")
public class PayInstOrderResultController extends BaseController
{
    private String prefix = "payorder/payInstOrderResult";
	
	@Autowired
	private IPayInstOrderResultService payInstOrderResultService;
	
	@RequiresPermissions("payorder:payInstOrderResult:view")
	@GetMapping()
	public String payInstOrderResult()
	{
	    return prefix + "/payInstOrderResult";
	}
	
	/**
	 * 查询机构订单结果列表
	 */
	@RequiresPermissions("payorder:payInstOrderResult:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PayInstOrderResultDO payInstOrderResult)
	{
		startPage();
        List<PayInstOrderResultDO> list = payInstOrderResultService.selectPayInstOrderResultList(payInstOrderResult);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出机构订单结果列表
	 */
	@RequiresPermissions("payorder:payInstOrderResult:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PayInstOrderResultDO payInstOrderResult)
    {
    	List<PayInstOrderResultDO> list = payInstOrderResultService.selectPayInstOrderResultList(payInstOrderResult);
        ExcelUtil<PayInstOrderResultDO> util = new ExcelUtil<PayInstOrderResultDO>(PayInstOrderResultDO.class);
        return util.exportExcel(list, "payInstOrderResult");
    }
	
	/**
	 * 新增机构订单结果
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存机构订单结果
	 */
	@RequiresPermissions("payorder:payInstOrderResult:add")
	@Log(title = "机构订单结果", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(PayInstOrderResultDO payInstOrderResult)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(payInstOrderResultService.insertPayInstOrderResult(payInstOrderResult));
	}

	/**
	 * 修改机构订单结果
	 */
	@GetMapping("/edit/{resultId}")
	public String edit(@PathVariable("resultId") Integer resultId, ModelMap mmap)
	{
		PayInstOrderResultDO payInstOrderResult = payInstOrderResultService.selectPayInstOrderResultById(resultId);
		mmap.put("payInstOrderResult", payInstOrderResult);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存机构订单结果
	 */
	@RequiresPermissions("payorder:payInstOrderResult:edit")
	@Log(title = "机构订单结果", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(PayInstOrderResultDO payInstOrderResult)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(payInstOrderResultService.updatePayInstOrderResult(payInstOrderResult));
	}
	
	/**
	 * 删除机构订单结果
	 */
	@RequiresPermissions("payorder:payInstOrderResult:remove")
	@Log(title = "机构订单结果", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(payInstOrderResultService.deletePayInstOrderResultByIds(ids));
	}
	
}
