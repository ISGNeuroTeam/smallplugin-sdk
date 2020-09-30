package ot.dispatcher.plugins.small.sdk

import org.apache.spark.sql.DataFrame

trait ScoreModel {

  def score(searchId: String, labelCol: String, predictionCol: String, metricName: String, featuresNumber: Double): DataFrame => DataFrame

}
