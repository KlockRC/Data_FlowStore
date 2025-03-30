package models

import io.circe.{Encoder, Decoder}
import io.circe.generic.semiauto._

case class Item(
    order_id: String,
    order_item_id: String,
    product_id: String,
    seller_id: String,
    shipping_limit_date: String,
    price: String,
    freight_value: String
)
object Item {
    implicit var itemEncoder: Encoder[Item] = deriveEncoder[Item]
    implicit var itemDecoder: Decoder[Item] = deriveDecoder[Item]
}
