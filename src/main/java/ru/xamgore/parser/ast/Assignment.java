package ru.xamgore.parser.ast;

import ru.xamgore.parser.lexer.Token;
import ru.xamgore.parser.Visitor;

import java.util.List;

public class Assignment implements Statement {
  public Token var;
  public List<Token> values;

  public Assignment(Token var, List<Token> values) {
    this.var = var;
    this.values = values;
  }

  @Override
  public String toString() {
    return "(" + var + ", ASSIGN, " + values + ")";
  }

    @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
}
