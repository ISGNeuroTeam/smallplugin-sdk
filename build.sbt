name := "smallplugin-sdk"

description := "SMaLL plugin SDK"

organization := "ot.dispatcher.plugins.small"

version := "1.0.0"

scalaVersion := "2.12.10"

lazy val dependencies = new {

  private val dispatcherSdkVersion = "2.0.0"
  private val sparkVersion = "3.1.2"

  val dispatcherSdk = "ot.dispatcher" % "dispatcher-sdk_2.12" % dispatcherSdkVersion % Compile
  val sparkMlLib = "org.apache.spark" %% "spark-mllib" % sparkVersion % Provided
}

libraryDependencies ++= Seq(
  dependencies.dispatcherSdk,
  dependencies.sparkMlLib
)