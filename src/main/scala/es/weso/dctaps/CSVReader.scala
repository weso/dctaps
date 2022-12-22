package es.weso.dctaps

import cats.effect._
import cats.implicits._
import fs2._
import fs2.io.file.{Files, Flags, Path}
import fs2.data.csv._
import fs2.data.csv.lowlevel._
//import fs2.data.csv.generic.semiauto._

object CSVReader {
 def readCSV(path: Path): Stream[IO, Map[String, String]] = {
      Files[IO].readAll(path, 1024, Flags.Read)
          .through(text.utf8.decode) 
          .through(lowlevel.rows[IO, String]())
          .through(lowlevel.headers[IO,String])
          .map(_.toMap)
          .map(normalizeMap(_))
 }

 def dctapElements[F[_]]: Pipe[F, Map[String,String], Either[DCTAPError, DCTAPElement]] = s =>
    s.map(parseDCTapElement)

 def parseDCTapElement(m: Map[String, String]): Either[DCTAPError, DCTAPElement] =
   parseLs(List(
      ("note", (e: DCTAPElement) => e.withNote.apply),
      ("severity", (e: DCTAPElement) => e.withSeverity.apply),
      ("shapeid", (e: DCTAPElement) => e.withShapeId.apply)
   ), m) // (DCTAPElement.empty)
/*   parse("note", m, _.withNote.apply)
   parse("severity", m, _.withSeverity.apply) 
   parse("valueshape", m, _.withValueShape.apply) 
   parse("valueconstrainttype", m, _.withValueConstraintType.apply) 
   parse("valueconstraint", m, _.withValueConstraint.apply) 
   parse("valuenodetype", m, _.withValueNodeType.apply) 
   parse("repeatable", m, _.withRepeatable.apply) 
   parse("mandatory", m, _.withMandatory.apply) 
   parse("propertylabel", m, _.withPropertyLabel.apply) 
   parse("propertyid", m, _.withPropertyId.apply) 
   parse("shapeid", m, _.withShapeId.apply) 
   (DCTAPElement.empty.asRight) */

 def parseLs(ls: List[(String, 
         DCTAPElement => String => Either[DCTAPError, DCTAPElement])],
         m: Map[String, String]
         ): Either[DCTAPError, DCTAPElement] = 
   ls.foldM(DCTAPElement.empty){ case (current, pair) => {
     val (s, updateFn) = pair
     m.get(s).fold(current.asRight)(updateFn(current)(_))
   }}            

 def normalizeMap(m: Map[String, String]): Map[String, String] =
    m.toList.map{ case (k,v) => (normalize(k), v)}.toMap

 def normalize(s: String): String =
    s.toLowerCase().filterNot(c => " _-".contains(c))

 def parse(
   s: String, 
   m: Map[String, String], 
   update: DCTAPElement => String => Either[DCTAPError, DCTAPElement]
   ): DCTAPElement => Either[DCTAPError, DCTAPElement] = e => 
    m.get(s).fold(e.asRight)(update(e)(_))

    
}