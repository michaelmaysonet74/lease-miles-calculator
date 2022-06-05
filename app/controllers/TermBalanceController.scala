package controllers

import models.{Balance, TermBalanceResponse}
import services.TermBalanceService
import com.rallyhealth.weepickle.v1.WeePickle.FromScala
import play.api.mvc.{AbstractController, Action}
import play.api.mvc.ControllerComponents

import scala.concurrent.{ExecutionContext, Future}
import com.rallyhealth.weejson.v1.jackson.ToPrettyJson

class TermBalanceController(
  termBalanceService: TermBalanceService,
  cc: ControllerComponents
)(implicit
  ec: ExecutionContext
) extends AbstractController(cc) {

  def getTermBalance(currentMiles: Int) = Action.async {
    val eventualBalance = termBalanceService.getBalance(currentMiles)
    val eventualLeaseInfo = termBalanceService.getLeaseInfo()

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
