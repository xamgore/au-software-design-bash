package ru.xamgore.parser.ast;

import ru.xamgore.parser.lexer.Token;
import ru.xamgore.parser.Visitor;

import java.util.List;

public class Command implements Statement {

  public Token cmd;
  public List<Token> args;

  public Command(Token cmd, List<Token> args) {
    this.cmd = cmd;
    this.args = args;
  }

  @Override
  public String toString() {
    return cmd.getText() + " " + args.toString();
  }

    @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
}
