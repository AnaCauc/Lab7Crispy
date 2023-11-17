import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainApp {

    public static void scriere(Map<Integer,Carte> lista) {
        try {
            ObjectMapper mapper=new ObjectMapper();
            File file=new File("src/main/resources/carti.json");
            mapper.writeValue(file,lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Map<Integer,Carte> citire() {
        try {
            File file=new File("src/main/resources/carti.json");
            ObjectMapper mapper=new ObjectMapper();
            Map<Integer,Carte> carti = mapper
                    .readValue(file, new TypeReference<Map<Integer,Carte>>(){});
            return carti;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main (String[] args)
    {
        Map<Integer,Carte> map=new HashMap<Integer,Carte>();
        map = citire();



        var entryset=map.entrySet();

        //Afisare
        entryset.forEach(System.out::println);



        //Stergere dupa o cheie data de la tastatura
        Scanner scanner = new Scanner(System.in);
        int cheie = scanner.nextInt();

        var it=entryset.iterator();
        while(it.hasNext())
        {
            var m =it.next();
            Integer key=m.getKey();
            Carte value=m.getValue();
            if (key.equals(cheie))
                it.remove();

        }

        entryset.forEach(System.out::println);


        //Adaugare dupa o cheie data de la tastatura
        cheie = scanner.nextInt();
        map.putIfAbsent(cheie, new Carte(scanner.next(),scanner.next(),scanner.nextInt()));

        entryset.forEach(System.out::println);


        scriere(map);

    }
}
