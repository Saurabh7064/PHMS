package com.team3.phms.Advice;
import lombok.Data;
@Data
public class Response<T> {
    private int status;
    private String message;
    private T data;
    private long timestamp ;

    public Response (){
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> Response<T> success(T data) {
        Response<T> Response = new Response<>();
        Response.setStatus(Code.RC100.getCode());
        Response.setMessage(Code.RC100.getMessage());
        Response.setData(data);
        return Response;
    }

    public static <T> Response<T> fail(int code, String message) {
        Response<T> Response = new Response<>();
        Response.setStatus(code);
        Response.setMessage(message);
        return Response;
    }
}