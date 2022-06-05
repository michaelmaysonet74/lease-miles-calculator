package modules

import services.{DateService, DateServiceImpl}
import com.softwaremill.macwire.wire

trait DateModule {

  lazy val dateService: DateService = wire[DateServiceImpl]

}
