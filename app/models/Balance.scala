package models

import play.api.libs.json.{Json, OWrites}

final case class Balance(
  monthly: Int,
  total: Int
)

object Balance {
  implicit val encoder: OWrites[Balance] = Json.writes[Balance]
}
