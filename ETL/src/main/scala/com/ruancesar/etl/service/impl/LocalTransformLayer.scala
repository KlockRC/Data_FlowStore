package com.ruancesar.etl.service.impl

import com.ruancesar.etl.service.TransformLayer
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

class LocalTransformLayer(df: DataFrame) extends TransformLayer {
  override def getValidRecords(): DataFrame = {
    ???
  }

  override def getInvalidRecords(): DataFrame = {
    ???
  }
}
