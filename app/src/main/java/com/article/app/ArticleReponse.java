package com.article.app;

public class ArticleReponse <T>{
    private String code;
    private String message;
    private T data;


    public ArticleReponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // Getters
    public String getCode() { return code; }
    public String getMessage() { return message; }
    public T getData() { return data; }

}
