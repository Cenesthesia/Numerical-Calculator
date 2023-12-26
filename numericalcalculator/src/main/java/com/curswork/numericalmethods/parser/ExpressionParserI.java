package com.curswork.numericalmethods.parser;

import java.util.List;
import java.util.Map;

public interface ExpressionParserI {

	 public String getExpression();

	 public void setExpression(String expression);
	 
	 public  List<String> getTokens();
	 
	 public List<String> parseExpression() throws ArithmeticException;
	 
	 public List<String> valuesSubstitute(Map<String, String> values) 
			 								throws ArithmeticException;
	 
}
