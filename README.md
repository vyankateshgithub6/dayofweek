# Welcome

This sample application demonstrates the implementation of a FusionFabric.cloud SPI. To learn more about SPIs, see [FusionCreator Documentation](https://developer.fusionfabric.cloud/documentation/spi-implementation). 

**To run this sample**

> You must have a recent installation of [Java](https://www.java.com/en/) on your machine. Also, an OAuth2 client is required, such as [Postman](https://www.postman.com/). 


1. Clone the current project.
2. Run this sample app with Maven:

```sh
mvn spring-boot:run
```
3. (Optional) If you don't have Maven installed on your machine, run this sample with **mvnw**: 
```sh
mvnw spring-boot:run
```
The sample application is running on http://localhost:9000

**To call the endpoint implemented by this sample**

1. Register an application on [**Fusion**Fabric.cloud Developer Portal](https://developer.fusionfabric.cloud), and include the [Sample SPI](https://developer.fusionfabric.cloud/api/sample-spi-v1-0504c686-15d4-4002-bc11-8c1791807fa4/docs) SPI in the **SPIs** tab. Use any valid URLs for the *SPI Base URL*, as you will not use them during working with this sample.
2. Generate a secret key for the B2B channel. See [FusionCreator Documentation](https://developer.fusionfabric.cloud/documentation/join-my-dashboard#secret-key) for details.
3. Download the Sample SPI Postman collection from the **Actions** menu on [Sample SPI reference documentation page](https://developer.fusionfabric.cloud/api/sample-spi-v1-0504c686-15d4-4002-bc11-8c1791807fa4/docs).
4. In Postman, import the collection file previously downloaded.
5. Edit the Postman collection **Variables** and provide the values for:
   + `client_id` and `client_secret` - the client ID and the secret key generated at step 2
   + `sample-spi-url` - use http://localhost:9000. This is the base URL of this sample application running locally on your machine.  
6. Call the `POST` **APIM fetch token** endpoint. Generate an access token to authorize the call using Oauth2 - Client Credentials. The call stores the access token in the `APIM_token` collection variable.
7. Call the `POST` **Call sample-spi with token** endpoint. Alternatively, edit the request body and update the `date` to any valid date. This will call the implementation of the [`POST` `/day-of-week`](https://developer.fusionfabric.cloud/api/sample-spi-v1-0504c686-15d4-4002-bc11-8c1791807fa4/docs#operation/dayOfWeek) endpoint. 

> Both **POST Call sample-spi** methods reference the same endpoint. The difference between them is: 
>
>   + The **with token** one uses the access token stored in the `APIM_token` collection variable, and you receive a `200` response when you call it.
>   + The **without token** one does not store the access token in the `APIM_token` collection variable, and although you reach the target, you receive a `403 Forbidden` response, which is the expected response.

This sample application is released under the MIT License. See [LICENSE](LICENSE) for details.