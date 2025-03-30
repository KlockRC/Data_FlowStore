package models

import io.circe.{Encoder, Decoder}
import io.circe.generic.semiauto._

case class Pagamento(
    order_id: String,
    payment_sequential: String,
    payment_type: String,
    payment_installments: String,
    payment_value: String
)

object Pagamento {
    implicit val pagamentoEncoder: Encoder[Pagamento] = deriveEncoder[Pagamento]
    implicit val pagamentoDecoder: Decoder[Pagamento] = deriveDecoder[Pagamento]
}