package com.nitindhar.scandrill.models

import play.api.libs.json.{Writes, Json}

case class EmailPacket(
  key: String,
  message: EmailMessage,
  async: Boolean = false,
  ipPool: String = "Main Pool",
  returnPathDomain: Option[String] = None
)

object EmailPacket {
  implicit object EmailPacketWrites extends Writes[EmailPacket] {
    def writes(emailPacket: EmailPacket) = Json.obj(
      "key" -> emailPacket.key,
      "message" -> Json.toJsFieldJsValueWrapper(emailPacket.message),
      "async" -> emailPacket.async,
      "ip_pool" -> emailPacket.ipPool,
      "return_path_domain" -> emailPacket.returnPathDomain
    )
  }
}

