package saketh.linkora.localization.domain.model.info

import kotlinx.serialization.Serializable

@Serializable
data class AvailableLanguageDTO(
    val localizedName: String,
    val languageCode: String,
    val localizedStringsCount: Int = 0
)
