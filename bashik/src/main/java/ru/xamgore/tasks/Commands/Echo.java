package ru.xamgore.tasks.Commands;

import ru.xamgore.tasks.Task;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Echo extends Task {

  @Override
  public int exec() {
    o = Stream.of(args).collect(Collectors.joining(" "));
    return 0;
  }
}
