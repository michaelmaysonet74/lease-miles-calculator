package models

import com.rallyhealth.weepickle.v1.WeePickle.{macroFromTo, FromTo}

final case class Today(
  date: String,
  time: String
)

object Today {

  implicit val todayFromTo: FromTo[Today] = macroFromTo[Today]

}
