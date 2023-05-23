package com.BikkadIT.blog.helper;

public class SecurityConstant {

	
	public static final Integer JWT_TOKEN_VALIDITY= 5 * 60 * 60;
	
	public static final String MODE_THREADLOCAL="Mode Threadlocal";
	
	public static final String MODE_INHERITABLEADLOCAL="Mode Inheritableadlocal";
	
	public static final String MODE_GLOBAL="MODE_GLOBAL";		
	
	public static final String MODE_PRE_INTIALIZED="MODE_PRE_INTIALIZED";
	
	public static final String SYSTEM_PROPERTY="spring.security.strategy";
	
	public static final String HEADER_STRING="Authorization";
	
	public static final String TOKEN_PREFIX="Bearer";
	
	public static final int TOKEN_INDEX=7;
	
	private static final String SECRET_KEY = "secret";
	
	private static String strategyName=System.getProperty(SYSTEM_PROPERTY);
	
	private static  SecurityConstant strategy;
	
	private static int initializeCount = 0;
	
	public static final String[] PUBLIC_URLS = {"/api/v1/auth/**","/v3/api-docs","/v2/api-docs",
			"/swagger-resources/**", "/swagger-ui/***","/webjars/**"
			};
	
	
}
