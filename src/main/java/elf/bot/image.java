package elf.bot;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.javacord.api.DiscordApi;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;

public class image{
    String name;
    String imgPath;

    image(String characterName,String imgPath){
        this.name = characterName;
        this.imgPath = imgPath;
    }

    public void setName(String characterName){
        this.name = characterName;
    }
    public String getName(){
        return this.name;
    }
    public void setImgPath(String imgPath){
        this.imgPath = imgPath ;
    }
    public String getImgPath(){
        return this.imgPath;
    }

    public static void imageInteractions(DiscordApi api,String token, String prefix){

    }

    public static void newImageDict(String imageIdentifier,String characterName,String imgPath) throws IOException {
        image newImage = new image(characterName,imgPath);
        images newImageData = new images();
        newImageData.imageDict.put(imageIdentifier,newImage);


        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        try {
            File myObj = new File("imageData.json");
            if (myObj.createNewFile()) {
                mapper.writeValue(Paths.get("imageData.json").toFile(), newImageData);
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File Exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public static void newImage(images imageData,String imageIdentifier,String characterName, String imgPath) throws IOException {
        image newImage = new image(characterName,imgPath);
        imageData.imageDict.put(imageIdentifier,newImage);


        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        try {
            File myObj = new File("imageData.json");
            if (myObj.exists()) {
                mapper.writeValue(Paths.get("imageData.json").toFile(), imageData);
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File Exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public static void saveImageDict(images ImageData){
        ObjectMapper mapper = new ObjectMapper();

        try {
            File myObj = new File("imageData.json");
            if (myObj.exists()) {
                mapper.writeValue(Paths.get("imageData.json").toFile(), ImageData);
                System.out.println("File written to.");
            } else {

                System.out.println("File written to.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
    public static images loadImageDict() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        File file = new File("imageData.json");
        return mapper.readValue(file,images.class);

    }


}

class images {
    HashMap<String,image> imageDict = new HashMap<String,image>();


    public void addImage(String imageIdentifier,image newImage){
        this.imageDict.put(imageIdentifier,newImage);
    }
    public void removeImage(String imageIdentifier){
        this.imageDict.remove(imageIdentifier);
    }
    public void setImageDict(HashMap<String,image> imageDict){
        this.imageDict = imageDict;
    }
    public HashMap<String,image> getImageDict(){
        return this.imageDict;
    }

}



