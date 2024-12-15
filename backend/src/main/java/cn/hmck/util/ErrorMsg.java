package cn.hmck.util;

import lombok.Getter;

/**
 * 错误信息枚举类
 *
 * @author 陈亮
 * @since 2024-12-6
*/

@Getter
public enum ErrorMsg {
    ACCOUNT_EXIT("用户已存在"),
    ACCOUNT_LEN("手机号长度不符合要求"),
    ACCOUNT_Ban("账号已被封禁"),
    ACCOUNT_NOT_EXIT("用户不存在"),
    PASSWORD_IS_NOT_SAME("密码不一致"),
    PASSWORD_RESET_ERROR("修改密码失败"),
    EMAIL_SEND_ERROR("邮件发送失败 请重试"),
    PARAM_ERROR("参数错误"),
    SYSTEM_ERROR("系统错误"),
    REGISTER_ERROR("注册失败"),
    FILE_TYPE_ERROR("文件类型错误 请选择.jpg或.png"),
    FILE_NOT_EXIT("文件不存在"),
    FILE_DOWNLOAD_ERROR("文件下载异常"),
    FILE_SIZE_ERROR("文件过大"),
    OPERAT_FREQUENCY("操作频繁 稍后重试"),
    MISSING_PARAMETER("缺少参数"),
    COOKIE_ERROR("请重新登录"),
    EMAIL_LOGIN_ERROR("登录失败 账号或密码错误"),
    JSON_READ_ERROR("json参数解析错误"),
    FORM_NUMBER_ERROR("表单id错误"),
    REPEAT_COMMIT_ERROR("请勿重复提交"),
    COMMIT_FAIL_ERROR("提交失败"),
    FAVORITE_EXIT("收藏已存在"),
    NOT_FOUND("未找到"),
    PUBLISH_POST_ERROR("动态发布失败"),
    GET_USER_LIST_ERROR("获取用户列表失败"),
    UPDATE_ERROR("更新失败"),
    DELETE_ERROR("删除失败"),
    FILE_EMPTY_ERROR("文件上传失败，文件不能为空"),
    FILE_UPLOAD_ERROR("文件上传失败");

    private final String msg;

    ErrorMsg(String msg) {
        this.msg = msg;
    }
}
