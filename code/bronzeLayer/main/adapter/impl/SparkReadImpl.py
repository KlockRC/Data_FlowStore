from pyspark.sql import SparkSession, DataFrame
from pyspark.sql.functions import col, from_json
from pyspark.sql.types import StructType
from main.adapter.SparkRead import SparkRead
from kafka import KafkaConsumer
import json


class SparkReadImpl(SparkRead):
    def __init__(self, spark: SparkSession):
        self.spark = spark

    def read_kafka(self, kafka_bootstrap: str, topic: str, schema: StructType) -> DataFrame:
        consumer = KafkaConsumer(
            topic,
            bootstrap_servers=kafka_bootstrap,
            auto_offset_reset='earliest',
            enable_auto_commit=False,
            value_deserializer=lambda x: x.decode('utf-8'),
            consumer_timeout_ms=100
        )
        data = []
        for message in consumer:
            try:
                data2 = json.loads(message.value)
                data.append(data2)
            except json.JSONDecodeError:
                print("log nao esqueça") ######## logs test
        consumer.close()
        df = self.spark.createDataFrame(data,schema)
        return df

    def read_s3(self, format: str, s3_path: str) -> DataFrame:
        return self.spark.read \
            .format(format) \
            .option("inferSchema", "true") \
            .option("header", "true") \
            .load(s3_path)
    def read_sql(self, table: str, con: str, prop: dict) -> DataFrame:
        return self.spark.read.jdbc(
            url=con,
            table=table,
            properties=prop
        )
