package ru.xamgore;

public class Main {
  public static void main(String[] args) {
    new Repl(System.getenv()).run();
//    Stream.of(
////      "\"echo\" example.txt | wc",
////      "echo \"Hello, world!\"",
////      "FILE=example.txt",
////      "cat $FILE",
////      "cat example.txt | wc",
//      "echo 123 | wc | kek | ac=12"
////      "x=exit",
////      "$x"
//    )
  }
}
