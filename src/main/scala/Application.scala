package org.schoerghuber.lukas.arithmeticexpressioninterpreter;

object Application
{
	def main(args: Array[String]): Unit =
	{
		var line = "   (25+4)*  6";
		val scanner: Lexer = new Lexer(line);

		val symbols: List[Symbol] = scanner.getSymbols();
		println(symbols.length);
		//for ( i <- symbols ) { println(i); }

		val parser: Parser = new Parser(symbols);
		parser.processExpression();
	}
}
