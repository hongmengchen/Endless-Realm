package cn.hmck.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 返回结果封装类
 *
 * @author 陈亮
 * @since 2024-12-6
*/

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Result<T> {
    private Integer status_code;
    private String msg;
    private T data;

    public void status_code(Integer status_code) {
        this.status_code = status_code;
    }

    //直接返回成功状态码
    public static <T> Result<T> success(){
        Result<T> result=new Result<>();
        result.setStatus_code(1);
        return result;
    }

    //返回成功状态码的同时返回对象
    public static <T> Result<T> success(T data){
        Result<T> result=new Result<>();
        result.setStatus_code(1);
        result.setData(data);
        return result;
    }

    //直接返回错误状态码和错误信息
    public static <T> Result<T> fail(ErrorMsg errorMsg){
        Result<T> result=new Result<>();
        result.setStatus_code(0);
        result.setMsg(errorMsg.getMsg());
        return result;
    }

    //返回错误状态码和错误信息的同时返回错误对象
    public static <T> Result<T> fail(ErrorMsg errorMsg,T data){
        Result<T> result=new Result<>();
        result.setStatus_code(0);
        result.setMsg(errorMsg.getMsg());
        result.setData(data);
        return result;
    }
}
