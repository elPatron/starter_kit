resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases/"

resolvers += "sonatype-public" at "https://oss.sonatype.org/content/groups/public"

resolvers += "repo.codahale.com" at "http://repo.codahale.com"

resolvers += Resolver.url("Artifactory Online", url("http://repo.scala-sbt.org/scalasbt/repo"))(Resolver.ivyStylePatterns)

addSbtPlugin("com.trafficland" % "sbt-plugins" % "0.8.0")

addSbtPlugin("play" % "sbt-plugin" % "2.1.5")

addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.7.1")

addSbtPlugin("com.eed3si9n" % "sbt-buildinfo" % "0.2.5")