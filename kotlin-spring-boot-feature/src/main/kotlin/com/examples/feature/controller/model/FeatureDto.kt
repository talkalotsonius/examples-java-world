package com.examples.feature.controller.model

import com.examples.feature.service.model.FeatureData

import java.util.UUID

data class FeatureDto(
    val id: UUID?,
    val timestamp: Long?,
    val beginViewingDate: Long?,
    val endViewingDate: Long?,
    val missionName: String?
) {
    companion object ModelMapper {
        fun FeatureData.toFeatureDto() = FeatureDto(
            id = this.id,
            timestamp = this.timestamp,
            beginViewingDate = this.beginViewingDate,
            endViewingDate = this.endViewingDate,
            missionName = this.missionName
        )
    }
}
