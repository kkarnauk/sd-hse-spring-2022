package ru.hse.sd.hwproj.model

data class Checker(val id: Long, val content: Content) {
    @Suppress("ArrayInDataClass")
    data class Content(val bytes: ByteArray)
}
