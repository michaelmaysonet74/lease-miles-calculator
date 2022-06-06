package controllers

import models.TermBalanceResponse
import services.TermBalanceService
import com.rallyhealth.weepickle.v1.WeePickle.FromScala
import com.rallyhealth.weejson.v1.jackson.ToPrettyJson
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

import scala.concurrent.ExecutionContext

class TermBalanceController(
  termBalanceService: TermBalanceService,
  cc: ControllerComponents
)(implicit
  ec: ExecutionContext
) extends AbstractController(cc) {

  def getTermBalance(currentMiles: Int): Action[AnyContent] = Action.async {
    val eventualBalance = termBalanceService.getBalance(currentMiles)
    val eventualLeaseInfo = termBalanceService.getLeaseInfo

    for {
      balance <- eventualBalance
      leaseInfo <- eventualLeaseInfo
    } yield Ok(
      FromScala(
        TermBalanceResponse(
          balance,
          leaseInfo
        )
      ).transform(ToPrettyJson.string)
    ).as("application/json")
  }

}
