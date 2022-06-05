# RobotChallenge

![Git Actions](https://github.com/bhathiya/RobotChallenge/actions/workflows/maven.yml/badge.svg)

[![Codecov](https://codecov.io/gh/bhathiya/RobotChallenge/branch/main/graph/badge.svg?token=A6jjPscQGG)](https://codecov.io/gh/bhathiya/RobotChallenge)

# How to run

## Prerequisites:
- Docker

## Steps:
1. Pull docker image.

```shell
docker pull bhathiya/robot-challenge
```

2. Start a docker container and go inside it.  

```shell
docker run -it robot-challenge bash
```

3. Run the application in one of the below methods. 

**Console input and output (Default)**
```shell
java -jar robot-challenge.jar --debug
```
or
```shell
cat sample-inputs/input1.txt | java -jar robot-challenge.jar --debug
```

**Text file input and output**
```shell
java -jar robot-challenge.jar --debug --input=TextFile --inputFilePath=sample-inputs/input1.txt --output=TextFile --outputFilePath=out.txt
```

**Mixed input and output**
```shell
java -jar robot-challenge.jar --debug --input=TextFile --inputFilePath=sample-inputs/input1.txt --output=StdOut
```

> **Note:** `--debug` prints debug logs.

