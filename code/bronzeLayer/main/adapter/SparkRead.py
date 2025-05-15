from abc import ABC, abstractmethod
from pyspark.sql import DataFrame
from pyspark.sql.types import StructType


class SparkRead(ABC):
    @abstractmethod
    def read_kafka(self, kafka_bootstrap: str, topic: str, schema: StructType) -> DataFrame:
        pass

    @abstractmethod
    def read_s3(self, format: str, s3_path: str) -> DataFrame:
        pass

    @abstractmethod
    def read_sql(self, sql: str, schema: StructType) -> DataFrame:
        pass