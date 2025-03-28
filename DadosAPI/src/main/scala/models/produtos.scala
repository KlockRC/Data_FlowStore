package models

import io.circe.{Encoder, Decoder}
import io.circe.generic.semiauto._

case class Produto(
  product_id: String,
  product_category_name: String,
  product_name_lenght: Int,
  product_description_lenght: Int,
  product_photos_qty: Int,
  product_weight_g: Int,
  product_length_cm: Int,
  product_height_cm: Int,
  product_width_cm: Int // Adicionado o novo campo
)

object Produto {
  implicit val produtoEncoder: Encoder[Produto] = deriveEncoder[Produto]
  implicit val produtoDecoder: Decoder[Produto] = deriveDecoder[Produto]
}