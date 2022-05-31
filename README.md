# HSE Software Design Course

Участники группы:
* Лебедев Егор
* Худяков Юрий
* Карнаухов Кирилл
* Сурков Петр

## Bash-like CLI

### Building

Linux or MacOS:

```bash
./gradlew fatJar
```

Windows:
```bash
.\gradlew.bat fatJar
```

It will create a jar in `build/libs/cli-fat-VERSION.jar` that can be run with Java.

### Running

The previous command creates a `jar` in `build/libs/cli-fat-1.0-SNAPSHOT.jar`, so you can run with:
```bash
java -jar build/libs/cli-fat-1.0-SNAPSHOT.jar 
```

### Running tests

Linux or MacOS:

```bash
./gradlew test
```

Windows:

```bash
.\gradlew.bat test
```

For verbose test log add `--info` option 


### Grep

Библиотеки для разбора ключей:
* [Clikt](https://ajalt.github.io/clikt/)
* [Apache-cli](https://commons.apache.org/proper/commons-cli/)
* [Kotlinx-cli](https://github.com/Kotlin/kotlinx-cli)
* [Kotlin-argparser](https://github.com/xenomachina/kotlin-argparser)

Была выбрана первая библиотека по следующим причинам:
* Последняя библиотека уже пару лет не поддерживается.
* `Apache-cli` и другие библиотеки для Джавы не удобны, потому что создавались конкретно для Джавы и не позволяют
  написать все также лаконично, как в том же `Clikt`.
* `Kotlinx-cli` развивается намного медленнее, чем `Clikt`, релизов не было почти год, еще нет версии `1.0`.
