# Scandrill

Scandrill is an async Scala client for the [MandrillApp API](https://mandrillapp.com/api/docs/).

## Getting Scandrill

The release versions of Scandrill are built against Scala 2.11.x

If you're using SBT, add the following line to your build file:

```scala
libraryDependencies += "com.nitindhar" %% "scandrill" % "0.1.0"
```

For Maven and other build tools, you can visit [search.maven.org](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.nitindhar%22).

To get sample configurations, click on the version of the module you are interested in.
You can also find direct download links at the bottom of that page. Choose the file ending in `x.x.x.jar`.

## Quick Start

```scala
import com.nitindhar.scandrill.client._

val scandrill = Scandrill("https://mandrillapp.com/api/1.0", API-KEY, DEFAULT-FROM-EMAIL, DEFAULT-FROM-NAME) // build the client
```

Using the Scandrill client

```scala
import com.nitindhar.scandrill.client._
import com.nitindhar.scandrill.models._

// sendMessage(template: String, subject: String, to: List[String]): Future[Either[ScandrillException, List[ScandrillResponse]]]
val template = render("welcome-email-template") // render your template to a string using whichever way you prefer
val subject = "Welcome Friend!"
val to = List("hello@world.com")

scandrill.sendMessage(template, subject, to)
```

Handling the response from `sendMessage`

```scala
scandrill.sendMessage(...).map {
  case Left(e) => Logger.info(s"Error occured: ${e.getMessage}")
  case Right(resp) => Logger.info(s"Successfully sent ${resp.size} emails!")
}
```

## Resources

[Getting started with Mandril App](https://mandrill.zendesk.com/hc/en-us/categories/200277237)

The `sendMessage` endpoint is part of the [Messages calls section](https://mandrillapp.com/api/docs/messages.JSON.html)

[Check here](https://mandrillapp.com/docs/integrations.html) to view the existing Mandrill App integrations.


## Contributing

- Create new examples
- Port/Write test cases
- Create documentation
  - Class level documentation for each class.
  - Brief method documentation welcome
- Review code base for consistency problems
- Review class hierarchy

## LICENSE

[MIT LICENSE](https://github.com/nitindhar7/scandrill/blob/master/LICENSE)
