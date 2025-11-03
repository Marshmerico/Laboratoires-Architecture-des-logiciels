# UsersApi

All URIs are relative to *http://localhost:8080/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getAllUsers**](UsersApi.md#getAllUsers) | **GET** /users | Récupérer tous les utilisateurs


<a name="getAllUsers"></a>
# **getAllUsers**
> List&lt;UserResponse&gt; getAllUsers()

Récupérer tous les utilisateurs

Liste tous les utilisateurs du système

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UsersApi;


UsersApi apiInstance = new UsersApi();
try {
    List<UserResponse> result = apiInstance.getAllUsers();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UsersApi#getAllUsers");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;UserResponse&gt;**](UserResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

