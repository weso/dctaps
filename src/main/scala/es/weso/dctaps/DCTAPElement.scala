package es.weso.dctaps
import cats.implicits._

case class DCTAPElement(
 shapeId: Option[String] = None, 
 propertyId: Option[String] = None, 
 propertyLabel: Option[String] = None,
 mandatory: Boolean = Defaults.MandatoryDefault,
 repeatable: Boolean = Defaults.RepeatableDefault,
 valueNodeType: Option[String] = None,
 valueDataType: Option[String] = None,
 valueConstraint: Option[String] = None,
 valueConstraintType: Option[String] = None,
 valueShape: Option[String] = None,
 note: Option[String] = None,
 severity: Option[String] = None
) {
  def withShapeId(shapeId: String): Either[DCTAPError, DCTAPElement] =
    this.copy(shapeId = Some(shapeId)).asRight

  def withPropertyId(pId: String): Either[DCTAPError, DCTAPElement] =
    this.copy(propertyId = Some(pId)).asRight

  def withPropertyLabel(lbl: String): Either[DCTAPError, DCTAPElement] =
    this.copy(propertyLabel = Some(lbl)).asRight

  def withMandatory(s: String): Either[DCTAPError, DCTAPElement] =  
    this.asRight // pending

  def withRepeatable(s: String): Either[DCTAPError, DCTAPElement] =  
    this.asRight // Pending

  def withValueNodeType(s: String): Either[DCTAPError, DCTAPElement] =  
    this.copy(valueNodeType = Some(s)).asRight

  def withValueDataType(s: String): Either[DCTAPError, DCTAPElement] =  
    this.copy(valueDataType = Some(s)).asRight

  def withValueConstraint(s: String): Either[DCTAPError, DCTAPElement] =  
    this.copy(valueConstraint = Some(s)).asRight

  def withValueConstraintType(s: String): Either[DCTAPError, DCTAPElement] =  
    this.copy(valueConstraintType = Some(s)).asRight

  def withValueShape(s: String): Either[DCTAPError, DCTAPElement] =  
    this.copy(valueShape = Some(s)).asRight

  def withNote(s: String): Either[DCTAPError, DCTAPElement] =  
    this.copy(note = Some(s)).asRight

  def withSeverity(s: String): Either[DCTAPError, DCTAPElement] =  
    this.copy(severity = Some(s)).asRight

}

object DCTAPElement {
  def empty: DCTAPElement = DCTAPElement()
}

