name := "SLF4JTest"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq("org.apache.logging.log4j" %% "log4j-api-scala" % "2.8.2",
"org.slf4j" % "slf4j-api" % "1.7.5",
"log4j" % "log4j" % "1.2.14",
  javaJdbc,
  cache,
  "org.springframework" % "spring-jdbc" % "4.2.5.RELEASE",
  "org.springframework" % "spring-orm" % "4.2.5.RELEASE",
  "org.springframework" % "spring-tx" % "4.2.5.RELEASE",
  "org.springframework" % "spring-expression" % "4.2.5.RELEASE",
  "org.springframework" % "spring-context" % "4.2.5.RELEASE",
  "org.springframework" % "spring-test" % "4.2.5.RELEASE",
  "org.hibernate" % "hibernate-entitymanager" % "5.1.0.Final",
  "mysql" % "mysql-connector-java" % "5.1.35",
  "javax.inject" % "javax.inject" % "1",
  "org.webjars" % "bootstrap" % "2.1.1"
)
