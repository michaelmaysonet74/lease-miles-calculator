package models

import play.api.libs.json.{Json, OWrites}

final case class TermBalanceResponse(
  currentMiles: Int,
  balance: Balance,
  lease: LeaseInfo
)

object TermBalanceResponse {
  implicit val encoder: OWrites[TermBalanceResponse] = Json.writes[TermBalanceResponse]
}
