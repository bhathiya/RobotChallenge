package org.bee.command;

public abstract class AbstractCommandWithArgs extends AbstractCommand {

    public AbstractCommandWithArgs(String command, String[] args) {
        super(command);
        this.args = args;
    }

    protected String[] args = null;

    public void setArgs(String[] args) {
        this.args = args;
    }

    public String[] getArgs() {
        return args;
    }
}
