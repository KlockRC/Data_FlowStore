from pyspark.sql import DataFrame
from main.infrastructure.SparkWriteSession import SparkWriteSession

class SparkWriteSessionImpl(SparkWriteSession):
    def __init__(self, jdbc_url: str, properties: dict):
        self.jdbc_url = jdbc_url
        self.properties = properties

    def to_sql(self, df: DataFrame, table: str, mode: str = "append") -> None:
        df.write \
            .mode(mode) \
            .jdbc(url=self.jdbc_url, table=table, properties=self.properties)