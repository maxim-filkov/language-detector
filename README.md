# language-detector
Language detector service automatically detects language of a given phrase.

The service itself uses [Language Detection Library for Java](https://github.com/optimaize/language-detector) and wraps this with RESTful APIs.
The list of exposed APIs is documented with Swagger and available at http://localhost:8080/swagger-ui.html after the service is started.

When you send a short phrase to detect its language, e.g. "a phrase", there is a high chance that the language will be detected wrongly.
So, either provide longer phrases, or give the service a hint by specifying a list of potential languages, e.g. `/v0/language/?phrase=short phras&hint=en&hint=ru`.
