package ru.xamgore.tasks.Commands;

import ru.xamgore.tasks.Task;

public class Pwd extends Task {

  @Override
  public int exec() {
    o = env.get("PWD");
    return 0;
  }

}
