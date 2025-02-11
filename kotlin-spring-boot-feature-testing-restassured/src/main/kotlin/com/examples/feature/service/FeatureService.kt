package com.examples.feature.service

import com.examples.feature.datasource.FeatureJsonObjectMapper
import com.examples.feature.exception.FeatureNotFoundException
import com.examples.feature.exception.ImageNotFoundException
import com.examples.feature.service.model.FeatureData
import com.examples.feature.service.model.FeatureData.ModelMapper.toFeatureData

import org.springframework.stereotype.Service
//import org.springframework.util.Base64Utils

import java.util.UUID
import java.util.Base64

@Service
class FeatureService(private val featureJsonObjectMapper: FeatureJsonObjectMapper) {

    fun findFeatures(): List<FeatureData> = retrieveFeature().map { it.toFeatureData() }
        .ifEmpty { throw FeatureNotFoundException("Feature list is empty") }

    fun findImageByFeatureId(featureId: UUID): ByteArray {
        val quickLook = retrieveFeature().filter { it.properties?.id == featureId }
            .map { it.properties?.quicklook }.firstOrNull()
            ?: throw ImageNotFoundException("Image is not found for the feature id: $featureId")

        return quickLook.convertToByteArray()
    }

    private fun retrieveFeature() = featureJsonObjectMapper.parseGeoJson().flatMap { it.features }

    private fun String.convertToByteArray() = Base64.getDecoder().decode(this)
}
