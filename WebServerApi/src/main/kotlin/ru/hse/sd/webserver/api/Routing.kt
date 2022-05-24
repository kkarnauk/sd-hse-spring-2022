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
import ru.hse.sd.hwproj.model.RunnerTask
import ru.hse.sd.hwproj.model.Submission
import ru.hse.sd.hwproj.queue.TasksSender
import ru.hse.sd.hwproj.queue.TasksSenderFacade
import ru.hse.sd.repo.Repository
import ru.hse.sd.repo.RepositoryFacade

internal fun Application.configureRouting() {
    install(ContentNegotiation) {
        gson()
    }

    val repo = RepositoryFacade()
    val tasksSender = TasksSenderFacade()

    routing {
        route("api/student") {
            routeStudentApi(repo)
        }
        route("api/submission") {
            routeSubmissionApi(repo, tasksSender)
        }
    }
}

private fun Route.routeStudentApi(repo: Repository) {
    get("/homework/{id}") {
        val id = checkNotNull(call.parameters["id"]?.toIntOrNull())
        call.respond(HttpStatusCode.OK, repo.getHomework(id))
    }

    post("/homework") {
        val homeworkContent = call.receive<Homework.Content>()
        call.respond(HttpStatusCode.OK, repo.addHomework(homeworkContent))
    }

    get("/homework") {
        call.respond(HttpStatusCode.OK, repo.getHomeworks())
    }

    get("/submission/{id}") {
        val id = checkNotNull(call.parameters["id"]?.toIntOrNull())
        call.respond(HttpStatusCode.OK, repo.getSubmission(id))
    }

    post("/submission") {
        val submissionContent = call.receive<Submission.Content>()
        call.respond(HttpStatusCode.OK, repo.addSubmission(submissionContent))
    }

    get("/{homeworkId}/submissions") {
        val homeworkId = checkNotNull(call.parameters["homeworkId"]?.toIntOrNull())
        call.respond(HttpStatusCode.OK, repo.getSubmissions(homeworkId))
    }

    get("/submission/{id}/result") {
        val id = checkNotNull(call.parameters["id"]?.toIntOrNull())
        val result = repo.getSubmissionResult(id)
        if (result == null) {
            call.respond(HttpStatusCode.NotFound)
        } else {
            call.respond(HttpStatusCode.OK, result)
        }
    }
}


private fun Route.routeSubmissionApi(repo: Repository, sender: TasksSender) {
    post("/check/{submissionId}") {
        val submissionId = checkNotNull(call.parameters["submissionId"]?.toIntOrNull())
        val submission = repo.getSubmission(submissionId)
        val homework = repo.getHomework(submission.homeworkId)
        sender.sendRunnerTask(RunnerTask(homework.checkerId, submissionId))
        call.respond(HttpStatusCode.OK)
    }

    post("/checker") {
        val checkerContent = call.receive<Checker.Content>()
        val checker = repo.addChecker(checkerContent)
        call.respond(HttpStatusCode.OK, checker)
    }
}
