package io.icure.md.client.mappers

import io.icure.kraken.client.models.MeasureDto
import io.icure.kraken.client.models.decrypted.ContentDto
import io.icure.kraken.client.models.decrypted.ServiceDto
import io.icure.md.client.models.Content
import io.icure.md.client.models.DataSample
import io.icure.md.client.models.Measure
import java.util.*

fun ServiceDto.toDataSample(): DataSample = DataSample(
    id = this.id,
    identifier = this.identifier.map { it.toIdentifier() },
    content = this.content.mapValues { it.value.toContent() },
    qualifiedLinks = this.qualifiedLinks.map { (k,v) -> k.name to v}.toMap(),
    codes = this.codes.map { it.toCodingReference() },
    labels = this.tags.map { it.toCodingReference() },
    transactionId = this.transactionId,
    batchId = this.contactId,
    healthElementsIds = this.healthElementsIds,
    canvasesIds = this.formIds,
    index = this.index,
    valueDate = this.valueDate,
    openingDate = this.openingDate,
    closingDate = this.closingDate,
    created = this.created,
    modified = this.modified,
    endOfLife = this.endOfLife,
    author = this.author,
    responsible = this.responsible,
    comment = this.comment
)

private fun ContentDto.toContent(): Content = Content(
    stringValue = this.stringValue,
    numberValue = this.numberValue,
    booleanValue = this.booleanValue,
    instantValue = this.instantValue,
    fuzzyDateValue = this.fuzzyDateValue,
    binaryValue = this.binaryValue,
    documentId = this.documentId,
    measureValue = this.measureValue?.toMeasure(),
    timeSeries = this.timeSeries?.toTimeSeries(),
    compoundValue = this.compoundValue?.map { it.toDataSample() },
    ratio = this.ratio?.map { it.toMeasure() },
    range = this.ratio?.map { it.toMeasure() },
)

private fun MeasureDto.toMeasure() = Measure(
    `value` = this.`value`,
    min = this.min,
    max = this.max,
    ref = this.ref,
    severity = this.severity,
    severityCode = this.severityCode,
    evolution = this.evolution,
    unit = this.unit,
    unitCodes = this.unitCodes?.map { it.toCodingReference() }?.toSet(),
    comment = this.comment,
    comparator = this.comparator,
)

fun DataSample.toServiceDto(dataSampleId: String = UUID.randomUUID().toString()): ServiceDto = ServiceDto(
    id = dataSampleId,
    identifier = this.identifier.map { it.toIdentifierDto() },
    content = this.content.mapValues { it.value.toContentDto() },
    qualifiedLinks = this.qualifiedLinks.map { (k, v) -> ServiceDto.LinkQualification.valueOf(k) to v }.toMap(),
    codes = this.codes.map { it.toCodeStubDto() },
    tags = this.labels.map { it.toCodeStubDto() },
    transactionId = this.transactionId,
    contactId = this.batchId,
    healthElementsIds = this.healthElementsIds,
    formIds = this.canvasesIds,
    index = this.index,
    valueDate = this.valueDate,
    openingDate = this.openingDate,
    closingDate = this.closingDate,
    created = this.created,
    modified = this.modified,
    endOfLife = this.endOfLife,
    author = this.author,
    responsible = this.responsible,
    comment = this.comment
)

private fun Content.toContentDto(): ContentDto = ContentDto(
    stringValue = this.stringValue,
    numberValue = this.numberValue,
    booleanValue = this.booleanValue,
    instantValue = this.instantValue,
    fuzzyDateValue = this.fuzzyDateValue,
    binaryValue = this.binaryValue,
    documentId = this.documentId,
    measureValue = this.measureValue?.toMeasureDto(),
    timeSeries = this.timeSeries?.toTimeSeriesDto(),
    compoundValue = this.compoundValue?.map { it.toServiceDto() },
    ratio = this.ratio?.map { it.toMeasureDto() },
    range = this.ratio?.map { it.toMeasureDto() },
)

private fun Measure.toMeasureDto() = MeasureDto(
    `value` = this.`value`,
    min = this.min,
    max = this.max,
    ref = this.ref,
    severity = this.severity,
    severityCode = this.severityCode,
    evolution = this.evolution,
    unit = this.unit,
    unitCodes = this.unitCodes?.map { it.toCodeStubDto() }?.toSet(),
    comment = this.comment,
    comparator = this.comparator,
)
