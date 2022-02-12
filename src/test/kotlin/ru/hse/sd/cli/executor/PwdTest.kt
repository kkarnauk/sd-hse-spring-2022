package ru.hse.sd.cli.executor

import io.mockk.every
import io.mockk.mockkConstructor
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ru.hse.sd.cli.env.Environment
import java.nio.file.Path

class PwdTest : CommandExecutorTest() {
    private val dir = Path.of("home", "user", "dir").toString()

    @BeforeEach
    fun `Mock environment`() {
        mockkConstructor(Environment::class)
        every {
            anyConstructed<Environment>() getProperty "workingDirectory"
        } propertyType Path::class answers {
            Path.of("home", "user", "dir")
        }
    }

    @AfterEach
    fun `Unmock all`() = unmockkAll()

    @Test
    fun `Simple pwd`() = withTestContext {
        test("pwd", dir)
    }
}
