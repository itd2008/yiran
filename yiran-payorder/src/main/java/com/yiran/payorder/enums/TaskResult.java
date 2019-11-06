package com.yiran.payorder.enums;

public class TaskResult {

    private boolean success;      //是否执行成功
    private String  resultMessage; //结果消息

    public TaskResult(boolean success, String message) {
        super();
        this.success = success;
        this.resultMessage = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getResultMessage() {
        return resultMessage;
    }

}
