package com.nitindhar.scandrill.models

import play.api.libs.functional.syntax._
import play.api.libs.json._
import play.api.libs.json.Reads

case class SendResponse(
  id: String,
  email: String,
  rejectReason: String,
  status: String
)

object SendResponse {
  implicit def sendResponseReads: Reads[SendResponse] = {
    (
      (__ \ "_id").read[String] and
      (__ \ "email").read[String] and
      (__ \ "reject_response").read[String] and
      (__ \ "status").read[String]
    )(SendResponse.apply _)
  }
}
