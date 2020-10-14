package ot.dispatcher.plugins.small.sdk

import com.typesafe.config.Config
import org.apache.spark.sql.DataFrame

trait ScoreModel {

  def score(modelName: String, modelConfig: Option[Config], searchId: Int, labelCol: String,
            predictionCol: List[String], featuresNumber: Double): DataFrame => DataFrame

}
