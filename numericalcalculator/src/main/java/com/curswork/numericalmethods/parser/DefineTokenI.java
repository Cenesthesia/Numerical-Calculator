package com.curswork.numericalmethods.parser;

public interface DefineTokenI {
	
  boolean isNumber(String paramString);
  
  boolean isOperator(String paramString);
  
  boolean isParenthesis(String paramString);
  
}
