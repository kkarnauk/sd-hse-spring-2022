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

private fun DIV.modalFade(modalId: String, title: String, inBody: DIV.() -> Unit, inFooter: DIV.() -> Unit) {
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
                div(classes = "modal-body") {
                    inBody()
                }
                div(classes = "modal-footer") {
                    inFooter()
                }
            }
        }
    }
}

fun HTML.studentPage(allHomework: List<Homework>) {
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
            modalFade(modalId, it.content.name, {
                p {
                    text(it.content.statement)
                }
                form {
                    div(classes = "mb-3") {
                        input(type = InputType.text, classes = "form-control") {
                            id = "solution"
                            placeholder = "Ссылка на github с решением"
                        }
                    }
                }
            }) {
                button(type = ButtonType.button, classes = "btn btn-secondary") {
                    attributes["data-bs-dismiss"] = "modal"
                    text("Отменить")
                }
                button(type = ButtonType.button, classes = "btn btn-primary") {
                    text("Отправить")
                }
            }
        }
        script {
            src = "/assets/bootstrap/bootstrap.bundle.min.js"
        }
    }
}

fun HTML.professorPage(allHomework: List<Homework>) {
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
    }
}


fun HTML.homeworkPage(homework: Homework, submission: List<Submission>) {
    head {
        title {
            +homework.content.name
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
                        text(homework.content.name)
                    }
                    h6(classes = "card-subtitle mb-2 text-muted") {
                        val startDate = dateFormat.format(homework.content.startDate)
                        val endDate = dateFormat.format(homework.content.endDate)
                        text("$startDate — $endDate")
                    }
                    p(classes = "card-text") {
                        text(homework.content.statement)
                    }
                }
                div(classes = "card-footer") {
                    div(classes = "list-group") {
                        submission.forEach {
                            val modalId = "modal${it.id}"
                            button(classes = "list-group-item list-group-item-action") {
                                attributes["data-bs-toggle"] = "modal"
                                attributes["data-bs-target"] = "#$modalId"
                                text(it.content.solutionLink.toString())
                            }
                            modalFade(modalId, "Решение задачи ${homework.content.name}", {
                                p {
                                    text("Сдано: ${dateFormat.format(it.content.date)}")
                                }
                                p {
                                    text("Ссылка: ")
                                    a(classes = "link-primary", href = it.content.solutionLink.toString()) {
                                        text(it.content.solutionLink.toString())
                                    }
                                }
                                p {
                                    text("Результат проверки: не проверено")
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
