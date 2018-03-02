package ru.xamgore.tasks.Commands;

import ru.xamgore.tasks.Task;

public class Assign extends Task {

  private final String varName;
  private final String value;

  public Assign(String varName, String value) {
    this.varName = varName;
    this.value = value;
  }

  @Override
  public int exec() {
    env.put(varName, value);
    return 0;
  }

}
