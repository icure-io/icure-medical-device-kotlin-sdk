package io.icure.md.client.mappers

import io.icure.kraken.client.models.TimeSeriesDto
import io.icure.md.client.models.TimeSeries

fun TimeSeriesDto.toTimeSeries() = TimeSeries(
    fields = this.fields,
    samples = this.samples,
    min = this.min,
    max = this.max,
    mean = this.mean,
    median = this.median,
    variance = this.variance
)

fun TimeSeries.toTimeSeriesDto() = TimeSeriesDto(
    fields = this.fields,
    samples = this.samples,
    min = this.min,
    max = this.max,
    mean = this.mean,
    median = this.median,
    variance = this.variance
)
