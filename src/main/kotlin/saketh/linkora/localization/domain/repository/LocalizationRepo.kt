package saketh.linkora.localization.domain.repository

import saketh.linkora.localization.domain.Language
import saketh.linkora.localization.domain.model.info.LocalizedInfoDTO

interface LocalizationRepo {
    fun getTranslationsFor(language: Language): String
    fun getLocalizedInfo(): LocalizedInfoDTO

    suspend fun getLatestKeysWithDefaultValues(): Map<String, String>
    suspend fun getLatestKeyValuePairsForALanguage(languageCode: String): Result<Map<String, String>>
}