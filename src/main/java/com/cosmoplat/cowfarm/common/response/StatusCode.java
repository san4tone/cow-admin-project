package com.cosmoplat.cowfarm.common.response;

/**
 *  状态
 */
public class StatusCode {

    public static final int OK=20000;//成功
    public static final int ERROR=20001;// 失败
    public static final int LOGINERROR = 20002;//用户名或密码错误
    public static final int ACCESSERROR = 20003;//权限不足

}
