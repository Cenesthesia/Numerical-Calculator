package com.curswork.numericalmethods.methods;

import com.curswork.numericalcalculator.NumericalCalculator;
import com.curswork.numericalmethods.parser.ExpressionParserI;
import com.curswork.numericalmethods.parser.ExpressionPerformerI;
import com.curswork.numericalmethods.parser.ParserAPII;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public abstract class Method {
  protected double left;
  
  protected double right;
  
  protected ExpressionPerformerI performer;
  
  protected ParserAPII parser;
  
  protected double eps;
  
  public double getLeft() {
    return this.left;
  }
  
  public void setLeft(double left) throws ArithmeticException {
    if (left < -100 || left > 99 || left > this.right)
      throw new ArithmeticException("Недопустимая левая граница. Она должна быть не меньше -100 "
      		+ "и не больше 99. Также левая граница не может быть больше правой. Измените значение левой или"
      		+ " правой границы."); 
    this.left = left;
  }
  
  public double getRight() {
    return this.right;
  }
  
  public void setRight(double right) throws ArithmeticException {
	  if (right < -99 || right > 100 || right > this.left)
      throw new ArithmeticException("Недопустимая правая граница. Она должна быть не меньше -99 "
        		+ "и не больше 100. Также правая граница не может быть больше левой. Измените значение левой или"
          		+ " правой границы."); 
    this.right = right;
  }
  
  public ExpressionPerformerI getPerformer() {
    return this.performer;
  }
  
  public ParserAPII getParserAPI() {
    return this.parser;
  }
  
  public void setPerformer(ExpressionPerformerI performer) throws NullPointerException {
    if (performer == null)
      throw new NullPointerException("Performer cannot be null"); 
    this.performer = performer;
    this.parser = (ParserAPII)performer;
  }
  
  public double getEps() {
    return this.eps;
  }
  
  public void setEps(double eps) throws ArithmeticException {
    if (eps < 0.000000000000001 || eps > 0.1)
      throw new ArithmeticException("Все вычисления проводятся с точностью в диапазоне от "
      		+ "0.000000000000001 до 0.1. Измените значение точности."); 
    this.eps = eps;
  }
  
  public void setParam(double left, double right, double eps) throws ArithmeticException {
    if (left >= right)
      throw new ArithmeticException("Левая граница не может быть больше правой или равной. Измените значение левой или"
      		+ " правой границы."); 
    if (left < -100.0 || left > 99.0)
        throw new ArithmeticException("Недопустимая левая граница. Она должна быть не меньше -100 "
        		+ "и не больше 99. Измените значение левой или"
        		+ " правой границы."); 
    if (right < -99.0 || right > 100.0)
        throw new ArithmeticException("Недопустимая правая граница. Она должна быть не меньше -99 "
          		+ "и не больше 100. Измените значение левой или"
            		+ " правой границы."); 
    this.left = left;
    this.right = right;
    setEps(eps);
  }
  
  public Method(ExpressionPerformerI performer) throws NullPointerException, ArithmeticException {
    if (performer == null)
      throw new NullPointerException("Performer cannot be null"); 
    setPerformer(performer);
    setParam(-10.0, 10.0, 0.0000001);
  }
  
  public Method(ExpressionPerformerI performer, double left, double right) throws NullPointerException, ArithmeticException {
    if (performer == null)
      throw new NullPointerException("Performer cannot be null"); 
    setPerformer(performer);
    setParam(left, right, 0.0000001);
  }
  
  public Method(ExpressionPerformerI performer, double left, double right, double eps) throws NullPointerException, ArithmeticException {
    if (performer == null)
      throw new NullPointerException("Performer cannot be null"); 
    setPerformer(performer);
    setParam(left, right, eps);
  }
  
  public abstract RootI calculateRoot();
  
  public String getExpression() throws ArithmeticException {
    return this.parser.getExpression();
  }
  
  public void setExpression(String expression) throws ArithmeticException {
    this.parser.setExpression(expression);
  }
  
  public List<String> getTokens() throws ArithmeticException {
    return this.parser.getTokens();
  }
  
  public List<String> parseExpression() throws ArithmeticException {
    return this.parser.parseExpression();
  }
  
  public List<String> valuesSubstitute() throws ArithmeticException {
    return this.parser.valuesSubstitute();
  }
  
  public ExpressionParserI getParser() {
    return this.performer.getParser();
  }
  
  public void setParser(ExpressionParserI parser) {
    this.performer.setParser(parser);
  }
  
  public void compile() throws ArithmeticException {
    this.performer.compile();
  }
  
  public double calculate() throws ArithmeticException {
    return this.performer.calculate();
  }
  
  public double calculate(double x) throws ArithmeticException {
    return this.performer.calculate(x);
  }
}
