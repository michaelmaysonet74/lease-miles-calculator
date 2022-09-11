package models

import com.rallyhealth.weepickle.v1.WeePickle.{macroFromTo, FromTo}
import com.rallyhealth.weepickle.v1.implicits.dropDefault

@dropDefault
final case class LeaseInfo(
  year: Option[Int] = None,
  month: Int,
  today: Today
)

object LeaseInfo {

  implicit val leaseInfoFromTo: FromTo[LeaseInfo] = macroFromTo[LeaseInfo]

}
