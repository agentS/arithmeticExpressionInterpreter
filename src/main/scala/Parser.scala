package org.schoerghuber.lukas.arithmeticexpressioninterpreter;

import java.lang.IllegalArgumentException;

class Parser(val symbols: List[Symbol])
{
	private var actualSymbol: Symbol = null;
	private var symbolIndex: Int = 0;
	private var result: Int = 0;

	def processExpression(): Unit =
	{
		println(s"Result = ${startExpression()}");
	}

	private def startExpression(): Int =
	{
		symbolIndex = 0;
		actualSymbol = symbols(symbolIndex);
		return expression();
	}

	private def expression(): Int =
	{
		var result: Int = term();
		actualSymbol match
		{
			case PlusSymbol() =>
			{
				moveToNextSymbol();
				result = result + expression();
			}
			case MinusSymbol() => 
			{
				moveToNextSymbol();
				result = result - expression();
			}
			case _ => {}
		}
		return result;
	}

	private def term(): Int =
	{
		var result: Int = factor();
		actualSymbol match
		{
			case MultiplicationSymbol() =>
			{
				moveToNextSymbol();
				result *= term();
			}
			case DivisionSymbol() =>
			{
				moveToNextSymbol();
				result /= term();
			}
			case _ => {}
		}
		return result;
	}

	private def factor(): Int =
	{
		actualSymbol match
		{
			case number: NumberSymbol => return numberLiteral();
			case OpenParenthesisSymbol() =>
			{
				moveToNextSymbol();
				var result: Int = expression();
				actualSymbol match
				{
					case ClosingParenthesisSymbol() =>
					{
						moveToNextSymbol();
						return result;
					}
					case _ => throw new IllegalArgumentException(") expected.");
				}
			};
		}
	}

	private def numberLiteral(): Int =
	{
		actualSymbol match
		{
			case number: NumberSymbol =>
			{
				moveToNextSymbol();
				return number.number;
			}
			case _ => throw new IllegalArgumentException("A number was expected.");
		}
	}

	private def moveToNextSymbol()
	{
		symbolIndex += 1;
		actualSymbol = symbols(symbolIndex);
	}
}
