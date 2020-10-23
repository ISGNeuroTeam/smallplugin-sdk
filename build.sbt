name := "smallplugin-sdk"

description := "SMaLL plugin SDK"

organization := "ot.dispatcher.plugins.small"

version := "0.2.0"

scalaVersion := "2.11.12"

lazy val dependencies = new {

  private val dispatcherSdkVersion = "1.1.0"
  private val sparkVersion = "2.4.3"

  val dispatcherSdk = "ot.dispatcher" % "dispatcher-sdk_2.11" % dispatcherSdkVersion % Compile
  val sparkMlLib = "org.apache.spark" %% "spark-mllib" % sparkVersion % Provided
}

libraryDependencies ++= Seq(
  dependencies.dispatcherSdk,
  dependencies.sparkMlLib
)