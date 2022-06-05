package models

import com.rallyhealth.weepickle.v1.WeePickle.{macroFromTo, FromTo}

final case class LeaseInfo(
  month: Int,
  today: Today
)

object LeaseInfo {

  implicit val leaseInfoFromTo: FromTo[LeaseInfo] = macroFromTo[LeaseInfo]

}
