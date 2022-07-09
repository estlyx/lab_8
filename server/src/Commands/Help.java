package Commands;

/**
 * The type Help.
 */
public class Help extends CommandWithNoArg {
    @Override
    public String execute(Object o){
        return ("help : вывести справку по доступным командам\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "add {element} : добавить новый элемент в коллекцию\n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_by_id id : удалить элемент из коллекции по его id\n" +
                "average_of_telephone_code : вывести среднее значение поля telephoneCode для всех элементов коллекции\n");
    }
    @Override
    public String getName(){return "help";}
}
