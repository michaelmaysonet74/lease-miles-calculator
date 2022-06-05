package models

import com.rallyhealth.weepickle.v1.WeePickle.{macroFromTo, FromTo}

final case class Balance(
  monthly: Int,
  total: Int
)

object Balance {

  implicit val balanceFromTo: FromTo[Balance] = macroFromTo[Balance]

}
