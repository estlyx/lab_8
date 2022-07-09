package Threads;

import Commands.AnyCommand;
import Commands.Commands;
import ForCity.*;
import Tools.Message;
import managers.DBManager;
import serv.ServConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Hashtable;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.String.valueOf;

public class Makem extends Thread {
    private AtomicReference<Message> ms, msout;
    private final Commands commands;
    private final Sendm send;

    public Makem(AtomicReference<Message> ms, AtomicReference<Message> msout,
                 Commands commands, Sendm send) {
        this.ms = ms;
        this.msout = msout;
        this.commands = commands;
        this.send = send;
        //start();
    }

    @Override
    public void run() {
        synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        //System.out.println(ms.get().getCommand() + "22");

        if (ms.get().getCommand().equals("add")) {
            //msout = new Message("Город успешно добавлен.");
            try {
                msout.set(new Message(commands.getCommand("add").execute(ms.get().getCity(), ms.get().getLogin())));
                load();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //citycollection.add(ms.getCity());

        } else if (ms.get().getArg() != null) {
            if (ms.get().getCommand().equals("Login")) {
                msout.set(new Message(commands.getCommand("login").execute(ms.get().getLogin(), ms.get().getArg())));
                System.out.println(msout.get().getCommand());
            } else if (ms.get().getCommand().equals("NewLogin")) {
                System.out.println(ms.get().getColor());
                msout.set(new Message(commands.getCommand("newlogin").execute(ms.get().getLogin(), ms.get().getArg(), ms.get().getColor())));
                System.out.println(msout.get().getCommand());
            } else {
                msout.set(new Message(commands.getCommand(ms.get().getCommand()).execute(ms.get().getArg(), ms.get().getLogin())));
                save();
            }
        } else {
            if (ms.get().getCommand().equals("exit")){
                try {
                    save();
                    System.out.println(commands.getCommand("save").execute());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                msout.set(new Message("Завершаю работу."));
            }else if (ms.get().getCommand().equals("GetCollection")){
                load();

                Message lol = new Message("GetCollection", commands.getCommand(ms.get().getCommand()).execute2());

                msout.set(lol);

                System.out.println("AAAAAAAAAAAA" + lol.getCityCollection().size());
            } else if (ms.get().getCommand().equals("update")) {
                System.out.println("AAAAAAAAAAAABBBBBBBBBB2");
                msout.set(new Message(commands.getCommand(ms.get().getCommand()).execute(ms.get().getLogin(), ms.get().getCity())));
                save();
                System.out.println("AAAAAAAAAAAABBBBBBBBBB");
            }
            else{
                try {
                    msout.set(new Message(commands.getCommand(ms.get().getCommand()).execute("null")));
                } catch (IOException | SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            //System.out.println(commands.getCommand(ms.getCommand()).execute("null"));
        }

        System.out.println(ms.get().getCommand());

        //System.out.println("kringe");
        synchronized (send) {
            send.notify();
        }
    }

    private void save(){
        try (Connection connect = DBManager.getInstance().getConnection();
             Statement req = connect.createStatement()) {
            connect.setAutoCommit(false);
            req.addBatch("DELETE from city");
            for (City tmp2 : CityCollection.getCollection()) {
                //System.out.println(tmp.getId());
                req.addBatch("insert into city VALUES (" + tmp2.getId() + ",'" + tmp2.getName() + "'," +
                        tmp2.getCoordinates().getX() + ',' + tmp2.getCoordinates().getY() + ",'" +
                        valueOf(tmp2.getCreationDate()) + "'," + tmp2.getAreaSize() + ',' + tmp2.getPopulation() + ',' +
                        tmp2.getMetersAboveSeaLevel() + ",'" + valueOf(tmp2.getEstablishmentDate()) + "'," + tmp2.getTelephoneCode() + ",'"
                        +valueOf(tmp2.getGovernment()) + "'," + tmp2.getGovernor().getHeight() + ",'" + tmp2.getLogin()+ "',"+tmp2.getColor()+ ")");
            }
            req.executeBatch();
            connect.commit();

            //return "Коллекция успешно сохранена.";
        } catch (SQLException e) {
            e.printStackTrace();
            //return "не удалось сохранить коллекцию.";
        }
    }

    private void load(){
        ResultSet tmp = null;

        try {
            tmp = DBManager.getInstance().getConnection().createStatement().executeQuery("SELECT * FROM city");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        ServConnection.citycollection.clear();
        while (true) {
            try {
                if (!tmp.next()) break;
                System.out.println(tmp.getInt("id"));
                City city = new City();
                city.setId(tmp.getInt("id"));
                city.setName(tmp.getString("name"));
                Coordinates coordinates = new Coordinates();
                coordinates.setX(tmp.getInt("x"));
                coordinates.setY(tmp.getInt("y"));
                city.setCoordinates(coordinates);
                city.setCreationDate(LocalDate.parse(tmp.getString("creationdate")));
                city.setAreaSize(tmp.getLong("area"));
                city.setPopulation(tmp.getLong("population"));
                city.setMetersAboveSeaLevel(tmp.getFloat("metersabovesealevel"));
                city.setEstablishmentDate(LocalDate.parse(tmp.getString("establishmentdate")));
                city.setTelephoneCode(tmp.getInt("telephonecode"));
                city.setGovernment(Government.valueOf(tmp.getString("government")));
                Human governor = new Human();
                governor.setHeight(tmp.getInt("governor"));
                city.setGovernor(governor);
                city.setLogin(tmp.getString("login"));
                city.setColor(tmp.getInt("color"));
                ServConnection.citycollection.add(city);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }
}