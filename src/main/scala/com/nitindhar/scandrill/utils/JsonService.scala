package com.nitindhar.scandrill.utils

import com.ning.http.client.AsyncHttpClientConfig.Builder
import play.api.libs.ws.WSRequestHolder
import play.api.libs.ws.ning.NingWSClient

private[scandrill] trait JsonService {

  def ws(uri: String): WSRequestHolder = {
    new NingWSClient(new Builder().build)
      .url(s"${uri}.json")
      .withHeaders(
        "Content-Type" -> "application/json",
        "Data-Type" -> "application/json"
      )
  }

}
