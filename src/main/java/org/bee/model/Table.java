package org.bee.model;

public class Table extends AbstractTable {
    private final int sideX;
    private final int sideY;
    private int robotX;
    private int robotY;

    private Direction robotDirection;

    public Table(int side) {
        this.sideX = side;
        this.sideY = side;
    }

    public Table(int sideX, int sideY) {
        this.sideX = sideX;
        this.sideY = sideY;
    }

    public int getSideX() {
        return sideX;
    }

    public int getSideY() {
        return sideY;
    }

    public int getRobotX() {
        return robotX;
    }

    public int getRobotY() {
        return robotY;
    }

    public void setRobotX(int robotX) {
        this.robotX = robotX;
    }

    public void setRobotY(int robotY) {
        this.robotY = robotY;
    }

    public Direction getRobotDirection() {
        return robotDirection;
    }

    public void setRobotDirection(Direction robotDirection) {
        this.robotDirection = robotDirection;
    }
}
