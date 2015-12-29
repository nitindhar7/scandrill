package com.nitindhar.scandrill.client

import com.nitindhar.scandrill.models.ScandrillException
import org.scalatest._

import scala.concurrent.ExecutionContext.Implicits.global

/*

Test:
- client generates properly
- EmailPacket gets created properly
- EmailMessage gets created properly
- EmailRecipient gets created properly
- jsonWS is mocked and tested
- test successful conversion to SendResponse
- test failed response from mandrill
- 

*/

class ScandrillTest extends FlatSpec with Matchers {

  def create = {
    val scandrill = Scandrill("url", "ABCDEF", "hello@nitindhar.com", "Hello")
    scandrill.sendMessage("", "", List("")).map {
      case Left(ScandrillException(msg)) => "oh noeessss"
      case Right(response) => response.size
    }
  }

}
