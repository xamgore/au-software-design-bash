package ru.xamgore.tasks;

import org.buildobjects.process.ExternalProcessFailureException;
import org.buildobjects.process.ProcBuilder;
import org.buildobjects.process.ProcResult;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ExternalTask extends Task {

  private final String file;

  public ExternalTask(String file) {
    this.file = file;
  }

  @Override
  public int exec() {
    try {
      InputStream is = new ByteArrayInputStream(i.getBytes());

      ProcResult res = new ProcBuilder(file)
        .withArgs(args)
        .withInputStream(is)
        .ignoreExitStatus()
        .run();

      o = res.getOutputString();
      return res.getExitValue();
    } catch (ExternalProcessFailureException ex) {
      System.out.println(ex.getMessage());
      return ex.getExitValue();
    }
  }

}
