package org.example.commonconfig.endpoinds;

import org.example.commonconfig.ports.Port;

public class Path {
    public static final String API = "/api";
    public static final String STUDENT_ENDPOINT = API + "/student";
    public static final String STUDENT_API_URL = "http://localhost:"+ Port.STUDENT_PORT + STUDENT_ENDPOINT;
    public static final String TEACHER_ENDPOINT = API + "/teacher";
    public static final String TEACHER_API_URL = "http://localhost:"+ Port.TEACHER_PORT + TEACHER_ENDPOINT;
    public static final String CLASS_ENDPOINT = API + "/class";
    public static final String CLASS_API_URL = "http://localhost:"+ Port.CLASS_PORT + CLASS_ENDPOINT;
    public static final String FIELD_ENDPOINT = API + "/field";
    public static final String FIELD_API_URL = "http://localhost:"+ Port.FIELD_PORT + FIELD_ENDPOINT;
}
