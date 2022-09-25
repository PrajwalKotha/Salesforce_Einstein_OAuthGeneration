# Salesforce Einstein OAuth Token Generation

Instead of going to this page and generating Einstein Platform Servicestoken, by using this API we can generate the token by providing RSA Key, expiration time and Email ID with which the RSA token is generated.
This is a module which can be use in our Real life projects
## Exisiting Process By Salesforce team

![image](https://user-images.githubusercontent.com/28332441/192156080-17b98524-1783-4b5f-a21e-bab48f3c1bb3.png)

## Process

### After importing the API into your studio

Deploy it and send the request based on the below details

```
Request Method:   POST
Body          :   multipart/form-data
RSAprivateKey :   RSA keyfile recieved from Salesforce to your registered mail
expTime       :   number in Min (max 30days)
userId        :   email ID to which the RSA key file recieved
```

