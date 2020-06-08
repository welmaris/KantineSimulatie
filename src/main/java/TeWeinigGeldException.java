package main.java;

import java.lang.Exception;

public class TeWeinigGeldException extends RuntimeException {

	TeWeinigGeldException(){
		super();
	}

	TeWeinigGeldException(Exception e){
		super(e);
	}

	TeWeinigGeldException(String message){
		super(message);
	}
}