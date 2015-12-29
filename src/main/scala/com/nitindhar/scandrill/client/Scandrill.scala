package com.nitindhar.scandrill.client

import com.nitindhar.scandrill.models.EmailPacket._
import com.nitindhar.scandrill.models._
import com.nitindhar.scandrill.utils.{ScandrillConfig, JsonService}
import play.api.libs.json.Json.{fromJson, toJson}

import scala.concurrent.Future

import scala.concurrent.ExecutionContext.Implicits.global

trait Scandrill {

  def sendMessage(template: String, subject: String, to: List[String]): Future[Either[ScandrillException, List[ScandrillResponse]]]

}

object Scandrill {

  def apply(apiUrl: String, apiKey: String, apiDefaultFromEmail: String, apiDefaultFromName: String) = new ScaldrillClient {
    override val url: String = apiUrl
    override val key: String = apiKey
    override val defaultFromEmail: String = apiDefaultFromEmail
    override val defaultFromName: String = apiDefaultFromName
  }

}

trait ScaldrillClient extends Scandrill with ScandrillConfig with JsonService {

  def sendMessage(template: String, subject: String, to: List[String]): Future[Either[ScandrillException, List[ScandrillResponse]]] = {
    val uri = s"${url}/messages/send"
    ws(uri).post(toJson(
      EmailPacket(
        key,
        EmailMessage(
          template,
          subject,
          defaultFromEmail,
          defaultFromName,
          to.map(EmailRecipient(_)),
          Map("Reply-To" -> defaultFromEmail)
        )
      )
    )).map(resp => {
      fromJson[List[ScandrillResponse]](resp.json).asOpt.map(Right(_)).getOrElse {
        Left(ScandrillException(s"Unable to fetch response from [${uri}}]"))
      }
    }).recover {
      case e: Exception => Left(ScandrillException(s"Unable to send email [${uri}}] to ${to}"))
    }
  }

}
