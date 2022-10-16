package loader

import modules.TermBalanceModule
import com.softwaremill.macwire.wire
import play.api.{Application, ApplicationLoader, BuiltInComponentsFromContext}
import play.api.ApplicationLoader.Context
import play.filters.HttpFiltersComponents
import play.api.routing.Router
import router.Routes

import scala.concurrent.ExecutionContext

class LeaseMilesCalculatorLoader extends ApplicationLoader {

  override def load(context: Context): Application =
    new LeaseMilesCalculatorComponents(context).application

}

class LeaseMilesCalculatorComponents(
  context: Context
) extends BuiltInComponentsFromContext(context)
    with TermBalanceModule
    with HttpFiltersComponents {

  implicit lazy val ec: ExecutionContext = actorSystem.dispatcher

  lazy val router: Router = {
    lazy val prefix = "/"
    wire[Routes]
  }

}
