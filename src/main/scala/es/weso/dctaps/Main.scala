package es.weso.dctaps
import cats.effect._
import cats.implicits._
import com.monovore.decline._
import com.monovore.decline.effect._
import fs2._
import fs2.io.file.{Files, Flags, Path}
import CSVReader._

case class DCTapsCommand(
    csvPath: Path
)

object Main extends CommandIOApp(
    name = "dctaps",
    header = "DC-TAP processor",
    version = "0.0.1"
) {

 val csvPath: Opts[Path] = Opts.option[String](
      "csv", 
      metavar = "file",
      help="CSV file"
    ).map(Path.apply)

 val command: Opts[DCTapsCommand] = csvPath.map(DCTapsCommand.apply)

 override def main: Opts[IO[ExitCode]] =
  command.map(c => 
    for {
      ls <- readCSV(c.csvPath)
            .through(dctapElements)
            .compile.toList
      _ <- IO.println(s"Lines: $ls")
    } yield ExitCode.Success
  )
}