package com.curswork.numericalmethods.parser;

public interface ExpressionPerformerI {
	
  ExpressionParserI getParser();
  
  void setParser(ExpressionParserI paramExpressionParserI);
  
  void compile() throws ArithmeticException;
  
  double calculate() throws ArithmeticException;
  
  double calculate(double paramDouble) throws ArithmeticException;

}