# AdminsApi

All URIs are relative to *http://localhost:8080/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getAdminAssignedTickets**](AdminsApi.md#getAdminAssignedTickets) | **GET** /admins/{adminId}/tickets | Récupérer les tickets assignés à un admin
[**getAllAdmins**](AdminsApi.md#getAllAdmins) | **GET** /admins | Récupérer tous les administrateurs


<a name="getAdminAssignedTickets"></a>
# **getAdminAssignedTickets**
> List&lt;TicketResponse&gt; getAdminAssignedTickets(adminId)

Récupérer les tickets assignés à un admin

Retourne tous les tickets assignés à un administrateur

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AdminsApi;


AdminsApi apiInstance = new AdminsApi();
Integer adminId = 56; // Integer | ID de l'administrateur
try {
    List<TicketResponse> result = apiInstance.getAdminAssignedTickets(adminId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AdminsApi#getAdminAssignedTickets");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **adminId** | **Integer**| ID de l&#39;administrateur |

### Return type

[**List&lt;TicketResponse&gt;**](TicketResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getAllAdmins"></a>
# **getAllAdmins**
> List&lt;AdminResponse&gt; getAllAdmins()

Récupérer tous les administrateurs

Liste tous les administrateurs du système

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AdminsApi;


AdminsApi apiInstance = new AdminsApi();
try {
    List<AdminResponse> result = apiInstance.getAllAdmins();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AdminsApi#getAllAdmins");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;AdminResponse&gt;**](AdminResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

