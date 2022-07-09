package Commands;

import ForCity.City;
import serv.ServConnection;

import java.util.List;


public class GetCollection extends CommandWithNoArg{
    @Override
    public List<City> execute2() {
        return ServConnection.citycollection.getCollection();
    }

    @Override
    public String getName() {
        return "GetCollection";
    }
}
