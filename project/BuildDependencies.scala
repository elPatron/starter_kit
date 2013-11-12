import sbt._

object BuildDependencies {
  val gitHubDependencies: Option[Array[ClasspathDep[ProjectReference]]] = None
  //Array(RootProject(uri("https://github.com/nmccready/akka-fileio.git")))

  val resolutionRepos = Seq(
    "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases/",
    "Typesafe repo" at "http://repo.typesafe.com/typesafe/releases/",
    "sonatype-public" at "https://oss.sonatype.org/content/repositories/public")

  val compileDeps = Seq(
    "play" %% "play-test" % play.core.PlayVersion.current, //use plugins version
    "com.github.scala-incubator.io" %% "scala-io-core" % V.scalax_io, //scala-io-file is in play
    "org.mockito" % "mockito-core" % V.mockito,
    "com.typesafe.akka" %% "akka-contrib" % V.akka
  )

  val testDeps = Seq(
    "play" %% "play-test" % play.core.PlayVersion.current,
    "com.github.scala-incubator.io" %% "scala-io-core" % V.scalax_io,
    "com.typesafe.akka" %% "akka-testkit" % V.akka,
    "org.scalatest" %% "scalatest" % V.scalaTest,
    "org.mockito" % "mockito-core" % V.mockito,
    "com.chuusai" %% "shapeless" % V.shapeless
    // "de.vorb" %% "akka-fileio" % V.akka_fileio
  )

  object V {
    // see play Change log, since 2.1.3 akka version can be overriden
    //http://www.playframework.com/changelog
    val akka = "2.2.3"
    val scalaTest = "2.0.M5b"
    val mockito = "1.9.0"
    val scalax_io = "0.4.0"
    val shapeless = "1.2.4"
    // val akka_fileio = "0.0.0"
  }

  def addScalaV(namespace: String, version: String): String = appendAll(namespace, "_", version)

  def appendAll(strings: String*) = {
    val sb = new StringBuilder()
    strings.foreach(s => sb.append(s))
    sb.toString
  }
}