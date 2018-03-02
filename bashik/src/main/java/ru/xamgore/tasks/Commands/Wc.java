package ru.xamgore.tasks.Commands;

import ru.xamgore.tasks.Task;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Wc extends Task {

  @Override
  public int exec() {
    String in = i;
    try {
      if (args.length > 0)
        in = new String(Files.readAllBytes(Paths.get(args[0])));
    } catch (IOException e) {
      return 1;
    }

    long linesCount = in.split("\\n").length;
    int wordsCount = in.split("\\s+").length;
    int bytesCount = in.length();

    o = bytesCount + "\t" + wordsCount + "\t" + linesCount;
    return 0;
  }

}
