from pyspark.sql.types import StructType, StructField, StringType

ReviewSchema = StructType([
  StructField("review_id", StringType(), nullable = True),
  StructField("order_id", StringType(), nullable = True),
  StructField("review_score", StringType(), nullable = True),
  StructField("review_comment_title", StringType(), nullable = True),
  StructField("review_comment_message", StringType(), nullable = True),
  StructField("review_creation_date", StringType(), nullable = True),
  StructField("review_answer_timestamp", StringType(), nullable = True)
])