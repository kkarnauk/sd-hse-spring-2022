package ru.hse.sd.webserver.api

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
import ru.hse.sd.repo.Repository
import ru.hse.sd.repo.RepositoryFacade

internal fun Application.configureRouting() {
    install(ContentNegotiation) {
        gson()
    }

    val repo = RepositoryFacade()

    routing {
        route("api/student") {
            routeStudentApi(repo)
        }
        route("api/submission") {
            routeProfessorApi(repo)
        }
    }
}

private fun Route.routeStudentApi(repo: Repository) {
    get("/homework/{id}") {
        val id = checkNotNull(call.parameters["id"]?.toLongOrNull())
        call.respond(HttpStatusCode.OK, repo.getHomework(id))
    }

    post("homework") {
        val homeworkContent = call.receive<Homework.Content>()
        call.respond(HttpStatusCode.OK, repo.addHomework(homeworkContent))
    }

    get("homework") {
        call.respond(HttpStatusCode.OK, repo.getHomeworks())
    }

    get("/submission/{id}") {
        val id = checkNotNull(call.parameters["id"]?.toLongOrNull())
        call.respond(HttpStatusCode.OK, repo.getSubmission(id))
    }

    post("/submission") {
        val submissionContent = call.receive<Submission.Content>()
        call.respond(HttpStatusCode.OK, repo.addSubmission(submissionContent))
    }

    get("{homeworkId}/submissions") {
        val homeworkId = checkNotNull(call.parameters["homeworkId"]?.toLongOrNull())
        call.respond(HttpStatusCode.OK, repo.getSubmissions(homeworkId))
    }

    get("/submission/{id}/result") {
        val id = checkNotNull(call.parameters["id"]?.toLongOrNull())
        call.respond(HttpStatusCode.OK, repo.getSubmissionResult(id))
    }
}


private fun Route.routeProfessorApi(repo: Repository) {
    post("/check/{submissionId}") {
        val id = checkNotNull(call.parameters["submissionId"]?.toLongOrNull())
        TODO()
    }

    post("checker") {
        val checkerContent = call.receive<Checker.Content>()
        repo.addChecker(checkerContent)
    }
}
