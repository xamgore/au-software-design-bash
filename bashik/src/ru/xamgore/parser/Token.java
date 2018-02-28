package ru.xamgore.parser;


public class Token {
  private final TokenType type;
  private final String text;
  private final int col;

  Token(TokenType type, String text, int col) {
    this.type = type;
    this.text = text;
    this.col = col;
  }

  public TokenType getType() {
    return type;
  }

  public String getText() {
    return text;
  }

  public int getCol() {
    return col;
  }

  public String position() {
    return "[" + col + "]";
  }

  @Override
  public String toString() {
    final String dots = text.length() == 0 ? "" : " : ";
    return text + dots + type.name() + " " + position();
  }
}
