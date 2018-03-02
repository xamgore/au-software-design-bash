package ru.xamgore;

import ru.xamgore.tasks.Task;
import ru.xamgore.parser.Parser;
import ru.xamgore.parser.ast.Statement;
import ru.xamgore.parser.lexer.Lexer;
import ru.xamgore.parser.visitors.ExecutorVisitor;
import ru.xamgore.parser.visitors.StringsConcater;
import ru.xamgore.parser.visitors.VariableInterpolator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Repl {

  private final Map<String, String> env;

  public Repl(Map<String, String> env) {
    this.env = new HashMap<>(env);
  }

  public void process(String line) {
    Lexer lex = new Lexer(line);
    Parser parser = new Parser(lex.tokenize());

    Statement stm = parser.parse();
    stm.accept(new VariableInterpolator(env));
    stm.accept(new StringsConcater());

    ExecutorVisitor exec = new ExecutorVisitor();
    stm.accept(exec);

    String stdin = "";
    for (Task t : exec.getTasks()) {
      t.attach(stdin, env);
      t.exec();
      stdin = t.getOutput();
    }

    System.out.println(stdin);
  }

  public void run() {
    Scanner input = new Scanner(System.in);

    while (true) {
      System.out.print("> ");
      if (input.hasNextLine())
        process(input.nextLine());
    }
  }

}
