package com.curswork.numericalmethods.parser;

import java.util.List;

public interface ParserAPII {
	
  String getExpression() throws ArithmeticException;
  
  void setExpression(String paramString) throws ArithmeticException;
  
  List<String> getTokens() throws ArithmeticException;
  
  List<String> parseExpression() throws ArithmeticException;
  
  List<String> valuesSubstitute() throws ArithmeticException;

}
