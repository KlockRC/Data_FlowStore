package models

import io.circe.{Encoder, Decoder}
import io.circe.generic.semiauto._

case class Vendedor(
    seller_id: String,
    seller_zip_code_prefix: String,
    seller_city: String,
    seller_state: String
)


object Vendedor {
    implicit val vendedorEncoder: Encoder[Vendedor] = deriveEncoder[Vendedor]
    implicit val vendedorDecoder: Decoder[Vendedor] = deriveDecoder[Vendedor]
}