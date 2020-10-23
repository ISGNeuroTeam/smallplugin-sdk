package ot.dispatcher.plugins.small.sdk.test

import com.typesafe.config.{Config, ConfigFactory}
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.scalatest.exceptions.TestFailedException
import org.scalatest.{Assertion, BeforeAndAfterAll, Outcome, fixture}
import ot.dispatcher.sdk.PluginUtils

abstract class ModelSpec extends fixture.FlatSpec with BeforeAndAfterAll {

  override type FixtureParam = PluginUtils

  protected lazy val sparkLogLevel: Level = Level.ERROR

  protected val smallCoreConfig: Config =
    ConfigFactory.empty("SMaLL Plugin Core Config")

  protected val dispatcherConfig: Config =
    ConfigFactory.empty("Dispatcher Config")

  protected lazy val sparkSession: SparkSession =
    SparkSession.builder()
      .appName(this.getClass.getSimpleName)
      .master("local")
      .getOrCreate()


  protected def getPluginUtils(sparkSession: SparkSession, coreConfig: Config, dispatcherConfig: Config): PluginUtils =
    new PluginUtils {

    override def getLoggerFor(classname: String): Logger = {
      val log = Logger.getLogger(classname)
      log.setLevel(Level.DEBUG)
      log
    }

    override def logLevelOf(name: String): String =
      Logger.getLogger(name).getLevel.toString

    override def printDfHeadToLog(log: Logger, id: Int, df: DataFrame): Unit =
      df.head(10).foreach(log.debug(_))

    override def sendError(id: Int, message: String): Nothing =
      throw new Error(message)

    override def spark: SparkSession =
      sparkSession

    override def executeQuery(query: String, df: DataFrame): DataFrame =
      ???

    override def executeQuery(query: String, index: String, startTime: Int, finishTime: Int): DataFrame =
      ???

    override def pluginConfig: Config =
      coreConfig

    override def mainConfig: Config =
      dispatcherConfig
  }

  override def beforeAll(): Unit =
    Logger.getLogger("org.apache.spark")
      .setLevel(sparkLogLevel)

  override def afterAll(): Unit =
    sparkSession.stop()

  override def withFixture(test: OneArgTest): Outcome = {
    val utils = getPluginUtils(sparkSession, smallCoreConfig, dispatcherConfig)
    withFixture(test.toNoArgTest(utils))
  }

  implicit class CompareDataFrames(left: DataFrame) {
    def shouldBe(right: DataFrame): Assertion = {
      assert(
        right.count() == left.count(),
        "DataFrames sizes should be equal"
      )

      val diff = left.except(right).union(right.except(left))

      assert(
        diff.isEmpty,
        s"DataFrames are different. Difference is:\n${diff.collect().mkString("\n")}"
      )
    }

    def shouldNotBe(right: DataFrame): Assertion = {
      assertThrows[TestFailedException](shouldBe(right))
    }
  }

}
