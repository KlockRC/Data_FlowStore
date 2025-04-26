name := "ETL-DataFlowStore"
version := "1.0"
scalaVersion := "3.3.5"
libraryDependencies ++= Seq(
  ("org.apache.spark" %% "spark-sql" % "3.5.5").cross(CrossVersion.for3Use2_13),
  ("org.apache.spark" %% "spark-core" % "3.5.5").cross(CrossVersion.for3Use2_13),
  ("org.apache.spark" %% "spark-streaming" % "3.5.5").cross(CrossVersion.for3Use2_13),
  ("org.apache.spark" %% "spark-sql-kafka-0-10" % "3.5.5").cross(CrossVersion.for3Use2_13),
  ("org.apache.hadoop" % "hadoop-aws" % "3.4.1"),
  ("org.apache.hadoop" % "hadoop-common" % "3.4.1")
)
