package com.nitindhar.scandrill.models

import org.scalatest.{Matchers, FlatSpec}
import play.api.libs.json.{JsArray, Json}

import EmailMessage.EmailMessageWrites

class EmailMessageTest extends FlatSpec with Matchers {

  private val emailMessage = EmailMessage("html", "subject", "from_email", "from_name", List(), Map("a" -> "b"), true, false)

  private val validationJson = Json.obj(
    "html" -> "html",
    "subject" -> "subject",
    "from_email" -> "from_email",
    "from_name" -> "from_name",
    "to" -> JsArray(),
    "headers" -> Json.obj("a" -> "b"),
    "auto_text" -> true,
    "inline_css" -> false
  )

  "Email message" should "produce correct json" in {
    assert(Json.toJson(emailMessage) == validationJson)
  }

}
