package Commands;

public abstract class MyCommand {

    protected String command;

    public void setPrikaz(String command) {
        this.command = command;
    }

    public abstract String execute();

    public abstract boolean exit();
}