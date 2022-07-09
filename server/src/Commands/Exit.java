package Commands;

/**
 * The type Exit.
 */
public class Exit extends CommandWithNoArg {
    @Override
    public String execute(Object o){
        return "Завершаю работу.";
    }
    @Override
    public String getName(){return "exit";}
}
