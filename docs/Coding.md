
# Coding

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **kotlin.String** | the Id of the coding. We encourage using either a v4 UUID or a HL7 Id. |  [optional]
**rev** | **kotlin.String** | the revision of the coding in the database, used for conflict management / optimistic locking. |  [optional]
**type** | **kotlin.String** |  |  [optional]
**code** | **kotlin.String** |  |  [optional]
**version** | **kotlin.String** | Must be lexicographically searchable |  [optional]
**description** | **kotlin.collections.Map&lt;kotlin.String, kotlin.String&gt;** | Description (ex: {en: Rheumatic Aortic Stenosis, fr: Sténose rhumatoïde de l&#39;Aorte}) |  [optional]
**qualifiedLinks** | **kotlin.collections.Map&lt;kotlin.String, kotlin.collections.List&lt;kotlin.String&gt;&gt;** | Links towards related codes |
**searchTerms** | **kotlin.collections.Map&lt;kotlin.String, kotlin.collections.Set&lt;kotlin.String&gt;&gt;** | Extra search terms/ language |
