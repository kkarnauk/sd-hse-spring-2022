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
        route("/homework") {
            routeHomework(repo)
        }
        route("/submission") {
            routeSubmission(repo)
        }
        route("/check") {
            routeChecker(repo)
        }
    }
}

private fun Route.routeHomework(repo: Repository) {
    get("/{id}") {
        val id = checkNotNull(call.parameters["id"]?.toLongOrNull())
        call.respond(HttpStatusCode.OK, repo.getHomework(id))
    }

    get {
        call.respond(HttpStatusCode.OK, repo.getHomeworks())
    }

    post {
        val content = call.receive<Homework.Content>()
        call.respond(HttpStatusCode.OK, repo.addHomework(content))
    }
}

private fun Route.routeSubmission(repo: Repository) {
    get("/{id}") {
        val id = checkNotNull(call.parameters["id"]?.toLongOrNull())
        call.respond(HttpStatusCode.OK, repo.getSubmission(id))
    }

    get("/{id}") {
        val id = checkNotNull(call.parameters["id"]?.toLongOrNull())
        call.respond(HttpStatusCode.OK, repo.getSubmissions(id))
    }

    get("/{id}/result") {
        val id = checkNotNull(call.parameters["id"]?.toLongOrNull())
        call.respond(HttpStatusCode.OK, repo.getSubmissionResult(id))
    }

    post {
        val content = call.receive<Submission.Content>()
        call.respond(HttpStatusCode.OK, repo.addSubmission(content))
    }

    post("/{id}/result") {
        val id = checkNotNull(call.parameters["id"]?.toLongOrNull())
        val result = call.receive<Submission.Result>()
        call.respond(HttpStatusCode.OK, repo.addSubmissionResult(id, result))
    }
}

private fun Route.routeChecker(repo: Repository) {
    get("/{id}") {
        val id = checkNotNull(call.parameters["id"]?.toLongOrNull())
        call.respond(HttpStatusCode.OK, repo.getChecker(id))
    }

    post {
        val content = call.receive<Checker.Content>()
        call.respond(HttpStatusCode.OK, repo.addChecker(content))
    }
}
