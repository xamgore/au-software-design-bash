package ru.xamgore.tasks.Commands;

import ru.xamgore.tasks.Task;


/**
 * Exit shutdowns the CLI process.
 */
public class Exit extends Task {

  @Override
  public int exec() {
    System.exit(0);
    return 0;
  }

}
