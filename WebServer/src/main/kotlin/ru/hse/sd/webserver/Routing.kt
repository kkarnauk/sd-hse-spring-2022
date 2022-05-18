package ru.hse.sd.webserver

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.routing.*
import io.ktor.webjars.*
import kotlinx.html.*
import java.text.SimpleDateFormat

internal fun Application.configureRouting() {
    install(ContentNegotiation) {
        gson()
    }
    install(Webjars) {
        path = "assets"
    }

    routing {
        route("student") {
            routeStudent()
        }
    }
}

private val dateFormat = SimpleDateFormat("d MMM yyyy")

private fun Route.routeStudent() {
    get() {
        val allHomework = getAllHomework()
        call.respondHtml(HttpStatusCode.OK) {
            head {
                title {
                    +"Задания"
                }
                link {
                    rel = "stylesheet"
                    href = "/assets/bootstrap/bootstrap.css"
                }
            }
            body {
                div(classes = "row row-cols-1 row-cols-md-4 g-4") {
                    allHomework.map {
                        div(classes = "col") {
                            div(classes = "card") {
                                div(classes = "card-body") {
                                    h5(classes = "card-title") {
                                        text(it.content.name)
                                    }
                                    h6(classes = "card-subtitle mb-2 text-muted") {
                                        val startDate = dateFormat.format(it.content.startDate)
                                        val endDate = dateFormat.format(it.content.endDate)
                                        text("$startDate — $endDate")
                                    }
                                    p(classes = "card-text") {
                                        text(it.content.statement)
                                    }
                                    a(classes = "btn btn-primary") {
                                        href = "#"
                                        text("Сдать")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}