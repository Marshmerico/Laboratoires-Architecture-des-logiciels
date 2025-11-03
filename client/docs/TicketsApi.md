# TicketsApi

All URIs are relative to *http://localhost:8080/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addComment**](TicketsApi.md#addComment) | **POST** /tickets/{ticketId}/comments | Ajouter un commentaire à un ticket
[**assignTicket**](TicketsApi.md#assignTicket) | **PUT** /tickets/{ticketId}/assign | Assigner un ticket à un admin
[**closeTicket**](TicketsApi.md#closeTicket) | **PUT** /tickets/{ticketId}/close | Fermer un ticket
[**createTicket**](TicketsApi.md#createTicket) | **POST** /tickets | Créer un nouveau ticket
[**getAllTickets**](TicketsApi.md#getAllTickets) | **GET** /tickets | Récupérer tous les tickets (admin uniquement)
[**getTicketById**](TicketsApi.md#getTicketById) | **GET** /tickets/{ticketId} | Récupérer un ticket par ID
[**getUserTickets**](TicketsApi.md#getUserTickets) | **GET** /tickets/user/{userId} | Récupérer les tickets d&#39;un utilisateur
[**updateTicketStatus**](TicketsApi.md#updateTicketStatus) | **PUT** /tickets/{ticketId}/status | Mettre à jour le statut d&#39;un ticket


<a name="addComment"></a>
# **addComment**
> TicketResponse addComment(ticketId, body)

Ajouter un commentaire à un ticket

Permet à un utilisateur d&#39;ajouter un commentaire à son ticket

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.TicketsApi;


TicketsApi apiInstance = new TicketsApi();
Integer ticketId = 56; // Integer | ID du ticket
AddCommentRequest body = new AddCommentRequest(); // AddCommentRequest | Commentaire à ajouter
try {
    TicketResponse result = apiInstance.addComment(ticketId, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TicketsApi#addComment");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ticketId** | **Integer**| ID du ticket |
 **body** | [**AddCommentRequest**](AddCommentRequest.md)| Commentaire à ajouter |

### Return type

[**TicketResponse**](TicketResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="assignTicket"></a>
# **assignTicket**
> TicketResponse assignTicket(ticketId, body)

Assigner un ticket à un admin

Permet d&#39;assigner un ticket à un administrateur

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.TicketsApi;


TicketsApi apiInstance = new TicketsApi();
Integer ticketId = 56; // Integer | ID du ticket
AssignTicketRequest body = new AssignTicketRequest(); // AssignTicketRequest | ID de l'admin à assigner
try {
    TicketResponse result = apiInstance.assignTicket(ticketId, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TicketsApi#assignTicket");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ticketId** | **Integer**| ID du ticket |
 **body** | [**AssignTicketRequest**](AssignTicketRequest.md)| ID de l&#39;admin à assigner |

### Return type

[**TicketResponse**](TicketResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="closeTicket"></a>
# **closeTicket**
> TicketResponse closeTicket(ticketId)

Fermer un ticket

Met le statut du ticket à TERMINE

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.TicketsApi;


TicketsApi apiInstance = new TicketsApi();
Integer ticketId = 56; // Integer | ID du ticket
try {
    TicketResponse result = apiInstance.closeTicket(ticketId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TicketsApi#closeTicket");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ticketId** | **Integer**| ID du ticket |

### Return type

[**TicketResponse**](TicketResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="createTicket"></a>
# **createTicket**
> TicketResponse createTicket(body)

Créer un nouveau ticket

Création d&#39;un ticket par un utilisateur

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.TicketsApi;


TicketsApi apiInstance = new TicketsApi();
CreateTicketRequest body = new CreateTicketRequest(); // CreateTicketRequest | Données du nouveau ticket
try {
    TicketResponse result = apiInstance.createTicket(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TicketsApi#createTicket");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**CreateTicketRequest**](CreateTicketRequest.md)| Données du nouveau ticket |

### Return type

[**TicketResponse**](TicketResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getAllTickets"></a>
# **getAllTickets**
> List&lt;TicketResponse&gt; getAllTickets()

Récupérer tous les tickets (admin uniquement)

Retourne la liste complète des tickets

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.TicketsApi;


TicketsApi apiInstance = new TicketsApi();
try {
    List<TicketResponse> result = apiInstance.getAllTickets();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TicketsApi#getAllTickets");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;TicketResponse&gt;**](TicketResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getTicketById"></a>
# **getTicketById**
> TicketResponse getTicketById(ticketId)

Récupérer un ticket par ID

Retourne les détails d&#39;un ticket spécifique

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.TicketsApi;


TicketsApi apiInstance = new TicketsApi();
Integer ticketId = 56; // Integer | ID du ticket
try {
    TicketResponse result = apiInstance.getTicketById(ticketId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TicketsApi#getTicketById");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ticketId** | **Integer**| ID du ticket |

### Return type

[**TicketResponse**](TicketResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getUserTickets"></a>
# **getUserTickets**
> List&lt;TicketResponse&gt; getUserTickets(userId)

Récupérer les tickets d&#39;un utilisateur

Retourne tous les tickets créés par un utilisateur spécifique

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.TicketsApi;


TicketsApi apiInstance = new TicketsApi();
Integer userId = 56; // Integer | ID de l'utilisateur
try {
    List<TicketResponse> result = apiInstance.getUserTickets(userId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TicketsApi#getUserTickets");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **Integer**| ID de l&#39;utilisateur |

### Return type

[**List&lt;TicketResponse&gt;**](TicketResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateTicketStatus"></a>
# **updateTicketStatus**
> TicketResponse updateTicketStatus(ticketId, body)

Mettre à jour le statut d&#39;un ticket

Change le statut d&#39;un ticket (OUVERT -&gt; ASSIGNE -&gt; VALIDATION -&gt; TERMINE)

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.TicketsApi;


TicketsApi apiInstance = new TicketsApi();
Integer ticketId = 56; // Integer | ID du ticket
UpdateStatusRequest body = new UpdateStatusRequest(); // UpdateStatusRequest | Nouveau statut
try {
    TicketResponse result = apiInstance.updateTicketStatus(ticketId, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TicketsApi#updateTicketStatus");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ticketId** | **Integer**| ID du ticket |
 **body** | [**UpdateStatusRequest**](UpdateStatusRequest.md)| Nouveau statut |

### Return type

[**TicketResponse**](TicketResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

