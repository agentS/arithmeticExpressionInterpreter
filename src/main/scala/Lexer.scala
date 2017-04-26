package org.schoerghuber.lukas.arithmeticexpressioninterpreter;

import java.lang.IllegalArgumentException;
import scala.collection.mutable.ListBuffer;

object Lexer
{
	val REGEX_DIGIT = "[0-9]".r();
}

class Lexer(var line: String)
{
	def getSymbols(): List[Symbol] =
	{
		var symbols: ListBuffer[Symbol] = ListBuffer[Symbol]();
		var previousSymbol: Symbol = new BlankSymbol();
		var processedNumberSymbol: NumberStringSymbol = new NumberStringSymbol("");
		for (character <- line)
		{
			val newSymbol: Symbol = getNextSymbol(character);
			newSymbol match
			{
				case BlankSymbol() => {}
				case digit:DigitSymbol =>
				{
					processedNumberSymbol.numberString += digit.digitString.toString;
				}
				case _ =>
				{
					previousSymbol match
					{
						case previousDigit: DigitSymbol =>
						{
							newSymbol match
							{
								case actualDigit: DigitSymbol => {}
								case _ =>
								{
									symbols += new NumberSymbol(processedNumberSymbol.numberString.toInt);
									processedNumberSymbol = new NumberStringSymbol("");
								}
							}
						}
						case _ => {}
					}

					symbols += newSymbol
				};
			}
			previousSymbol = newSymbol;
		}

		previousSymbol match
		{
			case actualDigit: DigitSymbol =>
			{
				symbols += new NumberSymbol(processedNumberSymbol.numberString.toInt);
				processedNumberSymbol = new NumberStringSymbol("");
			}
			case _ => {}
		}

		symbols += new EndOfLineSymbol();
		return symbols.toList;
	}

	private def getNextSymbol(character: Char): Symbol =
	{
		character match
		{
			case '+' => return new PlusSymbol();
			case '-' => return new MinusSymbol();
			case '*' => return new MultiplicationSymbol();
			case '/' => return new DivisionSymbol();
			case '(' => return new OpenParenthesisSymbol();
			case ')' => return new ClosingParenthesisSymbol();
			case Lexer.REGEX_DIGIT() =>
			{
				return new DigitSymbol(character);
			}
			case ' ' => return new BlankSymbol();
			case _ => throw new IllegalArgumentException("Invalid character " + character);
		}
	}
}