package org.bee.processor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bee.command.AbstractCommand;
import org.bee.command.AbstractCommandBuilder;
import org.bee.exception.InvalidInputException;
import org.bee.input.InputProcessor;
import org.bee.model.Table;
import org.bee.output.OutputProcessor;

import java.io.IOException;

public class RobotNavigator {

    private static final Logger log = LogManager.getLogger(RobotNavigator.class);

    private final InputProcessor inputProcessor;
    private final OutputProcessor outputProcessor;
    private final AbstractCommandBuilder abstractCommandBuilder;
    private final Table table;

    public RobotNavigator(InputProcessor inputProcessor, OutputProcessor outputProcessor,
                          AbstractCommandBuilder abstractCommandBuilder, Table table) {
        this.inputProcessor = inputProcessor;
        this.outputProcessor = outputProcessor;
        this.abstractCommandBuilder = abstractCommandBuilder;
        this.table = table;
    }

    public void navigate() {
        try {
            outputProcessor.sendOutput("Robot Challenge Started! Awaiting input...\n");
            String strCommand;
            while (true) {
                strCommand = inputProcessor.readNextLine();
                if (strCommand == null || strCommand.isEmpty()) {
                    break;
                }
                AbstractCommand command;
                try {
                    command = abstractCommandBuilder.getCommandObject(strCommand);
                    command.execute(table, outputProcessor);
                } catch (InvalidInputException ex) {
                    //ignore invalid input
                    log.debug("Error ignored: " + ex.getMessage());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("IO Error occurred!!", e);
        } finally {
            try {
                inputProcessor.complete();
            } catch (IOException e) {
                log.error(e);
            }
            try {
                outputProcessor.sendOutput("Robot Challenge Finished!\n");
                outputProcessor.complete();
            } catch (IOException e) {
                log.error(e);
            }
        }
    }

}
