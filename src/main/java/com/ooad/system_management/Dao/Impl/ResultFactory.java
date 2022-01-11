package com.ooad.system_management.Dao.Impl;

import com.ooad.system_management.Pojo.Result;
import com.ooad.system_management.Pojo.ResultCode;
import org.springframework.stereotype.Repository;

@Repository
public class ResultFactory {
    public static Result buildSuccessResult(Object data) {
        return buidResult(ResultCode.SUCCESS, "成功", data);
    }

    public static Result buildFailResult(String message) {
        return buidResult(ResultCode.FAIL, message, null);
    }

    public static Result buidResult(ResultCode resultCode, String message, Object data) {
        return buidResult(resultCode.code, message, data);
    }

    public static Result buidResult(int resultCode, String message, Object data) {
        return new Result(resultCode, message, data);
    }
}
