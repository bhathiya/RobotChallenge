package org.bee.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bee.exception.InvalidInputException;
import org.bee.model.Table;
import org.bee.output.OutputProcessor;

import java.io.IOException;

public class ReportCmd extends AbstractCommand {

    private static final Logger log = LogManager.getLogger(ReportCmd.class);

    private static final String REPORT = "REPORT";

    public ReportCmd() {
        super(REPORT);
    }

    @Override
    public void execute(Table table, OutputProcessor out) throws IOException, InvalidInputException {
        if (table.getRobotDirection() == null) {
            log.debug("Report called without placing the robot");
            throw new InvalidInputException("Turned Right without placing the robot");
        }

        String message = String.format("Output: %d,%d,%s\n",
                table.getRobotX(), table.getRobotY(), table.getRobotDirection());
        out.sendOutput(message);
        log.debug("Reported robot location. {}", message);
    }

}
