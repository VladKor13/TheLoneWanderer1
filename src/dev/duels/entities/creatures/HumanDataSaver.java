package dev.duels.entities.creatures;

import java.io.*;

public class HumanDataSaver {
    public HumanDataSaver(){
    }

    public static void SerializeHumanData(Human object, String path){
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    new FileOutputStream(path)
            );
            objectOutputStream.writeObject(object);
            objectOutputStream.close();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static Human RestoreHumanData(String path){
        Human object = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(
                    new FileInputStream(path)
            );
            object = (Human) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return object;
    }
}
