The task has been implemented using the Publisher-Subscriber design pattern. (https://en.wikipedia.org/wiki/Publish%E2%80%93subscribe_pattern)

CLASSES
The content server has been implemented as a singleton class and acts as the broker.
The subscriber acts as a 'regular' user of the platform.
The publisher acts as the gateway to adding new market price feeds. It can be seen as the broker admin.
The message class acts as a market feed record.

SUBSCRIBER
Subscribers are able to subscribe to particular exchanges e.g. GBP_USD.
Subscribers will be notified when a new price feed for an exchange they have subscribed to has been published.

PUBLISHER
Upon the publishing of a new market feed record, a commission is added to the asking price and bid.
A market feed with bid > ask is not valid and cannot be published.

TESTING
Back-End functions can be tested using JUnit.
Postman can be used to test the API endpoints.
Cypress can be used as the front-end testing tool.