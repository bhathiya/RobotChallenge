# RobotChallenge

![Git Actions](https://github.com/bhathiya/RobotChallenge/actions/workflows/maven.yml/badge.svg)

[![Codecov](https://codecov.io/gh/bhathiya/RobotChallenge/branch/main/graph/badge.svg?token=A6jjPscQGG)](https://codecov.io/gh/bhathiya/RobotChallenge)

# How to start the application

## Prerequisites:
- Docker

## Steps:
1. Pull docker image.

```shell
docker pull bhathiya/robot-challenge
```

2. Start a docker container and go inside it.  

```shell
docker run -it bhathiya/robot-challenge bash
```

3. Run the application in one of the below methods. 

**A) Console input and output (Default)**
```shell
$ java -jar robot-challenge.jar --debug
```
> **Note:** `--debug` prints debug logs.

 See [How to Play](#play) section for play commands.

**B) Text file input and output**
```shell
java -jar robot-challenge.jar --debug --input=TextFile --inputFilePath=sample-inputs/input1.txt --output=TextFile --outputFilePath=out.txt
```

**C) Mixed input and output**
```shell
java -jar robot-challenge.jar --debug --input=TextFile --inputFilePath=sample-inputs/input1.txt --output=StdOut
```

## Input Arguments

| Argument           | Description                                              |
|--------------------|----------------------------------------------------------|
| `--debug`          | Prints debug logs                                        |
| `--input`          | Input method. `TextFile` or `StdIn`. Default: `StdIn`    |
| `--output`         | Output method. `TextFile` or `StdOut`. Default: `StdOut` |
| `--inputFilePath`  | Input file path if `--input=TextFile` is given.          |
| `--outputFilePath` | Output file path if `--output=TextFile` is given.        |                                       |

# <a name="play"> How to play</a>

The application can read in commands of the following form:

```text
PLACE X,Y,F
MOVE
LEFT
RIGHT
REPORT
```

- `PLACE` will put the toy robot on the table in position X,Y and facing `NORTH`, `SOUTH`, `EAST` or `WEST`.
- The origin `(0,0)` is considered to be the SOUTH WEST most corner.
- The first valid command to the robot is a `PLACE` command, after that, any sequence of commands may be issued, in any order, including another `PLACE` command. 
- The application discards all commands in the sequence until a valid `PLACE` command has been executed.
- `MOVE` will move the toy robot one unit forward in the direction it is currently facing.
- `LEFT` and `RIGHT` will rotate the robot 90 degrees in the specified direction without changing the position of the robot.
- `REPORT` will announce the X,Y and orientation of the robot.
- A robot that is not on the table ignores the `MOVE`, `LEFT`, `RIGHT` and `REPORT` commands.

### Example:
```shell
$ java -jar robot-challenge.jar --debug

Robot Challenge Started! Awaiting input...

PLACE 1,2,NORTH
[07:22:19.912] DEBUG {org.bee.command.PlaceCmd} - Placed the robot at (1,2) facing NORTH.
MOVE
[07:22:28.707] DEBUG {org.bee.command.MoveCmd} - Moved from (1,2) to (1,3).
MOVE
[07:22:31.463] DEBUG {org.bee.command.MoveCmd} - Moved from (1,3) to (1,4).
LEFT
[07:22:33.315] DEBUG {org.bee.command.LeftCmd} - Turned Left from NORTH to WEST.
MOVE
[07:22:36.140] DEBUG {org.bee.command.MoveCmd} - Moved from (1,4) to (0,4).
REPORT
Output: 0,4,WEST
[07:22:38.867] DEBUG {org.bee.command.ReportCmd} - Reported robot location. Output: 0,4,WEST
```
