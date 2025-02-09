package saketh.linkora.localization.domain.model.info

import kotlinx.serialization.Serializable

@Serializable
data class LocalizedInfoDTO(
    val totalAvailableLanguages: Int,
    val availableLanguages: List<AvailableLanguageDTO>,
    val totalStrings: Int,
    val lastUpdatedOn: String
)
