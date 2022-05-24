package ru.hse.sd.hwproj.facade

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.http.*
import ru.hse.sd.hwproj.settings.AddressSettings
import java.net.URL

abstract class Facade(protected val addressSettings: AddressSettings) : AutoCloseable {
    protected val client = HttpClient(CIO) {
        install(JsonFeature) {
            serializer = GsonSerializer()
        }
    }

    protected suspend inline fun <reified T> request(url: String, reqMethod: HttpMethod, reqBody: Any? = null): T {
        return client.request(URL("http", addressSettings.host, addressSettings.port, url)) {
            method = reqMethod
            reqBody?.let {
                body = it
                contentType(ContentType.Application.Json)
            }
        }
    }

    override fun close() {
        client.close()
    }
}
