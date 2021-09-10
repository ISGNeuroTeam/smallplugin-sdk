# SMaLL plugin SDK

The tools for development a SMaLL plugin extensions.

## Getting Started

### Prerequisites

This package is require the SBT, and the JDK 8 installed.
It also required the `dispatcher-sdk` module published locally.

## Creating extension

To create your own extension you need implement trait method.

### Configure project

To start development process you only need to add **SMaLL plugin SDK** library dependency in your project like this:

```
  lazy val smallPluginSdkVersion: String = "0.2.0"

  libraryDependencies += "ot.dispatcher.plugins.small" % "smallplugin-sdk_2.11" % smallPluginSdkVersion % Provided
```
The `smallplugin-sdk` library is a part of the **SMaLL plugin Core** and the `smallplugin-sdk` is compiled into it.
So you can mark the `smallplugin-sdk` library as `Provided` to make you extension's size much smaller. 

### An `apply` command extension

An `apply` command extension can be created by implementing the `apply` method of the `ApplyModel` trait.

```scala
object MyApplyModel extends ApplyModel {

  override def apply(modelName: String, modelConfig: Option[Config], searchId: Int, featureCols: List[String],
                     targetName: Option[String], keywords: Map[String, String], utils: PluginUtils)
    : DataFrame => DataFrame =

      df => {
        
        val result: DataFrame = ???
        result
      }
}
``` 

### The `fit` command extension

The `fit` command extension can be created by implementing the `fit` method of the `FitModel` trait.

```scala
object MyFitModel extends FitModel {

  override def fit(modelName: String, modelConfig: Option[Config], searchId: Int, featureCols: List[String],
                   targetCol: Option[String], keywords: Map[String, String], utils: PluginUtils)
    : DataFrame => (PipelineModel, DataFrame) =

      df => {
      
        val result: (PipelineModel, DataFrame) = ???
        result
      }
}
```

### The `score` command extension

The `score` command extension can be created by implementing the `score` method of the `ScoreModel` trait.

```scala
object MyScoreModel extends ScoreModel {

  override def score(modelName: String, modelConfig: Option[Config], searchId: Int, labelCol: String,
                     predictionCol: String, keywords: Map[String, String], utils: PluginUtils)
    : DataFrame => DataFrame =

      df => {
        
        val result: DataFrame = ???
        result
      }
}
```

### The multi-command extension

It is possible to create multi-command extension by extending multiple traits for each command you wish.

```scala
object MyMultiModel extends ScoreModel with FitModel with ApplyModel {

  override def apply(modelName: String, modelConfig: Option[Config], searchId: Int, featureCols: List[String],
                     targetName: Option[String], keywords: Map[String, String], utils: PluginUtils)
    : DataFrame => DataFrame =
      ???

  override def score(modelName: String, modelConfig: Option[Config], searchId: Int, labelCol: String,
                     predictionCol: String, keywords: Map[String, String], utils: PluginUtils)
    : DataFrame => DataFrame = 
      ???

  override def fit(modelName: String, modelConfig: Option[Config], searchId: Int, featureCols: List[String],
                   targetCol: Option[String], keywords: Map[String, String], utils: PluginUtils)
    : DataFrame => (PipelineModel, DataFrame) = 
      ???
}
```

## Testing extensions

This SDK module provides testing tools for developers of the **SMaLL Plugin Core** extensions.
The testing tools based on the `ScalaTest` framework and provides the abstract suite in `FlatSpec` style.
The `ModelSpec` class provides the `PluginUtils` as the _fixture_ to each test case as argument.

It also provides the `DataFrame`'s matchers `shouldBe` and `shouldNotBe`. 
The base `ModelSpec` testing suite class is not includes [ScalaTest's Matchers](https://www.scalatest.org/user_guide/using_matchers) you need manually extend your test suite with it to use `SmcalaTest`'s matchers. 

### Example
```scala
class DummyApplySpec extends ModelSpec {

  "The dummy model" should "be easy to test" in { utils =>
    val run = DummyApply.apply(
      ...
      utils = utils
    )

    val input: DataFrame = ...

    val expected: DataFrame = ...

    val actual = run(input)

    actual shouldBe input
    actual shouldNotBe expected
  }
}
```

_The full code example can be found in the `src/test` directory of this module._

### Access the SMaLL Plugin Core and the Dispatcher configuration

The **SMaLL Plugin Core**'s and the **Dispatcher** configuration defined as protected properties of the `ModelSpec`.
It can be overwritten in you test suite and access them via `PluginUtils` variable.
```scala

class MyModelSpec extends ModelSpec {
  
  override val smallCoreConfig: Config = ...

  override val dispatcherConfig: Config = ...
}
```


## Deployment

Then your extension is ready you can deploy it as a part of the **SMaLL Plugin Core**.
See the "Extensions deployment" section of **SMaLL Plugin Core** README.md file.

## Built With

* [SBT](https://www.scala-sbt.org) - Build tool for Scala and Java projects

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

Dmitriy Arkhireev (darkhireev@isgneuro.com)

Nikolay Ryabykh (nryabykh@isgneuro.com)  


## License

[OT.PLATFORM. License agreement.](LICENSE.md)


## Acknowledgments

Dmitriy Arkhireev (darkhireev@isgneuro.com)

Nikolay Ryabykh (nryabykh@isgneuro.com)  

Andrey Starchenkov (astarchenkov@isgneuro.com)