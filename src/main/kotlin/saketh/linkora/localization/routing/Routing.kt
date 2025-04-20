package saketh.linkora.localization.routing

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import saketh.linkora.localization.domain.Language
import saketh.linkora.localization.domain.repository.LocalizationRepo

fun Application.configureRouting(localizationRepo: LocalizationRepo) {
    val json = Json { isLenient = true }
    routing {
        get("/") {
            call.respondRedirect("https://github.com/sakethpathike/Linkora")
        }

        get("/info") {
            call.respond(
                Json.encodeToString(
                    localizationRepo.getLocalizedInfo()
                )
            )
        }
        get("/keys") {
            HttpClient(CIO).use { httpClient ->
                httpClient.get("https://raw.githubusercontent.com/LinkoraApp/Linkora/master/composeApp/src/commonMain/kotlin/com/sakethh/linkora/common/Localization.kt")
                    .bodyAsText().substringAfter("/***** THE SACRED SCRAPING RITUAL BEGINS *****/").substringBefore(
                        "/*****  SCRAPING RITUAL COMPLETE  *****/"
                    ).trimIndent().split("),").map {
                        it.trim().run {
                            if (this[this.lastIndex] == ')') this.substring(0, this.lastIndex) else this
                        }
                    }.associate {
                        it.substringBefore("(") to json.decodeFromString<String>(
                            it.substringAfter("(").substringAfter("defaultValue").substringAfter("=").trim()
                                .replace("\\'", "'")
                        ).replace("\${LinkoraPlaceHolder.First.value}", "{#LINKORA_PLACE_HOLDER_1#}")
                            .replace("\${LinkoraPlaceHolder.Second.value}", "{#LINKORA_PLACE_HOLDER_2#}")
                    }.let {
                        call.respond(it)
                    }
            }
        }
        Language.entries.forEach { language ->
            get(language.availableLanguageDTO.languageCode) {
                call.respondText(localizationRepo.getTranslationsFor(language))
            }
        }
    }
}