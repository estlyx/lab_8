package Commands;

import ForCity.City;
import ForCity.CityCollection;
import Tools.CityCreater;
import serv.ServConnection;

/**
 * The type Update.
 */
public class Update extends AnyCommand {


    @Override
    public String execute(Object o1, Object o2) {
        CityCollection collection = new CityCollection();
        try {
            System.out.println("YA EBALLLLLLLLLLLLLLLL2");
            int id=((City)o2).getId();
            City updCity = collection.getCollection().stream().filter(x->x.getId()==id).findFirst().get();
            if(updCity.getLogin().equals(o1)){;
                int color = updCity.getColor();
                City nc = (City) o2;
                nc.setColor(color);
                ServConnection.citycollection.getCollection().removeIf(p -> p.getId() == id);
                ServConnection.citycollection.add(nc);
                System.out.println("YA EBALLLLLLLLLLLLLLLL");
                return ("Город успешно обновлен.");
            } else return "Элемента с таким id не существует. или увас нет прав для внесения изменений";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Элемента с таким id не существует.";
        }
    }

    @Override
    public String getName() {
        return "update";
    }
}
