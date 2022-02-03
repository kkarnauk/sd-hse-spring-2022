# HSE Software Design Course

Участники группы:
* Лебедев Егор
* Худяков Юрий
* Карнаухов Кирилл
* Сурков Петр

## Bash-like CLI

### Building

Just run `./gradlew fatJar` (on MacOS or Linux) or `.\gradlew.bat fatJar` (on Windows).
It will create a jar in `build/libs/cli-fat-VERSION.jar` that can be run with Java.

### Running

Just run `java -jar /path/to/fatJar` (described in the previous section).

### Running tests

If you want to run tests, just use `./gradlew test` (on MacOS or Linux) or `.\gradlew.bat test` (on Windows).
