package saketh.linkora.localization.domain.repository

import saketh.linkora.localization.domain.model.info.LocalizedInfoDTO

interface LocalizationRepo {
    fun getTranslationsFor(languageCode: String): String
    suspend fun getLocalizedInfo(): LocalizedInfoDTO

    suspend fun getLatestKeysWithDefaultValues(): Map<String, String>
    suspend fun getLatestKeyValuePairsForALanguage(languageCode: String): Result<Map<String, String>>
}