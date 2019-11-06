package com.yiran.api.facade;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yiran.common.base.ResultWrapper;
import com.yiran.framework.web.base.BaseController;
import com.yiran.member.base.Response;
import com.yiran.member.domain.MemberTmMember;
import com.yiran.member.domain.MemberTrCompanyMember;
import com.yiran.member.request.ActivatePersonalRequest;
import com.yiran.member.request.CompanyMemberQueryRequest;
import com.yiran.member.request.CreateMemberInfoRequest;
import com.yiran.member.request.IntegratedCompanyRequest;
import com.yiran.member.request.IntegratedPersonalRequest;
import com.yiran.member.request.MemberIntegratedIdRequest;
import com.yiran.member.request.MemberIntegratedRequest;
import com.yiran.member.request.PersonalMemberInfoRequest;
import com.yiran.member.request.PersonalMemberQueryRequest;
import com.yiran.member.request.UpdateMemberLockStatusRequest;
import com.yiran.member.response.ActivatePersonalResponse;
import com.yiran.member.response.CreateMemberInfoResponse;
import com.yiran.member.response.IntegratedCompanyResponse;
import com.yiran.member.response.IntegratedPersonalResponse;
import com.yiran.member.response.MemberIntegratedResponse;
import com.yiran.member.service.IMemberTmMemberService;
import com.yiran.member.service.IMemberTrCompanyMemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 会员管理
 * @author pandaa
 *
 */
@RestController
@RequestMapping("/api/yiran/member")
@Api(value="会员管理接口",description="会员管理接口")
public class MemberFacade extends BaseController{
	private Logger        logger = LoggerFactory.getLogger(MemberFacade.class);
	@Autowired
	private IMemberTmMemberService memberTmMemberService;
	@Autowired
	private IMemberTrCompanyMemberService memberTrCompanyMemberService;
	
	@PostMapping("/queryPersonalMember")
    @ApiOperation(value = "查询个人会员",notes="查询个人会员")
	public ResultWrapper<Map<String,Object>> queryPersonalMember(@RequestBody PersonalMemberQueryRequest request) {
		try {
			Map<String, Object> map = new HashMap<>();
			MemberTmMember memberInfo = memberTmMemberService.selectMemberTmMemberById(request.getMemberId());
			if (logger.isInfoEnabled()) {
				logger.info("查询个人会员返回对象 : " + memberInfo);
			}
			return ResultWrapper.ok().putData(memberInfo);
			} catch (Exception e) {
				logger.error("查询个人会员异常 : ", e);
				throw new RuntimeException("调用queryPersonalMember接口异常", e);
		}
	}
	
	 /** 
    * 查询企业会员
    * @param environment
    * @param request
    * @return
    */
	@PostMapping("/queryCompanyMember")
    @ApiOperation(value = "查询企业会员",notes="查询企业会员")
    public ResultWrapper<Map<String,Object>> queryCompanyMember(@RequestBody CompanyMemberQueryRequest request) {
        try {
        	Map<String, Object> map = new HashMap<>();
        	MemberTrCompanyMember companyMember = memberTrCompanyMemberService.selectMemberTrCompanyMemberById(request.getMemberId());
            if (logger.isInfoEnabled()) {
                logger.info("查询企业会员返回对象 : " + companyMember);
            }
            return ResultWrapper.ok().putData(companyMember);
        } catch (Exception e) {
            logger.error("查询企业会员异常 : ", e);
            throw new RuntimeException("调用queryCompanyMember接口异常", e);
        }
    }
	
	/** 
    * 创建会员
    * @param environment
    * @param request
    * @return
    */
	@PostMapping("/createMemberInfo")
    @ApiOperation(value = "创建会员",notes="创建会员")
    public ResultWrapper<Map<String,Object>> createMemberInfo(@RequestBody CreateMemberInfoRequest request) {
        try {
            CreateMemberInfoResponse response = memberTmMemberService.createMemberInfo(request);
            if (logger.isInfoEnabled()) {
                logger.info("创建会员返回对象 : " + response.toString());
            }
            return ResultWrapper.ok().putData(response);
        } catch (Exception e) {
            logger.error("创建会员异常 : ", e);
            throw new RuntimeException("调用createMemberInfo接口异常", e);
        }
    }
	
	/** 
    * 根据会员标识和平台类型查询会员综合信息
    * @param environment
    * @param request
    * @return
    */
	@PostMapping("/queryMemberIntegratedInfo")
    @ApiOperation(value = "根据会员标识和平台类型查询会员综合信息",notes="根据会员标识和平台类型查询会员综合信息")
    public ResultWrapper<Map<String,Object>> queryMemberIntegratedInfo(@RequestBody MemberIntegratedRequest request) {
        try {
            MemberIntegratedResponse response = memberTmMemberService.queryMemberIntegratedInfo(request);
            if (logger.isInfoEnabled()) {
                logger.info("根据会员标识和平台类型查询会员综合信息返回对象 : " + response.toString());
            }
            return ResultWrapper.ok().putData(response);
        } catch (Exception e) {
            logger.error("根据会员标识和平台类型查询会员综合信息异常 : ", e);
            throw new RuntimeException("调用queryMemberIntegratedInfo接口异常", e);
        }
    }

    /** 
    * 根据会员编号查询会员综合信息
    * @param request
    * @return
    */
	@PostMapping("/queryMemberIntegratedInfoById")
    @ApiOperation(value = "根据会员编号查询会员综合信息",notes="根据会员编号查询会员综合信息")
    public ResultWrapper<Map<String,Object>> queryMemberIntegratedInfoById(@RequestBody MemberIntegratedIdRequest request) {
        try {
            MemberIntegratedResponse response = memberTmMemberService.queryMemberIntegratedInfoById(request);
            if (logger.isInfoEnabled()) {
                logger.info("根据会员编号查询会员综合信息返回对象 : " + response.toString());
            }
            return ResultWrapper.ok().putData(response);
        } catch (Exception e) {
            logger.error(" 根据会员编号查询会员综合信息异常 : ", e);
            throw new RuntimeException("调用queryMemberIntegratedInfoById接口异常", e);
        }
    }
	
	/** 
    * 个人会员集成创建
    * @param environment
    * @param request
    * @return
    */
	@PostMapping("/createIntegratedPersonalMember")
    @ApiOperation(value = "个人会员集成创建",notes="个人会员集成创建")
    public ResultWrapper<Map<String,Object>> createIntegratedPersonalMember(@RequestBody IntegratedPersonalRequest request) {
        try {
            IntegratedPersonalResponse response = memberTmMemberService.createIntegratedPersonalMember(request);
            if (logger.isInfoEnabled()) {
                logger.info("个人会员集成创建返回对象 : " + response.toString());
            }
            return ResultWrapper.ok().putData(response);
        } catch (Exception e) {
            logger.error("个人会员集成创建异常 : ", e);
            throw new RuntimeException("调用createIntegratedPersonalMember接口异常", e);
        }
    }
	
	/** 
    * 设置个人会员信息
    * @param environment
    * @param request
    * @return
    */
	@PostMapping("/setPersonalMemberInfo")
    @ApiOperation(value = "设置个人会员信息",notes="设置个人会员信息")
    public ResultWrapper<Map<String,Object>> setPersonalMemberInfo(@RequestBody PersonalMemberInfoRequest request) {
        try {
            Response response = memberTmMemberService.setPersonalMemberInfo(request);
            if (logger.isInfoEnabled()) {
                logger.info(" 设置个人会员信息返回对象 : " + response.toString());
            }
            return ResultWrapper.ok().putData(response);
        } catch (Exception e) {
            logger.error(" 设置个人会员信息异常 : ", e);
            throw new RuntimeException("调用setPersonalMemberInfo接口异常", e);
        }
    }

	/** 
    * 激活个人会员
    * @param environment
    * @param request
    * @return
    */
	@PostMapping("/activatePersonalMemberInfo")
    @ApiOperation(value = "激活个人会员",notes="激活个人会员")
    public ResultWrapper<Map<String,Object>> activatePersonalMemberInfo(@RequestBody ActivatePersonalRequest request) {
        try {
            ActivatePersonalResponse response = memberTmMemberService.activatePersonalMemberInfo(request);
            if (logger.isInfoEnabled()) {
                logger.info("激活个人会员返回对象 : " + response.toString());
            }
            return ResultWrapper.ok().putData(response);
        } catch (Exception e) {
            logger.error("激活个人会员异常 : ", e);
            throw new RuntimeException("调用activatePersonalMemberInfo接口异常", e);
        }
    }
    
    /**
     * 更新会员锁定状态
     * @param environment
     * @param request
     * @return
     */
	@PostMapping("/updateMemberLockStatus")
    @ApiOperation(value = "更新会员锁定状态",notes="更新会员锁定状态")
    public ResultWrapper<Map<String,Object>> updateMemberLockStatus(UpdateMemberLockStatusRequest request){
        try {
            Response response = memberTmMemberService.updateMemberLockStatus(request);
            if (logger.isInfoEnabled()) {
                logger.info("更新会员锁定状态返回对象 : " + response.toString());
            }
            return ResultWrapper.ok().putData(response);
        } catch (Exception e) {
            logger.error("更新会员锁定状态异常 : ", e);
            throw new RuntimeException("调用updateMemberLockStatus接口异常", e);
        }
    }
	
	/** 
    * 设置企业会员信息
    * @param environment
    * @param request
    * @return
    */
    /*public ResultWrapper<Map<String,Object>> setCompanyMember(CompanyInfo request) {
        try {
            Response response = memberTmMemberService.setCompanyMember(environment, request);
            if (logger.isInfoEnabled()) {
                logger.info("设置企业会员信息返回对象 : " + response.toString());
            }
            return ResultWrapper.ok().putData(response);
        } catch (Exception e) {
            logger.error("设置企业会员信息异常 : ", e);
            throw new RuntimeException("调用setCompanyMember接口异常", e);
        }
    }*/

    /** 
    * 激活企业会员
    * @param environment
    * @param request
    * @return
    */
   /* public ResultWrapper<Map<String,Object>> activateCompanyMember(ActivateCompanyRequest request) {
        try {
            ActivateCompanyResponse response = memberTmMemberService.activateCompanyMember(environment,
                request);
            if (logger.isInfoEnabled()) {
                logger.info("激活企业会员返回对象 : " + response.toString());
            }
            return ResultWrapper.ok().putData(response);
        } catch (Exception e) {
            logger.error("激活企业会员异常 : ", e);
            throw new RuntimeException("调用activateCompanyMember接口异常", e);
        }
    }*/
    
    /**
     * 集成创建企业会员
     * @param environment
     * @param request
     */
    public ResultWrapper<Map<String,Object>> createIntegratedCompanyMember(IntegratedCompanyRequest request){
        try {
            IntegratedCompanyResponse response = memberTmMemberService.createIntegratedCompanyMember(request);
            if (logger.isInfoEnabled()) {
                logger.info("集成创建企业会员返回对象 : " + response.toString());
            }
            return ResultWrapper.ok().putData(response);
        } catch (Exception e) {
            logger.error("集成创建企业会员异常 : ", e);
            throw new RuntimeException("调用createIntegratedCompanyMember接口异常", e);
        }
    }

}
