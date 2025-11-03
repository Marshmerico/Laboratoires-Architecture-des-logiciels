
# TicketResponse

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**ticketId** | **Integer** |  |  [optional]
**title** | **String** |  |  [optional]
**description** | **String** |  |  [optional]
**status** | [**StatusEnum**](#StatusEnum) |  |  [optional]
**priority** | [**PriorityEnum**](#PriorityEnum) |  |  [optional]
**creationDate** | [**OffsetDateTime**](OffsetDateTime.md) |  |  [optional]
**updateDate** | [**OffsetDateTime**](OffsetDateTime.md) |  |  [optional]
**creatorId** | **Integer** |  |  [optional]
**assignedAdminId** | **Integer** | 0 si non assigné |  [optional]


<a name="StatusEnum"></a>
## Enum: StatusEnum
Name | Value
---- | -----
OUVERT | &quot;OUVERT&quot;
ASSIGNE | &quot;ASSIGNE&quot;
VALIDATION | &quot;VALIDATION&quot;
TERMINE | &quot;TERMINE&quot;


<a name="PriorityEnum"></a>
## Enum: PriorityEnum
Name | Value
---- | -----
HAUTE | &quot;haute&quot;
MOYENNE | &quot;moyenne&quot;
BASSE | &quot;basse&quot;



