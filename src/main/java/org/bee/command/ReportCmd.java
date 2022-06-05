package org.bee.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bee.exception.InvalidInputException;
import org.bee.model.Table;
import org.bee.output.OutputProcessor;

import java.io.IOException;

/**
 * Represents REPORT Command
 */
public class ReportCmd extends AbstractCommand {

    private static final Logger log = LogManager.getLogger(ReportCmd.class);

    private static final String REPORT = "REPORT";

    protected ReportCmd() {
        super(REPORT);
    }

    /**
     * Reports the current location and direction of the robot.
     *
     * @param table Table representing the valid area of movement.
     * @param out   Output processor for output messages.
     * @throws IOException IO Exception while writing to the OutputProcessor
     * @throws InvalidInputException Invalid Input Exception
     */
    @Override
    public void execute(Table table, OutputProcessor out) throws IOException, InvalidInputException {
        if (table.getRobotDirection() == null) {
            log.debug("Can't report without placing the robot");
            throw new InvalidInputException("Report called without placing the robot");
        }

        String message = String.format("Output: %d,%d,%s\n",
                table.getRobotX(), table.getRobotY(), table.getRobotDirection());
        out.sendOutput(message);
        log.debug("Reported robot location. {}", message);
    }

}
