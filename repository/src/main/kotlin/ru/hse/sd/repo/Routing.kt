package ru.hse.sd.repo

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import ru.hse.sd.hwproj.model.Checker
import ru.hse.sd.hwproj.model.Homework
import ru.hse.sd.hwproj.model.Submission

internal fun Application.configureRouting() {
    install(ContentNegotiation) {
        gson()
    }

    val repo = RepositoryImpl()

    routing {
        routeHomework(repo)
        routeSubmission(repo)
        routeChecker(repo)
    }
}

private fun Routing.routeHomework(repo: Repository) {
    get("/homework/{id}") {
        val id = checkNotNull(call.parameters["id"]?.toLongOrNull())
        call.respond(HttpStatusCode.OK, repo.getHomework(id))
    }

    get("/homework") {
        call.respond(HttpStatusCode.OK, repo.getHomeworks())
    }

    post("/homework") {
        val content = call.receive<Homework.Content>()
        call.respond(HttpStatusCode.OK, repo.addHomework(content))
    }
}

private fun Routing.routeSubmission(repo: Repository) {
    get("/submission/{id}") {
        val id = checkNotNull(call.parameters["id"]?.toLongOrNull())
        call.respond(HttpStatusCode.OK, repo.getSubmission(id))
    }

    get("/submission/{id}") {
        val id = checkNotNull(call.parameters["id"]?.toLongOrNull())
        call.respond(HttpStatusCode.OK, repo.getSubmissions(id))
    }

    get("/submission/{id}/result") {
        val id = checkNotNull(call.parameters["id"]?.toLongOrNull())
        call.respond(HttpStatusCode.OK, repo.getSubmissionResult(id))
    }

    post("/submission") {
        val content = call.receive<Submission.Content>()
        call.respond(HttpStatusCode.OK, repo.addSubmission(content))
    }
}

private fun Routing.routeChecker(repo: Repository) {
    post("/checker") {
        val content = call.receive<Checker.Content>()
        call.respond(HttpStatusCode.OK, repo.addChecker(content))
    }
}
