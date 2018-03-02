package ru.xamgore;

import ru.xamgore.parser.Parser;
import ru.xamgore.parser.ast.Statement;
import ru.xamgore.parser.lexer.Concater;
import ru.xamgore.parser.lexer.Lexer;
import ru.xamgore.parser.lexer.Token;
import ru.xamgore.parser.visitors.StatementPrinter;
import ru.xamgore.parser.visitors.StringsConcater;
import ru.xamgore.parser.visitors.TreeFlattener;
import ru.xamgore.parser.visitors.VariableInterpolator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
  public static void main(String[] args) {
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
//      .map(s -> new Lexer(s).tokenize())
//      .map(toks -> new Parser(toks).parse())
//      .forEach(e -> {
//        AbstractVisitor v = new AbstractVisitor();
//        e.accept(v);
//      });

    Map<String, String> m = new HashMap<>();
    m.put("wor", "wor");
    m.put("k", "kkkk");
    m.put("hello", "hello ");
    m.put("{wor}ld", "HWWWWW");
    m.put("cho", "cho");

    Statement t = new Parser(new Lexer("e$cho 'x'abc'x' | echo 'hi '$hello${wor}ld | x=$k! | $lol $kek").tokenize()).parse();
    System.out.println(t);

    StatementPrinter p = new StatementPrinter();
    t.accept(p);
    System.out.println(p.getResult());

    VariableInterpolator i = new VariableInterpolator(m);
    t.accept(i);

    t.accept(new StringsConcater());
    System.out.println(t);

    p = new StatementPrinter();
    t.accept(p);
    System.out.println(p.getResult());

    System.out.println(System.getenv());
  }
}
