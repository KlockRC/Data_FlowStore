package models

import io.circe.{Encoder, Decoder}
import io.circe.generic.semiauto._

case class  Cliente(
    customer_id: String,
    customer_unique_id: String,
    customer_zip_code_prefix: String,
    customer_city: String,
    customer_state: String
)

object Cliente {
    implicit val clienteEncoder: Encoder[Cliente] = deriveEncoder[Cliente]
    implicit val clienteDecoder: Decoder[Cliente] = deriveDecoder[Cliente]
}
