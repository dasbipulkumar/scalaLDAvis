import Build._

unmanagedSourceDirectories in Compile += baseDirectory.value / "examples" / "src"
unmanagedResourceDirectories in Compile += baseDirectory.value / "resources"
unmanagedResources in Compile += baseDirectory.value / "resources"
unmanagedResources in Test += baseDirectory.value / "resources"
unmanagedResources in Runtime += baseDirectory.value / "resources"


lazy val localRoot = Project(id = "scalaLDAVis", base = file("."))
                  .settings(BuildSettings.clusterSettings)

lazy val clusterRoot = Project(id = "scalaLDAVis-local", base = file("."))
  .settings(BuildSettings.intelliJSettings)


//Sonatype Publishing Settings

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (version.value.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

pomExtra := (
  <url>https://github.com/iaja/scalaLDAvis</url>
    <licenses>
      <license>
        <name>Apache License, Version 2.0</name>
        <url>http://www.apache.org/licenses/LICENSE-2.0.html</url>
        <distribution>repo</distribution>
      </license>
    </licenses>
    <scm>
      <url>git@github.com:iaja/scalaLDAvis</url>
      <connection>scm:git:git@github.com:iaja/scalaLDAvis</connection>
    </scm>
    <developers>
      <developer>
        <id>Mageswaran1989</id>
        <name>Mageswaran Dhandapani</name>
      </developer>
    </developers>)

useGpg := true
publishMavenStyle := true
credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")