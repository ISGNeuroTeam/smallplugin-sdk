package ot.dispatcher.plugins.small.sdk

import com.typesafe.config.Config
import org.apache.spark.sql.DataFrame
import ot.dispatcher.sdk.PluginUtils

trait ScoreModel {

  def score(modelName: String, modelConfig: Option[Config], searchId: Int, labelCol: String,
            predictionCol: String, keywords: Map[String, String], utils: PluginUtils): DataFrame => DataFrame

}
