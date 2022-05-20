package ru.hse.sd.webserver

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.request.*
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
        ApiRequest.addHomework(Homework.Content(name, startDate, endDate, statement, RunnerTask(0)))
        call.respondRedirect("/professor")
    }

    get("/homework/{id}") {
        val homeworkId = checkNotNull(call.parameters["id"]?.toLongOrNull())
        val homework = ApiRequest.getHomework(homeworkId)
        val submissions = ApiRequest.getSubmissions(homeworkId)
        call.respondHtml(HttpStatusCode.OK) {
            homeworkPage(homework, submissions)
        }
    }
}