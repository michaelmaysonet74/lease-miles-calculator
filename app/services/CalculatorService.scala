package services

import scala.math.round

trait CalculatorService {

  def calculateMonthlyBalance(
    currentMiles: Int,
    currentMonthNumber: Int
  ): Int

  def calculateTotalBalance(
    currentMiles: Int
  ): Int

}

class CalculatorServiceImpl extends CalculatorService {

  import CalculatorServiceImpl._

  override def calculateMonthlyBalance(
    currentMiles: Int,
    currentMonthNumber: Int
  ): Int =
    round(defaultMonthlyBalance * currentMonthNumber - currentMiles)

  override def calculateTotalBalance(currentMiles: Int): Int =
    maybeTermMilesBalance match {
      case Some(termMilesBalance) if currentMiles < termMilesBalance =>
        termMilesBalance.toInt - currentMiles
      case _ => 0
    }

}

object CalculatorServiceImpl {

  import scala.sys.env

  private val maybeTermMilesBalance =
    env.getOrElse("TERM_MILES_BALANCE", "0").toFloatOption

  private val maybeTermLengthMonths =
    env.getOrElse("TERM_LENGTH_MONTHS", "0").toFloatOption

  private val defaultMonthlyBalance =
    (for {
      termMilesBalance <- maybeTermMilesBalance
      termLengthMonths <- maybeTermLengthMonths
    } yield termMilesBalance / termLengthMonths).getOrElse(0.0f)

}
