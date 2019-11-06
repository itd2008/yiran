package com.yiran.payorder.domain;

import java.util.List;

public abstract class Result
{
	  public static final String RESULT_CODE_SUCCESS = "success";
	  public static final String RESULT_CODE_FAILED = "failed";
	  public static final String RESULT_CODE_INIT = "init";
	  public static final String RESULT_CODE_PASS = "000";
	  public static final String RESULT_CODE_REJECT = "001";
	  public static final String RESULT_CODE_MANUAL = "002";
	  public static final String RESULT_CODE_WARN = "003";
	  public static final String RESULT_CODE_MONITOR = "004";
	  public static final String RESULT_CODE_EXCEPTION = "999";
	  public static final String RESULT_CODE_ATTENTION = "005";
	  public static final String RESULT_MSG_PASS = "pass";
	  String result;
	  String msg;
	  String ruleNo;
	  String ruleDescription;
	  List<String> rulesNotPassed;

	  public Result(String result, String msg)
	  {
	    this.result = result;
	    this.msg = msg;
	  }

	  public String getResult() {
	    return this.result;
	  }
	  public void setResult(String result) {
	    this.result = result;
	  }
	  public String getMsg() {
	    return this.msg;
	  }
	  public void setMsg(String msg) {
	    this.msg = msg;
	  }

	  public List<String> getRulesNotPassed()
	  {
	    return this.rulesNotPassed;
	  }
	  public void setRulesNotPassed(List<String> rulesNotPassed) {
	    this.rulesNotPassed = rulesNotPassed;
	  }

	  public boolean isPriorTo(Result result)
	  {
	    if (null == result) {
	      return true;
	    }
	    if ("000".equals(this.result)) {
	      return false;
	    }
	    if ("000".equals(result.getResult())) {
	      return true;
	    }
	    return this.result.compareTo(result.getResult()) < 0;
	  }
	  public String getRuleNo() {
	    return this.ruleNo;
	  }
	  public void setRuleNo(String ruleNo) {
	    this.ruleNo = ruleNo;
	  }
	  public String getRuleDescription() {
	    return this.ruleDescription;
	  }
	  public void setRuleDescription(String ruleDescription) {
	    this.ruleDescription = ruleDescription;
	  }
	}