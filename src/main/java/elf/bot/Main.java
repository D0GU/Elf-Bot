package elf.bot;

import io.github.cdimascio.dotenv.Dotenv;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;


public class Main {
    public static void main(String[] args) {

        Dotenv dotenv = Dotenv.load();
        String token = dotenv.get("TOKEN");
        String prefix = dotenv.get("PREFIX");

        DiscordApi api = new DiscordApiBuilder()
                .setToken(token)
                .login().join();

        textInteractions.interactions(api,token,prefix);
        image.imageInteractions(api,token,prefix);
        responses.responseListeners(api,token,prefix);




    }




}


