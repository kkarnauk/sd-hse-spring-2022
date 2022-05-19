package ru.hse.sd.webserver

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.webjars.*
import ru.hse.sd.hwproj.model.Homework
import ru.hse.sd.hwproj.model.RunnerTask
import java.sql.Date
import java.sql.Timestamp

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
        val allHomework = getAllHomework()
        call.respondHtml(HttpStatusCode.OK) {
            studentPage(allHomework)
        }
    }
}

private fun Route.routeProfessor() {
    get {
        val allHomework = getAllHomework()
        call.respondHtml(HttpStatusCode.OK) {
            professorPage(allHomework)
        }
    }
    get("/addHomework") {
        val name = checkNotNull(call.request.queryParameters["name"])
        val statement = checkNotNull(call.request.queryParameters["statement"])
        val startDate = Timestamp(Date.valueOf(checkNotNull(call.request.queryParameters["startDate"])).time)
        val endDate = Timestamp(Date.valueOf(checkNotNull(call.request.queryParameters["endDate"])).time)
        addHomework(Homework.Content(name, startDate, endDate, statement, RunnerTask(0)))
        call.respondRedirect("/professor")
    }
}