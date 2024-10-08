package talker.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import talker.TalkerException;

/**
 * Represents an Event task with a start and end date/time
 */
public class Event extends Task {
    // input formatter
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    // output formatter
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");

    // start date/time
    private LocalDateTime from;
    // end date/time
    private LocalDateTime to;
    /**
     * Constructor for Event
     *
     * @param description description of task
     * @param from starting date/time
     * @param to ending date/time
     */
    public Event(String description, String from, String to) throws TalkerException {
        super(description, TaskType.EVENT);
        try {
            this.from = LocalDateTime.parse(from, INPUT_FORMAT);
            this.to = LocalDateTime.parse(to, INPUT_FORMAT);
            this.isStartBeforeEnd();
        } catch (DateTimeException e) {
            throw new TalkerException("Invalid date-time format. Use dd-MM-yyyy HH:mm (01-01-2024 00:00)");
        }
    }

    /**
     * Constructor for Event with status
     *
     * @param description description of task
     * @param from starting date/time
     * @param to ending date/time
     * @param isComplete status of task
     * @param priorityType priority of task
     * @throws TalkerException showing invalid event parameters
     */
    public Event(String description,
                 String from,
                 String to,
                 boolean isComplete,
                 PriorityType priorityType) throws TalkerException {
        super(description, TaskType.EVENT, isComplete, priorityType);
        try {
            this.from = LocalDateTime.parse(from, INPUT_FORMAT);
            this.to = LocalDateTime.parse(to, INPUT_FORMAT);
            this.isStartBeforeEnd();
        } catch (DateTimeException e) {
            throw new TalkerException("Invalid date-time format. Use dd-MM-yyyy HH:mm (01-01-2024 00:00)");
        }
    }

    /**
     * Checks if start of event is before end of event
     *
     * @throws TalkerException start of event must come before end
     */
    private void isStartBeforeEnd() throws TalkerException {
        if (from.isAfter(to)) {
            throw new TalkerException("Start of event has to be before end of event!");
        }
    }

    /**
     * Returns start date/time of task
     *
     * @return LocalDateTime object with the start date/time of task
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Returns end date/time of task
     *
     * @return LocalDateTime object with the end date/time of task
     */
    public LocalDateTime getTo() {
        return to;
    }

    /**
     * Returns string representation of Event for file writing
     *
     * @return String formatted by Task including start and end date/time
     */
    @Override
    public String getSaveFormat() {
        return super.getSaveFormat() + " | " + from.format(INPUT_FORMAT)
                + " | " + to.format(INPUT_FORMAT);
    }

    /**
     * Returns String representation of Event
     *
     * @return "[E]" with String representation of task including start and end date/time
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(OUTPUT_FORMAT)
                + " to: " + to.format(OUTPUT_FORMAT) + ")";
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Event) {
            Event event = (Event) object;
            boolean isSameDescription = this.description.equals(event.description);
            boolean isSameFrom = this.from.equals(event.from);
            boolean isSameTo = this.to.equals(event.to);

            if (isSameDescription && isSameFrom && isSameTo) {
                return true;
            }
        }
        return false;
    }
}
