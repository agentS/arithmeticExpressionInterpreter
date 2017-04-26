package org.schoerghuber.lukas.arithmeticexpressioninterpreter;

class Symbol()
{
}

case class PlusSymbol() extends Symbol
{
}

case class MinusSymbol() extends Symbol
{
}

case class MultiplicationSymbol() extends Symbol
{
}

case class DivisionSymbol() extends Symbol
{
}

case class OpenParenthesisSymbol() extends Symbol
{
}

case class ClosingParenthesisSymbol() extends Symbol
{
}

case class EndOfLineSymbol() extends Symbol
{
}

case class NumberSymbol(val number: Int) extends Symbol
{
}

case class NumberStringSymbol(var numberString: String) extends Symbol
{
}

case class DigitSymbol(val digitString: Char) extends Symbol
{
}

case class BlankSymbol() extends Symbol
{
}
