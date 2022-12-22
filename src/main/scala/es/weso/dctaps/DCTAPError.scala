package es.weso.dctaps

sealed abstract class DCTAPError(msg: String, inner: Throwable = null) extends Exception(msg, inner)

class ErrorParsingMandatoryAsBoolean(value: String, inner: Throwable = null)
    extends DCTAPError("Error parsing mandatory value as Boolean. Value: $value", inner) 

