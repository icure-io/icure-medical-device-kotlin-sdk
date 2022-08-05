
# User

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **kotlin.String** | the Id of the user. We encourage using either a v4 UUID or a HL7 Id. |  [optional]
**rev** | **kotlin.String** | the revision of the user in the database, used for conflict management / optimistic locking. |  [optional]
**deletionDate** | **kotlin.Long** | the soft delete timestamp. When a user is ”deleted“, this is set to a non null value: the moment of the deletion |  [optional]
**created** | **kotlin.Long** | the creation date of the user (encoded as epoch). |  [optional]
**name** | **kotlin.String** | Last name of the user. This is the official last name that should be used for official administrative purposes. |  [optional]
**properties** | [**kotlin.collections.Set&lt;Property&gt;**](Property.md) | Extra properties for the user. Those properties are typed (see class Property) |
**roles** | **kotlin.collections.Set&lt;kotlin.String&gt;** | Roles assigned to this user |
**login** | **kotlin.String** | Username for this user. We encourage using an email address |  [optional]
**passwordHash** | **kotlin.String** | Hashed version of the password (BCrypt is used for hashing) |  [optional]
**secret** | **kotlin.String** | Secret token used to verify 2fa |  [optional]
**use2fa** | **kotlin.Boolean** | Whether the user has activated two factors authentication |  [optional]
**groupId** | **kotlin.String** | id of the group (practice/hospital) the user is member of |  [optional]
**healthcarePartyId** | **kotlin.String** | Id of the healthcare party if the user is a healthcare party. |  [optional]
**patientId** | **kotlin.String** | Id of the patient if the user is a patient |  [optional]
**deviceId** | **kotlin.String** | Id of the patient if the user is a patient |  [optional]
**autoDelegations** | **kotlin.collections.Map&lt;kotlin.String, kotlin.collections.Set&lt;kotlin.String&gt;&gt;** | Delegations that are automatically generated client side when a new database object is created by this user |
**email** | **kotlin.String** | email address of the user (used for token exchange or password recovery). |  [optional]
**mobilePhone** | **kotlin.String** | mobile phone of the user (used for token exchange or password recovery). |  [optional]
**authenticationTokens** | [**kotlin.collections.Map&lt;kotlin.String, AuthenticationToken&gt;**](AuthenticationToken.md) | Encrypted and time-limited Authentication tokens used for inter-applications authentication |
