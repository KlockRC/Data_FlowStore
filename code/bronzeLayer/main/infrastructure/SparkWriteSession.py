from abc import ABC, abstractmethod
from pyspark.sql import DataFrame


class SparkWriteSession(ABC):
    @abstractmethod
    def to_sql(self, df: DataFrame, table: str, mode: str = "append") -> None:
        pass