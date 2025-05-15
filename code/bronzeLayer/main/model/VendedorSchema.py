from pyspark.sql.types import StructType, StructField, StringType

VendedorSchema = StructType([
  StructField("seller_id", StringType(), nullable = True),
  StructField("seller_zip_code_prefix", StringType(), nullable = True),
  StructField("seller_city", StringType(), nullable=True),
  StructField("seller_state", StringType(), nullable = True)
])