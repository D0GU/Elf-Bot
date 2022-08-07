package elf.bot;

import org.javacord.api.DiscordApi;

import java.io.IOException;
import java.util.Collections;

import static elf.bot.responses.loadResponses;


public class textInteractions {
    public static void interactions(DiscordApi api,String token,String prefix){

        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().equalsIgnoreCase(prefix+"pat")) {


                responses responseData;
                try {
                    responseData = loadResponses();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Collections.shuffle(responseData.pat);


                event.getChannel().sendMessage(responseData.pat.get(0));
            }
        });

        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().equalsIgnoreCase(prefix+"kiss")) {


                responses responseData;
                try {
                    responseData = loadResponses();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Collections.shuffle(responseData.kiss);


                event.getChannel().sendMessage(responseData.kiss.get(0));
            }
        });

        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().equalsIgnoreCase(prefix+"seggs")) {


                responses responseData;
                try {
                    responseData = loadResponses();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Collections.shuffle(responseData.seggs);


                event.getChannel().sendMessage(responseData.seggs.get(0));
            }
        });
    }
}
