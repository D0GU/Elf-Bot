package elf.bot;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.javacord.api.DiscordApi;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class responses{
    ArrayList<String> pat = new ArrayList<>();
    ArrayList<String> seggs = new ArrayList<>();
    ArrayList<String> kiss = new ArrayList<>();


    public void addResponse(String response){
        this.pat.add(response);
    }

    public ArrayList<String> getPat(){
        return this.pat;
    }
    public void setPat(ArrayList<String> pat){
        this.pat = pat;
    }

    public ArrayList<String> getSeggs(){
        return this.seggs;
    }
    public void setSeggs(ArrayList<String> seggs){
        this.seggs = seggs;
    }

    public ArrayList<String> getKiss(){
        return this.kiss;
    }
    public void setKiss(ArrayList<String> kiss){
        this.kiss = kiss;
    }

    public static void responseListeners(DiscordApi api, String token, String prefix){

        api.addMessageCreateListener(event -> {;
            String[] messageArr = event.getMessageContent().split(" ");

            if (Objects.equals(messageArr[0], (prefix + "new_response_data"))) {
                String responseType = messageArr[1];
                String responseArg = String.join(" ", Arrays.copyOfRange(messageArr, 2, messageArr.length));

                try {
                    newResponseData(responseArg,responseType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                event.getChannel().sendMessage("New response added!");
            }
        });

        api.addMessageCreateListener(event -> {;
            String[] messageArr = event.getMessageContent().split(" ");
            if (Objects.equals(messageArr[0], (prefix + "new_response"))) {
                responses responseData = new responses();

                String responseType = messageArr[1];
                String responseArg = String.join(" ",Arrays.copyOfRange(messageArr, 2, messageArr.length));

                try {
                    responseData = loadResponses();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                newResponse(responseData,responseArg,responseType);

                event.getChannel().sendMessage("New response added!");
            }
        });
    }
    public static void newResponse(responses responseData,String Response,String responseType){

        switch(responseType){
            case "pat":
                responseData.pat.add(Response);
                break;
            case "seggs":
                responseData.seggs.add(Response);
                break;
            case "kiss":
                responseData.kiss.add(Response);
        }


        ObjectMapper mapper = new ObjectMapper();
        try {
            File myObj = new File("responseData.json");
            if (myObj.exists()) {
                mapper.writeValue(Paths.get("responseData.json").toFile(), responseData);
                System.out.println("File written to.");
            } else {
                newResponseData(Response,responseType);
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public static void newResponseData(String Response,String responseType) throws IOException {
        responses newResponseData = new responses();

        switch(responseType){
            case "pat":
                newResponseData.pat.add(Response);
                break;
            case "seggs":
                newResponseData.seggs.add(Response);
                break;
            case "kiss":
                newResponseData.kiss.add(Response);
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        try {
            File myObj = new File("responseData.json");
            if (myObj.createNewFile()) {
                mapper.writeValue(Paths.get("responseData.json").toFile(), newResponseData);
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File Exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public static void saveResponses(responses responseData) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File myObj = new File("responseData.json");
            Path path = Paths.get("responseData.json");
            if (myObj.createNewFile()) {
                mapper.writeValue(path.toFile(), responseData);
                System.out.println("File created: " + myObj.getName());
            } else {
                mapper.writeValue(path.toFile(), responseData);
                System.out.println("File written to.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
    public static responses loadResponses() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        File file = new File("responseData.json");
        return mapper.readValue(file,responses.class);
    }
}