package com.curswork.numericalmethods.methods;

import com.curswork.numericalmethods.parser.ExpressionPerformerI;

public class ChordMethod extends Method {
  public ChordMethod(ExpressionPerformerI performer) throws NullPointerException, ArithmeticException {
    super(performer);
  }
  
  public ChordMethod(ExpressionPerformerI performer, double left, double right) throws NullPointerException, ArithmeticException {
    super(performer, left, right);
  }
  
  public ChordMethod(ExpressionPerformerI performer, double left, double right, double eps) throws NullPointerException, ArithmeticException {
    super(performer, left, right, eps);
  }
  
  public RootI calculateRoot() throws ArithmeticException {
    double tempR = this.right;
    double tempL = this.left;
    if (calculate(tempL) * calculate(tempR) < 0.0D) {
      int iterationsCount = 0;
      while (Math.abs(tempR - tempL) > this.eps) {
        double t1 = calculate(tempR);
        double t2 = calculate(tempL);
        tempL = tempR - (tempR - tempL) * t1 / (calculate(tempR) - calculate(tempL));
        tempR = tempL - (tempL - tempR) * calculate(tempL) / (calculate(tempL) - calculate(tempR));
        iterationsCount++;
        if (iterationsCount >= 100)
          throw new ArithmeticException("Количество итераций для нахождения корня с заданной точностью превысило"
          		+ " 100. Повторите попытку с другими параметрами."); 
      } 
      return new Root(tempR, true, iterationsCount);
    } 
    return new Root();
  }
}
