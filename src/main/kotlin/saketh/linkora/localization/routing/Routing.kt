package saketh.linkora.localization.routing

import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.*
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
        get("/contribute") {
            val latestKeysWithDefaultValuesFromTheApp = localizationRepo.getLatestKeysWithDefaultValues()
            localizationRepo.getLatestKeyValuePairsForALanguage(call.parameters["lang"].toString()).onSuccess {
                call.respondHtml {
                    head {
                        link(
                            rel = "stylesheet",
                            href = "https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap"
                        )
                        style {
                            +"""
    *, *::before, *::after {
        font-family: 'Poppins', sans-serif !important;
    }
    body {
        background-color: #1C1B1F;
        margin: 0;
        padding: 2rem;
    }
    form {
        max-width: 800px;
        margin: 0 auto;
    }
    .form-field {
        margin-bottom: 1.5rem;
    }
    label {
        font-weight: 150;
        display: block;
        margin: 0;
        padding: 0;
        font-size: 1.12rem;
        color: #CAC4D0;
    }
  
    h5 {
     display: block;
     font-size: 2rem;
     color: #D0BCFF;
     margin: 0;
     padding: 0;
     font-weight: 350;
     margin-bottom: 0.75rem;
    }
    
    input {
        width: 100%;
        padding: 0.75rem;
        border: 1px solid rgba(208, 188, 255, 0.3);  /* Softer border color */
        border-radius: 4px;
        font-size: 1rem;
        box-sizing: border-box;
        background-color: #2D2A31;  /* Dark background for inputs */
        color: #E6E1E5;  /* Light text color */
        transition: all 0.3s ease;
    }
    input:focus {
        border-color: #D0BCFF;
        box-shadow: 0 0 0 2px rgba(208, 188, 255, 0.2);
        outline: none;
    }
    input[type="submit"] {
        background-color: #4F378B;
        color: white;
        border: none;
        padding: 0.75rem 1.5rem;
        cursor: pointer;
        margin-top: 1rem;
    }
    input[type="submit"]:hover {
        background-color: #635387;
    }
    #copy-btn {
        position: fixed;
        bottom: 2rem;
        left: 50%;
        transform: translateX(-50%);
        background-color: #4F378B;
        color: white;
        border: none;
        padding: 0.75rem 1.5rem;
        cursor: pointer;
        border-radius: 8px;
        z-index: 1000;
    }
    #copy-btn:hover {
        background-color: #635387;
    }
    """
                        }
                    }
                    body {
                        script {
                            unsafe {
                                raw(
                                    """
       document.addEventListener("DOMContentLoaded", () => {
    async function copyNewJSONToClipboard(text) {
      await navigator.clipboard.writeText(text);
    }
    document.getElementById("copy-btn")
            .addEventListener("click", () => copyNewJSONToClipboard('generatedJson'));
  });
      """.trimIndent()
                                )
                            }
                        }
                        latestKeysWithDefaultValuesFromTheApp.forEach { (key, value) ->
                            div(classes = "form-field") {
                                label {
                                    +key
                                }
                                h5 {
                                    +value
                                }
                                textInput(name = key) {
                                    this.value = it[key] ?: ""
                                }
                                br()
                                br()
                            }
                        }
                        button {
                            id = "copy-btn"
                            +"Copy JSON"
                        }
                        br()
                        br()
                    }
                }
            }.onFailure {
                call.respond(message = it.message as Any)
            }
        }
        get("/keys") {
            call.respond(localizationRepo.getLatestKeysWithDefaultValues())
        }
        Language.entries.forEach { language ->
            get(language.availableLanguageDTO.languageCode) {
                call.respondText(localizationRepo.getTranslationsFor(language))
            }
        }
    }
}

fun text(string: String) {
    println(string)
}