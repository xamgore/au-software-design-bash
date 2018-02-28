package ru.xamgore.parser;

public class LexerException extends RuntimeException {
  public LexerException(int pos, String msg) {
    super("[" + pos + "] " + msg);
  }
}
