package com.examples.feature.service.model

import com.examples.feature.datasource.model.Feature
import java.util.UUID

data class FeatureData(
    val id: UUID?,
    val timestamp: Long?,
    val beginViewingDate: Long?,
    val endViewingDate: Long?,
    val missionName: String?
) {
    companion object ModelMapper {
        fun Feature.toFeatureData() = FeatureData(
            id = this.properties?.id,
            timestamp = this.properties?.timestamp,
            beginViewingDate = this.properties?.acquisition?.beginViewingDate,
            endViewingDate = this.properties?.acquisition?.endViewingDate,
            missionName = this.properties?.acquisition?.missionName
        )
    }
}
