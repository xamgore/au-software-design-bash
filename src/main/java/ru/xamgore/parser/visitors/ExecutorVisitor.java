package ru.xamgore.parser.visitors;

import ru.xamgore.tasks.Commands.*;
import ru.xamgore.tasks.ExternalTask;
import ru.xamgore.tasks.InternalCommands;
import ru.xamgore.tasks.Task;
import ru.xamgore.parser.ast.Assignment;
import ru.xamgore.parser.ast.Command;
import ru.xamgore.parser.lexer.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ExecutorVisitor extends AbstractVisitor {
  private List<Task> tasks = new ArrayList<>();

  private static Map<String, Supplier<Task>> commands =
    InternalCommands.get();


  @Override
  public void visit(Assignment s) {
    String value = s.values.stream()
      .map(Token::getText)
      .collect(Collectors.joining(" "));

    tasks.add(new Assign(s.var.getText(), value));
  }

  @Override
  public void visit(Command s) {
    String name = s.cmd.getText();

    Task t = (commands.containsKey(name))
      ? commands.get(name).get()
      : new ExternalTask(name);

    t.setArgs(s.args.stream().map(Token::getText).toArray(String[]::new));
    tasks.add(t);
  }

  public List<Task> getTasks() {
    return tasks;
  }
}
