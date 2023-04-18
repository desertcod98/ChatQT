package connection;

import io.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ActiveConnections {
    private static ActiveConnections instance;
    private ArrayList<ConnHandler> activeConnections = new ArrayList<>();

    private ActiveConnections() {
    }

    public static ActiveConnections getInstance() {
        if (instance == null) {
            instance = new ActiveConnections();
        }
        return instance;
    }

    public void addConnection(ConnHandler connHandler) {
        activeConnections.add(connHandler);
    }

    public void removeConnection(ConnHandler connHandler) {
        Logger.notify(String.format("%s (%s) %s"
                , connHandler.getClientUsername()
                , connHandler.getIpString()
                , "disconnected"));
        broadcast(connHandler.getClientUsername() + " disconnected");
        activeConnections.remove(connHandler);
    }

    public List<ConnHandler> getActiveConnections() {
        return Collections.unmodifiableList(activeConnections); //caller only needs to read the list
    }

    public void broadcast(String message) {
        activeConnections.forEach(connHandler -> connHandler.sendMessage(message));
    }

    public Optional<ConnHandler> getUser(String username) {
        return activeConnections
                .stream()
                .filter(connHandler -> connHandler.getClientUsername().equalsIgnoreCase(username.trim()))
                .findFirst();
    }

    public void closeAllConnections(){
        activeConnections.forEach(ConnHandler::shutdown);
        activeConnections.clear();
    }
}
