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
In the config file nothing is really important, beside the DNS.

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
> cp keystore.jks ../dropwizard/

> cd ../dropwizard/

> nano src/dist/config.yml

Now go pack to the "cert" folder and copy the content of your certificate.pem.
Now go to developer.amazon.com and open you skill. On the left side click "SSL Certificate".
Select "I will upload a self-signed certificate in X.509 format." and paste it in the field below.

Run the application locally
---------------------------
Go to the project root directory

Run the application
> ./gradlew run

or if you have gradle installed
> gradle run

Test the server by calling: [https://0.0.0.0:8443/](https://0.0.0.0:8443/)

If you set up the dyndns and the port-forwarding correctly, you should be able to see incoming requests in the console.

For development IntelliJ IDEA is recommended.


Publish as Lambda function
--------------------------

Deploying your code to AWS Lambda has many advantages (no server maintenance, costs, etc.). The gradle tasks "shadowJar" will create a JAR including all dependencies that you specified in the alexa project.
The settings for the AWS Lambda service are:
```
Runtime: Java 8
Handler: de.hennroja.alexaskill.Handler
```

Generate the jar by calling
> ./gradlew shadowJar

Find the jar file in
> alexa/build/libs/

Now simply upload the jar and trigger a last test.

Keep in mind that you have to change the endpoint address from HTTPS + your dyndns to the lambda function.
Alternatively you can create a new skill and copy intent schema + utterances.