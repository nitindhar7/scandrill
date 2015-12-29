package com.nitindhar.scandrill.models

import play.api.libs.functional.syntax._
import play.api.libs.json._
import play.api.libs.json.Reads

case class ScandrillResponse(
  id: String,
  email: String,
  rejectReason: String,
  status: String
)

object ScandrillResponse {
  implicit def scandrillResponseReads: Reads[ScandrillResponse] = {
    (
      (__ \ "_id").read[String] and
      (__ \ "email").read[String] and
      (__ \ "reject_response").read[String] and
      (__ \ "status").read[String]
    )(ScandrillResponse.apply _)
  }
}
