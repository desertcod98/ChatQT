package connection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ActiveConnections {
    private static ActiveConnections instance;
    private ArrayList<ConnHandler> activeConnections = new ArrayList<>();

    private ActiveConnections(){}

    public static ActiveConnections getInstance(){
        if(instance == null){
            instance = new ActiveConnections();
        }
        return instance;
    }
    public void addConnection(ConnHandler connHandler){
        activeConnections.add(connHandler);
    }

    public void removeConnection(ConnHandler connHandler){
        activeConnections.remove(connHandler);
    }

    public List<ConnHandler> getActiveConnections(){
        return Collections.unmodifiableList(activeConnections); //caller only needs to read the list
    }
}
