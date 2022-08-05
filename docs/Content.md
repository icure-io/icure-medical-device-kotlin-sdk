
# Content

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**stringValue** | **kotlin.String** |  |  [optional]
**numberValue** | **kotlin.Double** |  |  [optional]
**booleanValue** | **kotlin.Boolean** |  |  [optional]
**instantValue** | [**java.time.OffsetDateTime**](java.time.OffsetDateTime.md) |  |  [optional]
**fuzzyDateValue** | **kotlin.Long** | Value as date. The format could have a all three (day, month and year) or values on any of these three, whatever is known. |  [optional]
**binaryValue** | [**io.icure.kraken.client.infrastructure.ByteArrayWrapper**](io.icure.kraken.client.infrastructure.ByteArrayWrapper.md) |  |  [optional]
**documentId** | **kotlin.String** | Linked document. |  [optional]
**measureValue** | [**Measure**](Measure.md) |  |  [optional]
**timeSeries** | [**TimeSeries**](TimeSeries.md) |  |  [optional]
**compoundValue** | [**kotlin.collections.List&lt;DataSample&gt;**](DataSample.md) |  |  [optional]
**ratio** | [**kotlin.collections.List&lt;Measure&gt;**](Measure.md) |  |  [optional]
**range** | [**kotlin.collections.List&lt;Measure&gt;**](Measure.md) |  |  [optional]
