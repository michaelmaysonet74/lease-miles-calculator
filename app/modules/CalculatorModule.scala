package modules

import services.{CalculatorService, CalculatorServiceImpl}
import com.softwaremill.macwire.wire

trait CalculatorModule {

  lazy val calculatorService: CalculatorService = wire[CalculatorServiceImpl]

}
