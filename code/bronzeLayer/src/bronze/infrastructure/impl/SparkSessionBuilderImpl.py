from bronze.infrastructure.SparkSessionBuilder import SparkSessionBuilder
from pyspark.sql import SparkSession

class SparkSessionBuilderImpl(SparkSessionBuilder):
    def __init__(self, AppName: str, cluster: str):
        self.sparksession = SparkSession.builder \
            .appName(AppName) \
            .master(cluster) \
            .getOrCreate()
        
    def build(self) -> SparkSession:
        return self.sparksession
    
    def conf_aws_region_s3(self, region: str) -> SparkSession:
        self.spark_session.conf.set("spark.hadoop.fs.s3a.endpoint", f"s3.{region}.amazonaws.com")
        self.spark_session.conf.set("spark.hadoop.fs.s3a.impl", "org.apache.hadoop.fs.s3a.S3AFileSystem")
        return self.spark_session