package services

import models.{Balance, LeaseInfo}

import scala.concurrent.{ExecutionContext, Future}

trait TermBalanceService {

  def getBalance(currentMiles: Int): Future[Balance]

  def getLeaseInfo: Future[LeaseInfo]

}

class TermBalanceServiceImpl(
  calculatorService: CalculatorService,
  dateService: DateService
)(implicit
  ec: ExecutionContext
) extends TermBalanceService {

  override def getBalance(currentMiles: Int): Future[Balance] = Future {
    Balance(
      monthly = calculatorService.calculateMonthlyBalance(
        currentMiles = currentMiles,
        currentMonthNumber = dateService.getCurrentMonth
      ),
      total = calculatorService.calculateTotalBalance(
        currentMiles
      )
    )
  }

  override def getLeaseInfo: Future[LeaseInfo] = Future {
    val maybeCurrentYear = dateService.getCurrentYear
    val currentMonth = processCurrentMoth(dateService.getCurrentMonth, maybeCurrentYear)
    LeaseInfo(
      year = maybeCurrentYear,
      month = currentMonth,
      today = dateService.getToday
    )
  }

  private def processCurrentMoth(
    currentMonth: Int,
    maybeCurrentYear: Option[Int]
  ): Int =
    if (maybeCurrentYear.exists(_ > 0))
      currentMonth % 12
    else
      currentMonth

}
