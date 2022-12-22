scalaVersion := "3.2.1"

// Set to false or remove if you want to show stubs as linking errors
nativeLinkStubs := true

libraryDependencies ++= Seq(
  "org.typelevel" %%% "cats-core"      % "2.9.0",
  "org.typelevel" %%% "cats-effect"    % "3.4.2",
  "co.fs2"        %%% "fs2-core"       % "3.4.0",
  "co.fs2"        %%% "fs2-io"         % "3.4.0",
  "org.gnieh"     %%% "fs2-data-csv"   % "1.6.0", 
  "com.monovore"  %%% "decline"        % "2.4.0",
  "com.monovore"  %%% "decline-effect" % "2.4.0",
)

// enablePlugins(ScalaNativePlugin)