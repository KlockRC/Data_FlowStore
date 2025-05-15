
from pyspark.sql.types import StructType, StructField, StringType

ProdutoSchema = StructType([
  StructField("product_id",StringType(), nullable = True),
  StructField("product_category_name",StringType(), nullable = True),
  StructField("product_name_lenght",StringType(), nullable = True),
  StructField("product_description_lenght",StringType(), nullable = True),
  StructField("product_photos_qty",StringType(), nullable = True),
  StructField("product_weight_g",StringType(), nullable = True),
  StructField("product_length_cm",StringType(), nullable = True),
  StructField("product_height_cm",StringType(), nullable = True),
  StructField("product_width_cm",StringType(), nullable = True)
])
