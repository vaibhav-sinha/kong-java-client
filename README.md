# Kong Java Client

[Kong](https://getkong.org/) is a popular Open Source API Gateway. Kong Java Client makes it easy to configure the API Gateway through your code.

## Usage

    KongClient kongClient = new KongClient("http://localhost:8001");
    Consumer request = new Consumer();
    request.setCustomId("1234-5678-9012");
    Consumer response = kongClient.getConsumerService().createConsumer(request);

Look in the tests to find more examples.

## Supported Plugins

Besides the Admin APIs, Plugin configuration is also supported.

### Authentication Plugins
  * Basic Auth
  * Key Auth
  * HMAC Auth
  * JWT Auth
  * OAuth2
  * LDAP

Support for more plugins is being added.

For example, to add credentials for a new Consumer for Basic Auth

    kongClient.getBasicAuthService().addCredentials("con-su-mer-id", "username", "password");
