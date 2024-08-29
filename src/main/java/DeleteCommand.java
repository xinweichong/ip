public class DeleteCommand extends Command{

    private String[] parsed;

    public DeleteCommand(String[] parsed) {
        this.parsed = parsed;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws TalkerException {
        list.deleteTask(parsed, ui);
    }

}
