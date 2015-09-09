package com.nitindhar.scandrill.models

import play.api.libs.json.{Writes, Json}

case class EmailMessage(
  html: String,
  subject: String,
  fromEmail: String,
  fromName: String,
  to: List[EmailRecipient],
  headers: Map[String, String],
  autoText: Boolean = true,
  inlineCss: Boolean = true
)

object EmailMessage {
  implicit object EmailMessageWrites extends Writes[EmailMessage] {
    def writes(emailMessage: EmailMessage) = Json.obj(
      "html" -> emailMessage.html,
      "subject" -> emailMessage.subject,
      "from_email" -> emailMessage.fromEmail,
      "from_name" -> emailMessage.fromName,
      "to" -> Json.toJsFieldJsValueWrapper(emailMessage.to),
      "headers" -> emailMessage.headers,
      "auto_text" -> emailMessage.autoText,
      "inline_css" -> emailMessage.inlineCss
    )
  }
}
