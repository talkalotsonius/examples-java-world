package com.examples.feature.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

//@ConstructorBinding
@ConfigurationProperties(prefix = "feature")
data class ConfigProperties(
    val fileName: String
)
