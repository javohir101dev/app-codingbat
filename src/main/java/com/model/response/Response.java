package com.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {

    private String massage;

    private boolean result;

    private T data;

    public Response(String massage) {
        this.massage = massage;
        this.data = null;
        this.result = false;
    }

    public Response(T data){
        this.data = data;
        this.result = true;
        this.massage = "";
    }

}
