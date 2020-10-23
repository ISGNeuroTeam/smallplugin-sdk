package ot.dispatcher.plugins.small.sdk

import org.apache.spark.sql.DataFrame
import ot.dispatcher.plugins.small.sdk.test.ModelSpec

class DummyApplySpec extends ModelSpec {

  "The dummy model" should "be easy to test" in { utils =>
    val run = DummyApply.apply(
      modelName = "DummyModel",
      modelConfig = None,
      searchId = 0,
      featureCols = List.empty,
      targetName = None,
      keywords = Map.empty,
      utils = utils
    )

    import sparkSession.implicits._

    val input: DataFrame = Seq(
      (1,2,3,4,5),
      (1,2,3,4,5)
    ).toDF("a", "b", "c", "d", "e")

    val expected: DataFrame = Seq(
      (1,2,3,4,5),
      (1,1,1,1,1)
    ).toDF("a", "b", "c", "d", "e")

    val actual = run(input)

    actual shouldBe input
    actual shouldNotBe expected
  }
}
