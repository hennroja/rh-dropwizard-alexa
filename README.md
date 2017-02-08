# rh-dropwizard-alexa
Alexa with Dropwizard for Java Speechlets

Preperation for Development
===========================

To test your application with the Alexa test environment provided by devlopers.amazon.com, you can redirect all the request to your local machine. Therefore you 
need a ssl certificate, portforwarding from you router to your machine (443 --> 8443) and optionally a dyndns.


Create Certificate
------------------

Create a new folder ("cert").
> mkdir cert

> cd cert

Generate an RSA private key.
> openssl genrsa -out private-key.pem 2048

Create a new file called configuration.cnf
> touch configuration.cnf

Copy, paste and adjust the following to the configuration.cnf file:
```
[req]
distinguished_name = req_distinguished_name
x509_extensions = v3_req
prompt = no

[req_distinguished_name]
C = DE
ST = B
L = Berlin
O = MyCompanyIsCool
CN = My Awesome Skill

[v3_req]
keyUsage = keyEncipherment, dataEncipherment
extendedKeyUsage = serverAuth
subjectAltName = @subject_alternate_names

[subject_alternate_names]
DNS.1 = home.dyndns.example.com
```

> openssl req -new -x509 -days 365 
             -key private-key.pem 
             -config configuration.cnf 
             -out certificate.pem

> openssl pkcs12 -export -in certificate.pem -inkey private-key.pem -out pkcs.p12 -name cert -password pass:password1

Generate the keystore with keytool:

> keytool -deststorepass password1 -importkeystore -destkeypass password1 
  -destkeystore keystore.jks -srckeystore pkcs.p12 -srcstoretype 
  PKCS12 -srcstorepass password1 -alias cert


Put the keystore.jks into project root and set password in src/dist/config.yml
> cp keystore.jks ../

> cd ..

> nano src/dist/config.yml


Run the application
-------------------

Run the application
> ./gradlew run

or if you have gradle installed
> gradle run

Test the server by calling: [https://0.0.0.0:8443/](https://0.0.0.0:8443/)
