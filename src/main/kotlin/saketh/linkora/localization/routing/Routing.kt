package saketh.linkora.localization.routing

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import saketh.linkora.localization.domain.Language
import saketh.linkora.localization.domain.repository.LocalizationRepo

fun Application.configureRouting(localizationRepo: LocalizationRepo) {
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

        Language.entries.forEach { language ->
            get(language.availableLanguageDTO.languageCode) {
                call.respondText(localizationRepo.getTranslationsFor(language))
            }
        }
    }
}