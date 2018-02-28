package ru.xamgore.parser;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO description
 */
public final class Lexer {

  private static final String OPERATORS = "|";

  private final List<Token> tokens;
  private final String input;
  private final int len;

  private int pos = 0;


  public Lexer(String input) {
    this.input = input;
    len = input.length();

    tokens = new ArrayList<>();
  }

  public List<Token> tokenize() {
    while (pos < len) {
      final char ch = peek(0);

      if (OPERATORS.indexOf(ch) != -1) {
        tokenizeOperator();
      } else if (ch == '"' || ch == '\'') {
        tokenizeQuotedWord();
      } else if (!Character.isSpaceChar(ch)) {
        tokenizeWord();
      } else {
        next(); // whitespace
      }
    }

    return tokens;
  }

  private void tokenizeOperator() {
    char ch = peek(0);
    if (ch == '|') addToken(TokenType.PIPE);
    next();
  }

  private void tokenizeWord() {
    final StringBuilder buf = new StringBuilder();

    char ch = peek(0);
    while (" |\t\'\"\0".indexOf(ch) == -1) {
      buf.append(ch);
      ch = next();
    }

    addToken(TokenType.WORD, buf.toString());
  }

  private void tokenizeQuotedWord() {
    final StringBuilder buf = new StringBuilder();
    char quote = peek(0);
    char ch = next();

    while (true) {
      if (ch == '\0')
        throw error("Closing quote was not found");

      if (ch == quote)
        break;

      buf.append(ch);
      ch = next();
    }

    next(); // skip closing quote

    TokenType type = (quote == '"') ? TokenType.DQUOTED : TokenType.SQUOTED;
    addToken(type, buf.toString());
  }

  private char peek(int relativePosition) {
    final int position = pos + relativePosition;
    if (position >= len) return '\0';
    return input.charAt(position);
  }

  private char next() {
    pos++;
    return peek(0);
  }

  private void addToken(TokenType type) {
    addToken(type, "");
  }

  private void addToken(TokenType type, String text) {
    tokens.add(new Token(type, text, pos));
  }

  private LexerException error(String msg) {
    return new LexerException(pos, msg);
  }
}
