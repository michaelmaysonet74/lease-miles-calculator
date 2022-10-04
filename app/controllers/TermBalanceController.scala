package controllers

import models.{Balance, LeaseInfo, TermBalanceResponse}
import services.TermBalanceService
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents, Result}

import scala.concurrent.ExecutionContext

class TermBalanceController(
  termBalanceService: TermBalanceService,
  cc: ControllerComponents
)(implicit
  ec: ExecutionContext
) extends AbstractController(cc) {

  def getTermBalance(currentMiles: Int): Action[AnyContent] =
    Action.async {
      val eventualBalance = termBalanceService.getBalance(currentMiles)
      val eventualLeaseInfo = termBalanceService.getLeaseInfo
      for {
        balance <- eventualBalance
        leaseInfo <- eventualLeaseInfo
      } yield createTermBalanceResponse(balance, leaseInfo)
    }

  private def createTermBalanceResponse(
    balance: Balance,
    leaseInfo: LeaseInfo
  ): Result = Ok(
    Json.toJson(
      TermBalanceResponse(balance, leaseInfo)
    )
  )

}
