package models

import io.circe.{Encoder, Decoder}
import io.circe.generic.semiauto._

case class Review(
    review_id: String,
    order_id: String,
    review_score: String,
    review_comment_title: String,
    review_comment_message: String,
    review_creation_date: String,
    review_answer_timestamp: String
)

object Review {
  implicit val reviewEncoder: Encoder[Review] = deriveEncoder[Review]
  implicit val reviewDecoder: Decoder[Review] = deriveDecoder[Review]
}