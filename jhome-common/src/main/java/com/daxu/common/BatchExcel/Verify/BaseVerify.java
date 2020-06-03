package com.daxu.common.BatchExcel.Verify;

import java.util.HashMap;

public class BaseVerify {
 private HashMap<String, String>BuiltIn;

public BaseVerify() {
	super();
	BuiltIn=new HashMap<String, String>();
	// TODO Auto-generated constructor stub
}
 

public HashMap<String, String> getBuiltIn() {
	return BuiltIn;
}

public void setBuiltIn(HashMap<String, String> builtIn) {
	BuiltIn = builtIn;
}
 
}
