package com.curswork.numericalmethods.methods;

class Root implements RootI {
  private double value;
  
  private boolean present;
  
  private int iterationsCount;
  
  public double getValue() {
    return this.value;
  }
  
  public void setValue(double value) {
    this.value = value;
  }
  
  public boolean isPresent() {
    return this.present;
  }
  
  public void setPresent(boolean present) {
    this.present = present;
  }
  
  public int getIterationsCount() {
    return this.iterationsCount;
  }
  
  public void setIterationsCount(int iterationsCount) {
    this.iterationsCount = iterationsCount;
  }
  
  public Root() {}
  
  public Root(double value, boolean present) {
    this.value = value;
    this.present = present;
  }
  
  public Root(double value, boolean present, int iterationsCount) {
    this.value = value;
    this.present = present;
    this.iterationsCount = iterationsCount;
  }
}
