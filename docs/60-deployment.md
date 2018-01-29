### Deployment

Ensure that following env variable exists

```bash
export P24_MERCHANT_ID=""
export P24_CRC=""
export P24_RETURN_URL=""
export P24_STATUS_URL="{your_url}/verify-payment"

export ECOMMERCE_EMAIL_SERVER=""                                                                                                                  
export ECOMMERCE_EMAIL_USER=""                                                                                                                  
export ECOMMERCE_EMAIL_PASSWORD=""                                                                                                                      
export ECOMMERCE_EMAIL_PORT="" 
export ECOMMERCE_EMAIL_SENDER="sender@email"

```
```bash
mvn package
java -jar target/digital-commerce-1.0-SNAPSHOT.jar

#visit browser localhost:8080

#when other port required 
java -jar -Dserver.port=9999 target/digital-commerce-1.0-SNAPSHOT.jar

#visit browser localhost:9999
```

