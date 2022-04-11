name := "smallplugin-sdk"

description := "SMaLL plugin SDK"

organization := "ot.dispatcher.plugins.small"

version := "0.3.0"

scalaVersion := "2.11.12"

lazy val dependencies = new {

  private val dispatcherSdkVersion = "1.2.0"
  private val sparkVersion = "2.4.3"

  val dispatcherSdk = "ot.dispatcher" % "dispatcher-sdk_2.11" % dispatcherSdkVersion % Compile
  val sparkMlLib = "org.apache.spark" %% "spark-mllib" % sparkVersion % Provided
}

libraryDependencies ++= Seq(
  dependencies.dispatcherSdk,
  dependencies.sparkMlLib
)

credentials += Credentials("Sonatype Nexus Repository Manager", sys.env.getOrElse("NEXUS_HOSTNAME", ""), sys.env.getOrElse("NEXUS_COMMON_CREDS_USR", ""), sys.env.getOrElse("NEXUS_COMMON_CREDS_PSW", ""))

publishTo := Some("Sonatype Nexus Repository Manager" at sys.env.getOrElse("NEXUS_OTP_URL_HTTPS", "") + "/repository/ot.platform-sbt-releases")
