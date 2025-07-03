package saketh.linkora.localization.domain.model.info

import kotlinx.serialization.Serializable

@Serializable
data class LocalizedInfoDTO(
    val availableLanguages: List<AvailableLanguageDTO>,
    val totalDefaultValues: Int,
    val lastUpdatedOn: String,
    val totalAvailableLanguages: Int = availableLanguages.size
)
