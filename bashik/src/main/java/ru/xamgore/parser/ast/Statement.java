package ru.xamgore.parser.ast;

import ru.xamgore.parser.Visitor;

public interface Statement {
  void execute();

  void accept(Visitor visitor);
}
