package ru.hse.sd.webserver

import kotlinx.html.*
import ru.hse.sd.hwproj.model.Homework
import ru.hse.sd.hwproj.model.Submission
import java.text.SimpleDateFormat

private val dateFormat = SimpleDateFormat("d MMM yyyy")

private fun BODY.cards(allHomework: List<Homework>, createNew: Boolean, inFooter: DIV.(Homework) -> Unit) {
    div(classes = "container") {
        div(classes = "row row-cols-1 row-cols-md-4 g-4") {
            if (createNew) {
                div(classes = "col") {
                    form(classes = "card h-100", method = FormMethod.post, action = "professor/homework") {
                        div(classes = "card-body") {
                            h5(classes = "card-title") {
                                input(type = InputType.text, name = "name", classes = "form-control") {
                                    required = true
                                    placeholder = "Название новой домашки"
                                }
                            }
                            div(classes = "input-group mb-3") {
                                span(classes = "input-group-text") {
                                    id = "basic-addon1"
                                    text("С")
                                }
                                input(type = InputType.date, classes = "form-control input-sm", name = "startDate") {
                                    required = true
                                    id = "inputStartDate"
                                    attributes["aria-describedby"] = "basic-addon1"
                                }
                            }
                            div(classes = "input-group mb-3") {
                                span(classes = "input-group-text") {
                                    id = "basic-addon2"
                                    text("По")
                                }
                                input(type = InputType.date, classes = "form-control input-sm", name = "endDate") {
                                    required = true
                                    attributes["aria-describedby"] = "basic-addon2"
                                }
                            }
                            div {
                                textArea(classes = "form-control") {
                                    required = true
                                    name = "statement"
                                    style = "width: 100%"
                                    placeholder = "Описание новой домашки.."
                                }
                            }
                            text("Проверялка")
                            div(classes = "input-group mb-2") {
                                input(
                                    type = InputType.file,
                                    classes = "form-control form-file-input",
                                    name = "checker"
                                ) {
                                    id = "checkerForUpload"
                                    required = true
                                }
                            }
                            textArea(classes = "form-control") {
                                name = "checkerContent"
                                id = "checkerContent"
                                hidden = true
                            }
                        }
                        div(classes = "card-footer") {
                            button(type = ButtonType.submit, classes = "btn btn-primary") {
                                text("Создать")
                            }
                        }
                    }
                }
            }
            allHomework.forEach {
                div(classes = "col") {
                    div(classes = "card h-100") {
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
                        }
                        div(classes = "card-footer") {
                            inFooter(it)
                        }
                    }
                }
            }
        }
    }
}

private fun DIV.modalFade(
    modalId: String,
    title: String,
    action: String,
    formAction: DIV.() -> Unit,
    inFooter: DIV.() -> Unit
) {
    div(classes = "modal fade") {
        id = modalId
        tabIndex = "-1"
        attributes["aria-hidden"] = "true"
        div(classes = "modal-dialog") {
            div(classes = "modal-content") {
                div(classes = "modal-header") {
                    h5(classes = "modal-title") {
                        text(title)
                    }
                    button(type = ButtonType.button, classes = "btn-close") {
                        attributes["data-bs-dismiss"] = "modal"
                        attributes["aria-label"] = "Close"
                    }
                }
                form(method = FormMethod.post, action = action) {
                    div(classes = "modal-body") {
                        formAction()
                    }
                    div(classes = "modal-footer") {
                        inFooter()
                    }
                }
            }
        }
    }
}

internal fun HTML.studentPage(allHomework: List<Homework>) {
    head {
        title {
            +"Страничка студента"
        }
        link {
            rel = "stylesheet"
            href = "/assets/bootstrap/bootstrap.css"
        }
    }
    body {
        cards(allHomework, false) {
            val modalId = "modal${it.id}"
            button(type = ButtonType.button, classes = "btn btn-primary") {
                attributes["data-bs-toggle"] = "modal"
                attributes["data-bs-target"] = "#$modalId"
                text("Сдать")
            }
            modalFade(modalId, it.content.name, "student/submission", {
                p {
                    text(it.content.statement)
                }
                div(classes = "mb-3") {
                    input(type = InputType.text, classes = "form-control", name = "link") {
                        id = "solution"
                        placeholder = "Ссылка на github с решением"
                    }
                    input(type = InputType.text, classes = "form-control", name = "homeworkId") {
                        hidden = true
                        value = it.id.toString()
                    }
                }
            }) {
                button(type = ButtonType.button, classes = "btn btn-secondary") {
                    attributes["data-bs-dismiss"] = "modal"
                    text("Отменить")
                }
                button(type = ButtonType.submit, classes = "btn btn-primary") {
                    text("Отправить")
                }
            }
        }
        script {
            src = "/assets/bootstrap/bootstrap.bundle.min.js"
        }
    }
}

internal fun HTML.professorPage(allHomework: List<Homework>) {
    head {
        title {
            +"Страничка преподавателя"
        }
        link {
            rel = "stylesheet"
            href = "/assets/bootstrap/bootstrap.css"
        }
        link {
            rel = "stylesheet"
            href = "https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
        }
    }
    body {

        cards(allHomework, true) {
            a(href = "professor/homework/${it.id}", classes = "btn btn-primary") {
                text("Смотреть решения")
            }
        }
        script {
            src = "/assets/bootstrap/bootstrap.bundle.min.js"
        }
        script {
            //language=JavaScript
            unsafe {
                raw(
                    """
                        document.getElementById("checkerForUpload").addEventListener("change", handleChecker, false)
                        function handleChecker() {
                            var file = document.getElementById("checkerForUpload").files[0];
                            if (file) {
                               var reader = new FileReader();
                               reader.readAsText(file, "UTF-8");
                               reader.onload = function (evt) {
                                   document.getElementById("checkerContent").innerHTML = evt.target.result;
                               }
                               reader.onerror = function (evt) {
                                   document.getElementById("checkerContent").innerHTML = "";
                               }
                            }
                       }
           """.trimIndent()
                )
            }
        }
    }
}


internal fun HTML.homeworkPage(
    homework: Homework.Content,
    submissions: List<Submission>,
    results: List<Submission.Result?>
) {
    head {
        title {
            +homework.name
        }
        link {
            rel = "stylesheet"
            href = "/assets/bootstrap/bootstrap.css"
        }
        link {
            rel = "stylesheet"
            href = "https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
        }
    }
    body {
        div(classes = "container") {
            div(classes = "card h-100") {
                div(classes = "card-body") {
                    h5(classes = "card-title") {
                        text(homework.name)
                    }
                    h6(classes = "card-subtitle mb-2 text-muted") {
                        val startDate = dateFormat.format(homework.startDate)
                        val endDate = dateFormat.format(homework.endDate)
                        text("$startDate — $endDate")
                    }
                    p(classes = "card-text") {
                        text(homework.statement)
                    }
                }
                div(classes = "card-footer") {
                    div(classes = "list-group") {
                        submissions.zip(results).forEach { (submission, result) ->
                            val modalId = "modal${submission.id}"
                            button(classes = "list-group-item list-group-item-action") {
                                attributes["data-bs-toggle"] = "modal"
                                attributes["data-bs-target"] = "#$modalId"
                                text(submission.content.solutionLink.toString())
                            }
                            modalFade(modalId, "Решение задачи ${homework.name}", "", {
                                p {
                                    text("Сдано: ${dateFormat.format(submission.content.date)}")
                                }
                                p {
                                    text("Ссылка: ")
                                    a(classes = "link-primary", href = submission.content.solutionLink.toString()) {
                                        text(submission.content.solutionLink.toString())
                                    }
                                }
                                p {
                                    val resultMessage = if (result == null) {
                                        "не проверено"
                                    } else {
                                        "успех: ${result.success}, сообщение: ${result.message}, дата: ${result.checkDate}"
                                    }
                                    text("Результат проверки: $resultMessage")
                                }
                            }) {
                                button(type = ButtonType.button, classes = "btn btn-primary") {
                                    text("Проверить")
                                }
                            }
                        }
                    }
                }
            }
        }
        script {
            src = "/assets/bootstrap/bootstrap.bundle.min.js"
        }
    }
}
