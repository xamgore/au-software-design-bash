package ru.xamgore;

import ru.xamgore.parser.Lexer;

public class Main {
  public static void main(String[] args) {
    String s = "\"echo\" example.txt | wc";

    Lexer l = new Lexer(s);
    System.out.println(l.tokenize());
  }
}
