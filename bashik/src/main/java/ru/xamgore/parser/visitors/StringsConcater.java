package ru.xamgore.parser.visitors;

import ru.xamgore.parser.ast.Assignment;
import ru.xamgore.parser.ast.Command;
import ru.xamgore.parser.lexer.Token;

import java.util.List;

import static ru.xamgore.parser.lexer.Concater.concat;


public class StringsConcater extends AbstractVisitor {

  @Override
  public void visit(Assignment s) {
    s.values = concat(s.values);
  }

  @Override
  public void visit(Command s) {
    s.args.add(0, s.cmd);
    List<Token> toks = concat(s.args);
    s.cmd = toks.get(0);
    toks.remove(0);
    s.args = toks;
  }

}
