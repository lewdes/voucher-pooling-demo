# voucher-pooling-demo
Voucher Pooling Demo

This is a simple demo of voucher pooling restful api to redeem voucher code. It is implemented by following stacks
- SpringBoot
- Spring Data JPA
- Hibernate
- H2


You may use eclipse/intellij to setup the project and start to run it locally.

This demo is used to show that redeem process.

After started the application, you may use postman to test it by using below url

- To get all vouchers details with GET Method
>localhost:8080/vouchers/

- To redeem a voucher with POST Method, get the voucher code via first method
>localhost:8080/vouchers/redeem

sample payload
```json
{
	"voucherCode": "6a20e14ee8a64995b816753b277c69d3",
	"email": "ali@axiata.com"
	
}
```
