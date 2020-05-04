package com.foryou.exception;

public class UserNotExistException extends RuntimeException {
    private static final long serialVersionUID = -5988087255141067722L;
    private String id;
    public UserNotExistException(String id ){
        super("user not exist");
        this.id = id;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
