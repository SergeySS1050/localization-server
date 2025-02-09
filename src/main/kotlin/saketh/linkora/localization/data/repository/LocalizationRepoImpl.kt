package saketh.linkora.localization.data.repository

import kotlinx.serialization.json.Json
import saketh.linkora.localization.domain.Language
import saketh.linkora.localization.domain.model.info.LocalizedInfoDTO
import saketh.linkora.localization.domain.repository.LocalizationRepo

class LocalizationRepoImpl : LocalizationRepo {
    private fun retrieveRawFileString(languageCode: String): String {
        val file = this::class.java.getResourceAsStream("/raw/$languageCode.json")
        return file.use { it?.bufferedReader()?.readText().toString() }
    }

    override fun getTranslationsFor(language: Language): String {
        return retrieveRawFileString(languageCode = language.availableLanguageDTO.languageCode)
    }

    override fun getLocalizedInfo(): LocalizedInfoDTO {
        return LocalizedInfoDTO(
            totalAvailableLanguages = Language.entries.size,
            availableLanguages = Language.entries.map {
                val localizedStringsCount =
                    Json.decodeFromString<Map<String, String>>(retrieveRawFileString(it.availableLanguageDTO.languageCode)).size
                it.availableLanguageDTO.copy(localizedStringsCount = localizedStringsCount)
            },
            totalStrings = 328,
            lastUpdatedOn = "09-02-2025::11:06 PM IST"
        )
    }

}