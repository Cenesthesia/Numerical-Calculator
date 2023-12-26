package com.curswork.numericalmethods.parser;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class EquationPerformer implements ExpressionPerformerI, ParserAPII {
  private Stack<String> postfixExpression;
  
  private Stack<String> operators;
  
  private Map<String, String> VALUES;
  
  private final Map<String, Integer> OPERATOR_PRIORITIES;
  
  private ExpressionParserI parser;
  
  private DefineTokenI definer;
  
  private void operatorMapFiller() {
    valMapFiller();
    this.OPERATOR_PRIORITIES.put("sin", Integer.valueOf(1));
    this.OPERATOR_PRIORITIES.put("cos", Integer.valueOf(1));
    this.OPERATOR_PRIORITIES.put("tan", Integer.valueOf(1));
    this.OPERATOR_PRIORITIES.put("atan", Integer.valueOf(1));
    this.OPERATOR_PRIORITIES.put("log10", Integer.valueOf(1));
    this.OPERATOR_PRIORITIES.put("log2", Integer.valueOf(1));
    this.OPERATOR_PRIORITIES.put("ln", Integer.valueOf(1));
    this.OPERATOR_PRIORITIES.put("sqrt", Integer.valueOf(1));
    this.OPERATOR_PRIORITIES.put("rad", Integer.valueOf(1));
    this.OPERATOR_PRIORITIES.put("exp", Integer.valueOf(1));
    this.OPERATOR_PRIORITIES.put("^", Integer.valueOf(2));
    this.OPERATOR_PRIORITIES.put("*", Integer.valueOf(3));
    this.OPERATOR_PRIORITIES.put("/", Integer.valueOf(3));
    this.OPERATOR_PRIORITIES.put("+", Integer.valueOf(4));
    this.OPERATOR_PRIORITIES.put("-", Integer.valueOf(4));
    this.VALUES.put("x", "0.0");
    this.VALUES.put("pi", "3.141592653589");
    this.VALUES.put("e", "2.718281828459");
  }
  
  private void valMapFiller() {
    this.VALUES.put("x", "1.0");
    this.VALUES.put("-x", "-1.0");
    this.VALUES.put("pi", "3.141592653589");
    this.VALUES.put("e", "2.718281828459");
  }
  
  public EquationPerformer() {
    this.OPERATOR_PRIORITIES = new HashMap<>();
    this.VALUES = new HashMap<>();
    operatorMapFiller();
    valMapFiller();
    this.parser = null;
    this.definer = null;
    this.postfixExpression = null;
  }
  
  public EquationPerformer(ExpressionParserI parser) {
    this.OPERATOR_PRIORITIES = new HashMap<>();
    this.VALUES = new HashMap<>();
    operatorMapFiller();
    valMapFiller();
    this.parser = parser;
    this.definer = (DefineTokenI)parser;
    this.postfixExpression = null;
  }
  
  public EquationPerformer(ExpressionParserI parser, String expr) {
    this.OPERATOR_PRIORITIES = new HashMap<>();
    this.VALUES = new HashMap<>();
    operatorMapFiller();
    valMapFiller();
    this.parser = parser;
    this.definer = (DefineTokenI)parser;
    this.parser.setExpression(expr);
    this.postfixExpression = null;
  }
  
  public void setParser(ExpressionParserI parser) {
    this.parser = parser;
    this.postfixExpression = null;
  }
  
  public ExpressionParserI getParser() {
    return this.parser;
  }
  
  public String getExpression() throws ArithmeticException {
    if (this.parser == null)
      throw new ArithmeticException("The operation cannot be performed. The parser was not installed."); 
    return this.parser.getExpression();
  }
  
  public void setExpression(String expression) throws ArithmeticException {
    if (this.parser == null)
      throw new ArithmeticException("The operation cannot be performed. The parser was not installed."); 
    this.parser.setExpression(expression);
    this.postfixExpression = null;
  }
  
  public List<String> getTokens() throws ArithmeticException {
    if (this.parser == null)
      throw new ArithmeticException("The operation cannot be performed. The parser was not installed."); 
    return this.parser.getTokens();
  }
  
  public List<String> parseExpression() throws ArithmeticException {
    if (this.parser == null)
      throw new ArithmeticException("The operation cannot be performed. The parser was not installed."); 
    try {
      return this.parser.parseExpression();
    } catch (ArithmeticException e) {
      throw new ArithmeticException("Unable to compute tokens. The expression cannot be empty. Get the new value of an expression.");
    } 
  }
  
  public List<String> valuesSubstitute() throws ArithmeticException {
    if (this.parser == null)
      throw new ArithmeticException("The operation cannot be performed. The parser was not installed."); 
    try {
      return this.parser.valuesSubstitute(this.VALUES);
    } catch (ArithmeticException e) {
      throw new ArithmeticException("The list of tokens for the new equation has not been calculated. Tokens are null.");
    } 
  }
  
  public void compile() throws ArithmeticException {
    try {
      this.parser.parseExpression();
      this.operators = new Stack<>();
      this.postfixExpression = new Stack<>();
      transferToPolish();
    } catch (ArithmeticException e) {
      throw new ArithmeticException("The compilation operation is not available. An error was made when constructing the expression. Refer to the documentation and reconfigure the parser.");
    } 
  }
  
  private void transferToPolish() {
    List<String> expression = this.parser.getTokens();
    for (String str : expression) {
        if (operators.isEmpty() && (definer.isOperator(str) || isFunction(str))) {
            operators.push(str);
        } else if (!operators.isEmpty() && (definer.isOperator(str) || isFunction(str)) && !operators.peek().equals("(")) {
            if (OPERATOR_PRIORITIES.get(operators.peek()) <= OPERATOR_PRIORITIES.get(str)) {
                while (OPERATOR_PRIORITIES.get(operators.peek()) <= OPERATOR_PRIORITIES.get(str)) {
                    postfixExpression.push(operators.pop());
                    if (operators.isEmpty() || operators.peek().equals("(")) {
                        operators.push(str);
                        break;
                    }
                }
            } else {
                operators.push(str);
            }
        } else if (!operators.isEmpty() && (definer.isOperator(str) || isFunction(str)) && operators.peek().equals("(")) {
            operators.push(str);
        }else if (!operators.isEmpty() && str.equals("(")) {
                operators.push(str);
        } else if (str.equals(")")) {
            while(!operators.peek().equals("(")) {
            	postfixExpression.push(operators.pop());
            }
            operators.pop();
        } else {
        	postfixExpression.push(str);
        }
    }
    while (!this.operators.isEmpty())
      this.postfixExpression.push(this.operators.pop()); 
  }
  
  private boolean isFunction(String token) {
    return Arrays.<String>asList(new String[] { "sin", "cos", "tan", "atan", "log10", "log2", "ln", "sqrt", "rad", "exp" }).contains(token);
  }
  
  private double clc(Stack<String> post) {
    List<String> expressionReversed = post;
    Stack<String> expressionStack = new Stack<>();
    Stack<String> operationStack = new Stack<>();
    Collections.reverse(expressionReversed);
    for (String token : expressionReversed)
      expressionStack.push(token); 
    while (!expressionStack.isEmpty()) {
      String currToken = expressionStack.peek();
      if (isNumber(currToken)) {
        operationStack.push(expressionStack.pop());
        continue;
      } 
      if (this.definer.isOperator(currToken)) {
        String secondOperand = operationStack.pop();
        String firstOperand = operationStack.pop();
        String operator = expressionStack.pop();
        String result = mathOperation(firstOperand, operator, secondOperand);
        operationStack.push(result);
        continue;
      } 
      if (isFunction(currToken)) {
        String operand = operationStack.pop();
        String function = expressionStack.pop();
        String result = functionOperation(function, operand);
        operationStack.push(result);
      } 
    } 
    Collections.reverse(expressionReversed);
    return Double.parseDouble(operationStack.peek());
  }
  
  public double calculate(double x) throws ArithmeticException {
    if (this.postfixExpression == null)
      throw new ArithmeticException("The expression was not compiled. The value cannot be calculated."); 
    try {
      this.VALUES.put("x", Double.toString(x));
      this.VALUES.put("-x", Double.toString(-x));
      return clc(valuesSubstitutePostfix());
    } catch (ArithmeticException e) {
      throw new ArithmeticException(e.getMessage());
    } 
  }
  
  public double calculate() throws ArithmeticException {
    if (this.postfixExpression == null)
      throw new ArithmeticException("The expression was not compiled. The value cannot be calculated."); 
    try {
      this.VALUES.put("x", Double.toString(0.0D));
      this.VALUES.put("-x", Double.toString(-0.0D));
      return clc(valuesSubstitutePostfix());
    } catch (ArithmeticException e) {
      throw new ArithmeticException(e.getMessage());
    } 
  }
  
  private Stack<String> valuesSubstitutePostfix() throws ArithmeticException {
    Stack<String> resultList = new Stack<>();
    if (this.postfixExpression == null || this.postfixExpression.isEmpty())
      throw new ArithmeticException("The list of tokens for the new equation has not been calculated. Tokens are null."); 
    for (String str : this.postfixExpression)
      resultList.add(this.VALUES.getOrDefault(str, str)); 
    return resultList;
  }
  
  private boolean isNumber(String symbol) {
    return symbol.matches("-?\\d+(\\.\\d+)?");
  }
  
  private String mathOperation(String firstOperand, String operator, String secondOperand) throws ArithmeticException {
    double result = 0.0D;
    switch (operator) {
      case "+":
        result = Double.parseDouble(firstOperand) + Double.parseDouble(secondOperand);
        break;
      case "-":
        result = Double.parseDouble(firstOperand) - Double.parseDouble(secondOperand);
        break;
      case "/":
        if (Double.parseDouble(secondOperand) != 0.0D) {
          result = Double.parseDouble(firstOperand) / Double.parseDouble(secondOperand);
          break;
        } 
        throw new ArithmeticException("Division by zero error.");
      case "*":
        result = Double.parseDouble(firstOperand) * Double.parseDouble(secondOperand);
        break;
      case "^":
        result = Math.pow(Double.parseDouble(firstOperand), Double.parseDouble(secondOperand));
        break;
    } 
    return String.valueOf(result);
  }
  
  private String functionOperation(String function, String operand) throws ArithmeticException {
    switch (function) {
      case "sin":
        return String.valueOf(Math.sin(Double.parseDouble(operand)));
      case "cos":
        return String.valueOf(Math.cos(Double.parseDouble(operand)));
      case "tan":
        return String.valueOf(Math.tan(Double.parseDouble(operand)));
      case "atan":
        return String.valueOf(Math.atan(Double.parseDouble(operand)));
      case "log10":
        return String.valueOf(Math.log10(Double.parseDouble(operand)));
      case "log2":
        return String.valueOf(Math.log(Double.parseDouble(operand)) / Math.log(2.0D));
      case "sqrt":
        return String.valueOf(Math.sqrt(Double.parseDouble(operand)));
      case "rad":
        return String.valueOf(Math.toRadians(Double.parseDouble(operand)));
      case "exp":
        return String.valueOf(Math.exp(Double.parseDouble(operand)));
      case "ln":
        return String.valueOf(Math.log(Double.parseDouble(operand)));
    } 
    throw new ArithmeticException("A non-existent function was called.");
  }
}
