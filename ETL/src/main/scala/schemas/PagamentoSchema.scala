package schema

import org.apache.spark.sql.types._

val PagamentoSchema = StructType(Seq(
  StructField("payment_type", StringType, nullable = true),
  StructField("payment_installments", IntegerType, nullable = true),
  StructField("payment_value", DoubleType, nullable = true),
  StructField("payment_sequential", IntegerType, nullable = true),
  StructField("order_id", StringType, nullable = true)
))

