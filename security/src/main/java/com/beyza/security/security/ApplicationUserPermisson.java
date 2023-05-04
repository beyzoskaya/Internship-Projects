package com.beyza.security.security;

public enum ApplicationUserPermisson {
    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write"),
    COURSE_READ("course:read"),
    COURSE_WRITE("course:write");


    private final String permission;
   ApplicationUserPermisson(String permission) {

        this.permission = permission;
    }
    public String getPermission(){
       return permission;
    }



}
