package ot.dispatcher.plugins.small.sdk

import org.apache.spark.ml.PipelineModel
import org.apache.spark.sql.DataFrame
import ot.dispatcher.sdk.PluginUtils

trait FitModel {

  def fit(modelName: String, searchId: Int, featureCols: List[String], targetCol: Option[String], keywords: Map[String, String], utils: PluginUtils): DataFrame => (PipelineModel, DataFrame)

}
