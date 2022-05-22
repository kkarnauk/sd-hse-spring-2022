package ru.hse.sd.webserver

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.html.respondHtml
import io.ktor.http.HttpStatusCode
import io.ktor.request.receiveParameters
import io.ktor.response.respondRedirect
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.webjars.Webjars
import java.sql.Date
import java.sql.Timestamp
import ru.hse.sd.hwproj.model.Homework

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
        route("professor") {
            routeProfessor()
        }
    }
}

private fun Route.routeStudent() {
    get {
        val allHomework = ApiRequest.getAllHomework()
        call.respondHtml(HttpStatusCode.OK) {
            studentPage(allHomework)
        }
    }
}

private fun Route.routeProfessor() {
    get {
        val allHomework = ApiRequest.getAllHomework()
        call.respondHtml(HttpStatusCode.OK) {
            professorPage(allHomework)
        }
    }

    post("/homework") {
        val formParameters = call.receiveParameters()
        val name = checkNotNull(formParameters["name"])
        val statement = checkNotNull(formParameters["statement"])
        val startDate = Timestamp(Date.valueOf(checkNotNull(formParameters["startDate"])).time)
        val endDate = Timestamp(Date.valueOf(checkNotNull(formParameters["endDate"])).time)
        ApiRequest.addHomework(Homework.Content(name, startDate, endDate, statement, 0))
        call.respondRedirect("/professor")
    }

    get("/homework/{id}") {
        val homeworkId = checkNotNull(call.parameters["id"]?.toIntOrNull())
        val homework = ApiRequest.getHomework(homeworkId)
        val submissions = ApiRequest.getSubmissions(homeworkId)
        call.respondHtml(HttpStatusCode.OK) {
            homeworkPage(homework, submissions)
        }
    }
}