# Kong Java Client

[Kong](https://getkong.org/) is a popular Open Source API Gateway. Kong Java Client makes it easy to configure the API Gateway through your code.

## Installation

The artifact is available on Maven Central Repository and be downloaded by adding the following dependency in pom.xml

    <dependency>
        <groupId>com.github.vaibhav-sinha</groupId>
        <artifactId>kong-java-client</artifactId>
        <version>0.1.1-SNAPSHOT</version>
    </dependency>
    
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

### Security Plugins
  * ACL
  * IP Restriction

### Traffic Control Plugins
  * Rate Limiting
  * Request Size Limiting
  * Request Termination

Only those plugins are supported which might need configuration through code. For example, adding rate limit for a new consumer when there is a new signup. Plugins which require one time configuration are not supported.

### Example Usage

To add credentials for a new Consumer for Basic Auth

    kongClient.getBasicAuthService().addCredentials("con-su-mer-id", "username", "password");

    
To add OAuth2 Plugin for an API

    //See: RetrofitApiPluginServiceTest.java
    kongClient.getApiPluginService().addPluginForApi(API_NAME, oauth2Plugin);
    
To add an Application for a Consumer for OAuth2

    //See: RetrofitOAuth2ManageServiceTest.java
    kongClient.getOAuth2ManageService().createConsumerApplication(CONSUMER_ID, 
        new Application(appName, appRedirectUrl, appClientId, appClientSecret));
        
To do the OAuth2 Process (Authorization Code)

    //See: RetrofitOAuth2ProcessServiceTest.java
    kongClient.getOAuth2ProcessService().authorize(API_URI, authorizationRequest);
    kongClient.getOAuth2ProcessService().grantToken(API_URI, grantTokenRequest)

## test

export KONG_DISABLE_HTTPS_CHECK=true
export KONG_ADMIN_URL=http://localhost:32444

mvn test