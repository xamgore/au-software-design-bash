package ru.xamgore.parser.visitors;

import ru.xamgore.parser.Visitor;
import ru.xamgore.parser.ast.Assignment;
import ru.xamgore.parser.ast.Command;
import ru.xamgore.parser.ast.PipeSequence;
import ru.xamgore.parser.lexer.Token;
import ru.xamgore.parser.lexer.TokenType;

import java.util.ArrayList;
import java.util.List;

public class TreeFlattener implements Visitor {
  private final List<Token> res = new ArrayList<>();
  private int pos = -1;

  @Override
  public void visit(Assignment s) {
    int eqSignPos = s.var.getEndCol() + 1;

    add(s.var);
    add(new Token(TokenType.ASSIGN, "=", eqSignPos, eqSignPos));
    s.values.forEach(this::add);
  }

  @Override
  public void visit(Command s) {
    add(s.cmd);
    s.args.forEach(this::add);
  }

  @Override
  public void visit(PipeSequence s) {
    s.cmds.get(0).accept(this);

    s.cmds.stream()
      .skip(1)
      .forEach(st -> {
        // approximate pipe's position
        add(new Token(TokenType.PIPE, "|", pos, pos));
        st.accept(this);
      });
  }

  public List<Token> getResult() {
    return res;
  }

  private void add(Token t) {
    res.add(t);
    pos = t.getEndCol() + 1;
  }
}
