# language-detector
Language detector service automatically detects language of a given phrase

The service itself uses [Language Detection Library for Java](https://github.com/optimaize/language-detector) and wraps this RESTfull APIs.
The list of exposed APIs is documented with Swagger and available at http://localhost:8080/swagger-ui.html after the service is started.

When the service is given a short phrase, e.g. "a phrase", there is a high chance that the langauge will be detected wrongly.
So either provide longer phrases or give a hint to service specifying a list of potential languages, e.g. `/v0/language/?phrase=short phras&hint=en&hint=ru`.
