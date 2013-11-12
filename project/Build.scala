import sbt._
import trafficland.opensource.sbt.plugins._

object App {
  val appName = "s4"
  val appVersion = "0.0.1"
  val organization = "erez"
}

object ApplicationBuild extends Build {

  import App._
  import BuildSettings._

  val main = play.Project(appName, appVersion, appDependencies)
    .configs(IntTests, AllTests, SysTests)
    .settings(inConfig(IntTests)(Defaults.testTasks): _ *)
    .settings(inConfig(AllTests)(Defaults.testTasks): _ *)
    .settings(inConfig(SysTests)(Defaults.testTasks): _ *)
    .settings(ourSettings: _*)
    .settings(ourBuildInfoSettings: _*)
    .settings(net.virtualvoid.sbt.graph.Plugin.graphSettings: _*)
    .dependsOn(gitHubDeps: _*)
}
