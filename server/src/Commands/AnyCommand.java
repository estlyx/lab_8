package Commands;

import ForCity.City;
import ForCity.CityCollection;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public abstract class AnyCommand {
    public String execute(Object o) throws IOException, SQLException {
        return null;
    }
    public String execute() throws IOException {
        return null;
    }
    public String execute(Object o1, Object o2){
        return null;
    }
    public String execute(City city, String o2) throws SQLException {
        return null;
    }

    public String execute(Object o1, Object o2, Object o3){
        return null;
    }

    public String getName() {
        return null;
    }

    public List<City> execute2(){
        return null;
    }
}
