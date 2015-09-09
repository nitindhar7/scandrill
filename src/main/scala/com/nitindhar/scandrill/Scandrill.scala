package com.nitindhar.scandrill

import play.api.libs.json.Json.{toJson, fromJson}
import com.nitindhar.scandrill.models._
import com.nitindhar.scandrill.models.EmailMessage._
import com.nitindhar.scandrill.models.EmailPacket._
import com.nitindhar.scandrill.models.EmailRecipient._
import com.ning.http.client.AsyncHttpClientConfig.Builder
import play.api.libs.ws.ning.NingWSClient
import scala.concurrent.ExecutionContext.Implicits.global

trait Scandrill {

  def apiUri: String

  def apiKey: String

  def defaultFromEmail: String

  def defaultFromName: String

  def sendMessage(template: String, subject: String, to: List[String]) = {
    jsonWS("messages/send").post(toJson(EmailPacket(
      apiKey,
      EmailMessage(
        template,
        subject,
        defaultFromEmail,
        defaultFromName,
        to.map(EmailRecipient(_)),
        Map("Reply-To" -> defaultFromEmail)
      )
    ))).map(resp => {
      Some(fromJson[List[SendResponse]](resp.json))
    }).recover {
      case e: Exception => None
    }
  }

  private def jsonWS(path: String) = {
    new NingWSClient(new Builder().build)
      .url(s"${apiUri}/${path}.json")
      .withHeaders(
        "Content-Type" -> "application/json",
        "Data-Type" -> "application/json"
      )
  }

}

object Scandrill {

  def apply(config: ScandrillConfig) = new Scandrill {
    override val apiUri = config.apiUri
    override val apiKey = config.apiKey
    override val defaultFromEmail = config.defaultFromEmail
    override val defaultFromName = config.defaultFromName
  }

}
