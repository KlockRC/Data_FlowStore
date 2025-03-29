package models

import io.circe.{Encoder, Decoder}
import io.circe.generic.semiauto._

case class Produto(
  product_id: String,
  product_category_name: String,
  product_name_lenght: String,
  product_description_lenght: String,
  product_photos_qty: String,
  product_weight_g: String,
  product_length_cm: String,
  product_height_cm: String,
  product_width_cm: String 
)

object Produto {
  implicit val produtoEncoder: Encoder[Produto] = deriveEncoder[Produto]
  implicit val produtoDecoder: Decoder[Produto] = deriveDecoder[Produto]
}
