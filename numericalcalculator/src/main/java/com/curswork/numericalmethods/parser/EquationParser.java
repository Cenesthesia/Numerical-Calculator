package com.curswork.numericalmethods.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EquationParser implements ExpressionParserI, DefineTokenI {
  private String expression;
  
  private List<String> tokens;
  
  public EquationParser() {
    this.expression = "";
    this.tokens = null;
  }
  
  public EquationParser(String expression) {
    this.expression = expression;
    this.tokens = null;
  }
  
  public String getExpression() {
    return this.expression;
  }
  
  public void setExpression(String expression) {
    this.expression = expression;
    this.tokens = null;
  }
  
  public List<String> getTokens() {
    return this.tokens;
  }
  
  public List<String> parseExpression() throws ArithmeticException {
    if (this.expression == null || this.expression.isEmpty())
      throw new ArithmeticException("Unable to compute tokens. The expression cannot be empty. Get the new value of an expression."); 
    spaceRemove(this.expression.toLowerCase());
    numberDetecting();
    negativeValueFinder();
    return this.tokens;
  }
  
  private void spaceRemove(String expr) {
    expr = expr.replaceAll(" ", "");
    char[] lineAsChar = expr.toCharArray();
    this.tokens = new ArrayList<>();
    for (char ch : lineAsChar)
      this.tokens.add(String.valueOf(ch)); 
  }
  
  private void numberDetecting() {
    List<String> resultList = new ArrayList<>();
    for (int i = 0; i < this.tokens.size(); i++) {
      StringBuilder operatorOperand = new StringBuilder();
      if (isNumber(this.tokens.get(i))) {
        while (isNumber(this.tokens.get(i))) {
          operatorOperand.append(this.tokens.get(i));
          i++;
          if (i == this.tokens.size())
            break; 
        } 
        resultList.add(operatorOperand.toString());
      } 
      if (i < this.tokens.size() && (
        isOperator(this.tokens.get(i)) || isParenthesis(this.tokens.get(i))))
        resultList.add(this.tokens.get(i)); 
    } 
    this.tokens = resultList;
  }
  
  public boolean isNumber(String element) {
    char[] symbolAsChar = element.toCharArray();
    return (Character.isDigit(symbolAsChar[0]) || symbolAsChar[0] == '.' || 
      
      Character.isLetter(symbolAsChar[0]));
  }
  
  public boolean isOperator(String element) {
    return "+-*/^".contains(element);
  }
  
  public boolean isParenthesis(String element) {
    return "()".contains(element);
  }
  
  private void negativeValueFinder() {
    List<String> resultList = new ArrayList<>();
    boolean operator = true;
    for (int i = 0; i < this.tokens.size(); i++) {
      String nowToken = this.tokens.get(i);
      StringBuilder sb = new StringBuilder();
      if (operator && isOperator(nowToken)) {
        sb.append(nowToken).append(this.tokens.get(++i));
        operator = false;
      } else if (!operator && isOperator(nowToken)) {
        sb.append(nowToken);
        operator = true;
      } else if (isNumber(nowToken) || nowToken.equals(")")) {
        sb.append(nowToken);
        operator = false;
      } else if (nowToken.equals("(")) {
        sb.append(nowToken);
        operator = true;
      } 
      resultList.add(sb.toString());
    } 
    this.tokens = resultList;
  }
  
  public List<String> valuesSubstitute(Map<String, String> varValues) throws ArithmeticException {
    List<String> resultList = new ArrayList<>();
    if (this.tokens == null || this.tokens.isEmpty())
      throw new ArithmeticException("The list of tokens for the new equation has not been calculated. Tokens are null."); 
    for (String str : this.tokens)
      resultList.add(varValues.getOrDefault(str, str)); 
    return resultList;
  }
}
