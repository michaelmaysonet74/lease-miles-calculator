package models

import play.api.libs.json.{Json, OWrites}

final case class Today(
  date: String,
  time: String
)

object Today {
  implicit val encoder: OWrites[Today] = Json.writes[Today]
}
