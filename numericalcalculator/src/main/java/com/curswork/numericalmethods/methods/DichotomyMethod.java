package com.curswork.numericalmethods.methods;

import com.curswork.numericalmethods.parser.ExpressionPerformerI;

public class DichotomyMethod extends Method {
  public DichotomyMethod(ExpressionPerformerI performer) throws NullPointerException, ArithmeticException {
    super(performer);
  }
  
  public DichotomyMethod(ExpressionPerformerI performer, double left, double right) throws NullPointerException, ArithmeticException {
    super(performer, left, right);
  }
  
  public DichotomyMethod(ExpressionPerformerI performer, double left, double right, double eps) throws NullPointerException, ArithmeticException {
    super(performer, left, right, eps);
  }
  
  public RootI calculateRoot() {
    double tempR = this.right;
    double tempL = this.left;
    if (Math.abs(calculate(tempL)) < this.eps)
      return new Root(tempL, true); 
    if (Math.abs(calculate(tempR)) < this.eps)
      return new Root(tempR, true); 
    if (calculate(tempL) * calculate(tempR) < 0.0D) {
      double root = (tempL + tempR) / 2.0D;
      int iterationsCount = 1;
      while (Math.abs(calculate(root)) > this.eps) {
        if (calculate(tempL) * calculate(root) < 0.0D) {
          tempR = root;
        } else if (calculate(root) * calculate(tempR) < 0.0D) {
          tempL = root;
        } 
        root = (tempL + tempR) / 2.0D;
        iterationsCount++;
        if (iterationsCount >= 100)
            throw new ArithmeticException("Количество итераций для нахождения корня с заданной точностью превысило"
            		+ " 100. Повторите попытку с другими параметрами."); 
      } 
      return new Root(root, true, iterationsCount);
    } 
    return new Root();
  }
}
