val scala3Version = "3.6.4"

lazy val root = project
  .in(file("."))
  .settings(
    name := "discordbot",
    version := "0.1.0",

    scalaVersion := scala3Version,

    libraryDependencies += "net.katsstuff" %% "ackcord"                 % "0.18.1",
    libraryDependencies += "net.katsstuff" %% "ackcord-core"            % "0.18.1",
    libraryDependencies += "net.katsstuff" %% "ackcord-commands"        % "0.18.1",
    libraryDependencies += "net.katsstuff" %% "ackcord-lavaplayer-core" % "0.18.1"
  )