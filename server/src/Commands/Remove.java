package Commands;

import ForCity.City;
import ForCity.CityCollection;
import serv.ServConnection;

import java.util.Iterator;

/**
 * The type Remove.
 */

public class Remove extends AnyCommand {
    @Override
    public synchronized String execute(Object o, Object o2){
        try{
            CityCollection collection = new CityCollection();
            if (collection.getSize()==0) return("Коллекция пуста");
            else{

                try{
                    int s1 = ServConnection.citycollection.getCollection().size();
                    ServConnection.citycollection.getCollection().removeIf(p -> p.getId() == Integer.parseInt((String) o) && p.getLogin().equals(o2));
                    if (s1 -1 == ServConnection.citycollection.getCollection().size()) {
                        return ("Город успешно удален.");
                    }
                } catch (NumberFormatException exp) {
                    return "Неверно указан аргумент.";
                }
                //Iterator<City> it = (Iterator<City>) CityCollection.getCollection().iterator();

                return ("Элемента с таким id не существует или у вас нет прав для внесения изменений.");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getName(){return "remove_by_id";}
}
