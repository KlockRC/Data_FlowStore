from pyspark.sql import SparkSession, DataFrame
from pyspark.sql.functions import col, from_json
from pyspark.sql.types import StructType
from bronze.adapter.SparkRead import SparkRead


class SparkReadImpl(SparkRead):
    def __init__(self, spark: SparkSession):
        self.spark = spark

    def read_kafka(self, kafka_bootstrap: str, topic: str, schema: StructType) -> DataFrame:
        df = self.spark.read \
            .format("kafka") \
            .option("kafka.bootstrap.servers", kafka_bootstrap) \
            .option("subscribe", topic) \
            .load() \
            .selectExpr("CAST(value AS STRING)") \
            .withColumn("json_data", from_json(col("value"), schema)) \
            .select("json_data.*")
        return df

    def read_s3(self, format: str, s3_path: str) -> DataFrame:
        return self.spark.read \
            .format(format) \
            .option("inferSchema", "true") \
            .option("header", "true") \
            .load(s3_path)