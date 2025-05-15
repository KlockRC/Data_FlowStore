from main.infrastructure.SparkSessionBuilder import SparkSessionBuilder
from pyspark.sql import SparkSession

class SparkSessionBuilderImpl(SparkSessionBuilder):
    def __init__(self, AppName: str, cluster: str):
        self.AppName = AppName
        self.cluster = cluster
        self.sparksession = SparkSession.builder \
            .appName(AppName) \
            .config("spark.jars.packages",
                    "org.apache.hadoop:hadoop-aws:3.3.4,com.amazonaws:aws-java-sdk-bundle:1.11.1026,org.postgresql:postgresql:42.7.3") \
            .master(cluster) \
            .getOrCreate()
        
    def build(self) -> SparkSession:
        return self.sparksession
    
    def confAwsRegionS3(self, region: str) -> SparkSession:
        spark = self.sparksession
        spark.conf.set("spark.hadoop.fs.s3a.endpoint", f"s3.{region}.amazonaws.com")
        spark.conf.set("spark.hadoop.fs.s3a.impl", "org.apache.hadoop.fs.s3a.S3AFileSystem")
        return spark