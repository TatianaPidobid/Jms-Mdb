package com.mycompany.app;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import com.mycompany.app.interceptor.SimpleInterceptor;

@Stateless
@Interceptors(SimpleInterceptor.class) //wszystkie metody tej klasy zostaną przechwycone przez SimpleInterceptor
public class SimpleEJB {

	public String printMessage(String message) {

		System.out.println(" Executing method : printMessage" + message);
		return "Message is " + message;
	}
}
