package services

import models.{Balance, LeaseInfo}

import scala.concurrent.{ExecutionContext, Future}

trait TermBalanceService {

  def getBalance(currentMiles: Int): Future[Balance]

  def getLeaseInfo(): Future[LeaseInfo]

}

class TermBalanceServiceImpl(
  calculatorService: CalculatorService,
  dateService: DateService
)(implicit
  ec: ExecutionContext
) extends TermBalanceService {

  override def getBalance(currentMiles: Int): Future[Balance] =
    Future.successful(
      Balance(
        monthly = calculatorService.calculateMonthlyBalance(
          currentMiles = currentMiles,
          currentMonthNumber = dateService.getCurrentMonth()
        ),
        total = calculatorService.calculateTotalBalance(
          currentMiles
        )
      )
    )

  override def getLeaseInfo(): Future[LeaseInfo] =
    Future.successful(
      LeaseInfo(
        month = dateService.getCurrentMonth(),
        today = dateService.getToday()
      )
    )

}
