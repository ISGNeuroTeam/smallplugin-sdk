package ot.dispatcher.plugins.small.sdk

import org.apache.spark.sql.DataFrame
import ot.dispatcher.sdk.PluginUtils

trait ApplyModel {


  def apply(searchId: Int, featureCols: List[String], targetName: Option[String],
            keywords: Map[String, String], utils: PluginUtils): DataFrame => DataFrame

}
