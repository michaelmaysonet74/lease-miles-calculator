package modules

import controllers.TermBalanceController
import services.{TermBalanceService, TermBalanceServiceImpl}
import com.softwaremill.macwire.wire
import play.api.mvc.ControllerComponents

import scala.concurrent.ExecutionContext

trait TermBalanceModule extends DateModule with CalculatorModule {

  implicit def ec: ExecutionContext

  def controllerComponents: ControllerComponents

  lazy val termBalanceService: TermBalanceService = wire[TermBalanceServiceImpl]
  lazy val termBalanceController: TermBalanceController = wire[TermBalanceController]

}
