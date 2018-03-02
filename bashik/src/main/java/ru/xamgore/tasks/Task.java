package ru.xamgore.tasks;

import java.util.Map;

public abstract class Task {

  protected String i = "";
  protected String o = "";
  protected Map<String, String> env;
  protected String[] args = new String[0];

  public String getOutput() {
    return o;
  }

  public void attach(String i, Map<String, String> env) {
    this.i = i;
    this.env = env;
  }

  public void setArgs(String[] args) {
    this.args = args;
  }

  public abstract int exec();

}
