package io.icure.md.client.mappers

import io.icure.kraken.client.models.MeasureDto
import io.icure.kraken.client.models.decrypted.ContentDto
import io.icure.kraken.client.models.decrypted.ServiceDto
import io.icure.md.client.models.Content
import io.icure.md.client.models.DataSample
import io.icure.md.client.models.Measure

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
