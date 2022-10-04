package models

import play.api.libs.json.{Json, OWrites}

final case class LeaseInfo(
  year: Option[Int] = None,
  month: Int,
  today: Today
)

object LeaseInfo {
  implicit val encoder: OWrites[LeaseInfo] = Json.writes[LeaseInfo]
}
