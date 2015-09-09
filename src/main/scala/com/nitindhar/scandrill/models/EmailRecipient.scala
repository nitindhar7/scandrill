package com.nitindhar.scandrill.models

import play.api.libs.json.{Writes, Json}

case class EmailRecipient(
  email: String,
  name: Option[String] = None,
  `type`: String = "to"
)

object EmailRecipient {
  implicit object EmailRecipientWrites extends Writes[EmailRecipient] {
    def writes(emailRecipient: EmailRecipient) = Json.obj(
      "email" -> emailRecipient.email,
      "name" -> emailRecipient.name,
      "type" -> emailRecipient.`type`
    )
  }
}
