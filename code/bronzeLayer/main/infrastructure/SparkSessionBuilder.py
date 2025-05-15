from abc import ABC, abstractmethod
from pyspark.sql import SparkSession


class SparkSessionBuilder(ABC):
    @abstractmethod
    def build(self) -> SparkSession:
        pass
    @abstractmethod
    def confAwsRegionS3(self, region: str) -> SparkSession:
        pass