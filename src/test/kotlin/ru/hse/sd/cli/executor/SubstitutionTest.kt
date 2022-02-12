package ru.hse.sd.cli.executor

import org.junit.jupiter.api.Test

@Suppress("SpellCheckingInspection")
class SubstitutionTest : CommandExecutorTest() {
    @Test
    fun `Simple substitution test`() = withTestContext {
        test("a=b")
    }

    @Test
    fun `Argument substitution`() = withTestContext {
        test("a=text")
        test("echo \$a", "text")
    }

    @Test
    fun `Command substitution`() = withTestContext {
        test("a=ec")
        test("b=ho")
        test("\$a\$b wow", "wow")
    }

    @Test
    fun `Substitution in double quote literal`() = withTestContext {
        test("a=wow")
        test("echo \"\$a\"", "wow")
    }

    @Test
    fun `Dollar in quote literal`() = withTestContext {
        test("a=wow")
        test("echo '\$a'", "\$a")
    }

    @Test
    fun `Many substitution`() = withTestContext {
        test("var1=text1")
        test("var2= text2")
        test("var3 =text3")
        test("var4 = text4")
        test("echo \$var1", "text1")
        test("echo \$var2", "text2")
        test("echo \$var3", "text3")
        test("echo \$var4", "text4")
    }

    @Test
    fun `Test quotes`() = withTestContext {
        test("var1 =\"  text1     text2  \"")
        test("var2 ='  text3          text4 '")
        test("echo \$var1", "text1 text2")
        test("echo \$var2", "text3 text4")
    }

    @Test
    fun `Dollar sign`() = withTestContext {
        test("echo \"text$\"", "text$")
    }

    @Test
    fun `Multiple assigns`() = withTestContext {
        test("a=wow")
        test("echo \"\$a\$a text \$a\"", "wowwow text wow")
    }

    @Test
    fun `Test reassign`() = withTestContext {
        test("a=wow")
        test("a=hehe")
        test("echo \$a", "hehe")
    }

}
