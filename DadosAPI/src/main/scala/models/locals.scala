package models

import io.circe.{Encoder, Decoder}
import io.circe.generic.semiauto._

case class Local(
    geolocation_zip_code_prefix: String,
    geolocation_lat: String,
    geolocation_lng: String,
    geolocation_city: String,
    geolocation_state: String
)

object Local {
    implicit var localEncoder: Encoder[Local] = deriveEncoder[Local]
    implicit var localDecoder: Decoder[Local] = deriveDecoder[Local]
}