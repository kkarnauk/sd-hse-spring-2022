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
