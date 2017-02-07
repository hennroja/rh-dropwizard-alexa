# rh-dropwizard-alexa
Alexa with Dropwizard for Java Speechlets

Preperation for Development
===========================

To test your application with the Alexa test environment provided by devlopers.amazon.com, you can redirect all the request to your local machine. Therefore you 
need a ssl certificate, portforwarding from you router to your machine (443 --> 8443) and optionally a dyndns.


Certificate
-----------

Create a new folder ("cert").
> mkdir cert

> cd cert

Generate an RSA private key.
> openssl genrsa -out private-key.pem 2048

> openssl req -new -x509 -days 365 \
             -key private-key.pem \
             -config configuration.cnf \
             -out certificate.pem

> openssl pkcs12 -export -in certificate.pem -inkey private-key.pem -out pkcs.p12 -name cert -password pass:password1


> keytool -deststorepass password1 -importkeystore -destkeypass password1 \
  -destkeystore keystore.jks -srckeystore pkcs.p12 -srcstoretype \
  PKCS12 -srcstorepass password1 -alias cert

Put the keystore.jks into project root and set password in src/dist/config.yml
