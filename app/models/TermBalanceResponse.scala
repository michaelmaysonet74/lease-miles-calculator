package models

import com.rallyhealth.weepickle.v1.WeePickle.{macroFromTo, FromTo}

final case class TermBalanceResponse(
  balance: Balance,
  lease: LeaseInfo
)

object TermBalanceResponse {

  implicit val termBalanceResponseFromTo: FromTo[TermBalanceResponse] =
    macroFromTo[TermBalanceResponse]

}
