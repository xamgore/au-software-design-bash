package ru.xamgore.parser;

public enum TokenType {
  EOF,
  WORD,    // word
  DQUOTED, // "word"
  SQUOTED, // 'word'
  PIPE,    // cmd1 | cmd2
}