package ru.xamgore.parser.visitors;

import ru.xamgore.parser.Visitor;
import ru.xamgore.parser.ast.Assignment;
import ru.xamgore.parser.ast.Command;
import ru.xamgore.parser.ast.PipeSequence;
import ru.xamgore.parser.lexer.Token;

import java.util.function.Consumer;

public class StatementPrinter implements Visitor {
  StringBuilder res = new StringBuilder();

  public String getResult() {
    return res.toString();
  }


  @Override
  public void visit(Assignment s) {
    add(s.var);
    add("=");

    if (s.values.size() > 0) add(s.values.get(0));
    s.values.stream().skip(1).forEach(addWithPrefix(" "));
  }

  @Override
  public void visit(Command c) {
    add(c.cmd);
    c.args.forEach(addWithPrefix(" "));
  }

  @Override
  public void visit(PipeSequence seq) {
    seq.cmds.get(0).accept(this);

    seq.cmds.stream()
      .skip(1)
      .forEach(st -> {
        add(" | ");
        st.accept(this);
      });
  }


  private void add(String s) {
    res.append(s);
  }

  private void add(Token tok) {
    add(tok.getText());
  }

  private Consumer<Token> addWithPrefix(String prefix) {
    return tok -> {
      add(prefix);
      add(tok);
    };
  }
}
