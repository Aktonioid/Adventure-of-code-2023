package adventure.days.day_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class DayFiveSolution 
{
    public static long PartOneSolution() throws IOException
    {

        // Мои идеи были говном
        // Надо считать наш инпут за раз, потом назделиить на массивы по разрыву строк, затем их внутри уже раскидать в отдельные строки по \n
        // Так же надо не городить мапы для связей, а проходиться по каждому сиду в отдельности и потом просто сравнимать его с минимальным значением
        // Блять. Сука. Тут была простая блять формула где, получается Изначальное значение += (начало промежутка приравнивания(метса назначения) - начало промежутка сравнения(источника))
        // 
        // Так же залесть на реддит и посмотреть че пишут другие люди, так как ощущуние, что я чет алгоритм перемудриваю и есть более элегантное и компактное решение
        Path path = Path.of("src/main/java/adventure/days/day_5/input.txt");

        BufferedReader reader = Files.newBufferedReader(path);

        String line = reader.readLine();

        ArrayList<Long> seeds = new ArrayList<>(Stream
                                                    .of(line.split(":")[1].split(" "))
                                                    .filter(s -> !s.isEmpty())
                                                    .map(Long::valueOf)
                                                    .collect(Collectors.toList()));

        ArrayList<String> seedToSoil;
        ArrayList<String> soilToFert;
        ArrayList<String> fertToWater;
        ArrayList<String> waterToLight;
        ArrayList<String> lighttoTemp;
        ArrayList<String> tempToHum;
        ArrayList<String> humToLoc;
        
        

        reader.readLine(); // скипаем пустую строку

        seedToSoil = readLinesToArray(reader);
        
        reader.readLine();// скип пустой строки

        soilToFert = readLinesToArray(reader);
        reader.readLine();// скип пустой строки

        fertToWater = readLinesToArray(reader);
        reader.readLine();// скип пустой строки

        waterToLight = readLinesToArray(reader);
        reader.readLine();// скип пустой строки

        lighttoTemp = readLinesToArray(reader);
        reader.readLine();// скип пустой строки

        tempToHum = readLinesToArray(reader);
        reader.readLine();// скип пустой строки

        humToLoc = readLinesToArray(reader);
        reader.readLine();// скип пустой строки

        ArrayList<ArrayList<String>> maps = new ArrayList<>(
                                            Arrays.asList(seedToSoil,
                                                          soilToFert,
                                                          fertToWater,
                                                          waterToLight,
                                                          lighttoTemp,
                                                          tempToHum,
                                                          humToLoc));

        long min = Long.MAX_VALUE;
        for(Long x : seeds)
        {
            for(ArrayList<String> map : maps)
            {
                for(String destSourceRange : map)
                {
                    // System.out.println(destSourceRange.replaceAll(" ", "_"));
                    String[] splittedLine = destSourceRange.split(" ");
                    long destination = Long.parseLong(splittedLine[0]);
                    long source = Long.parseLong(splittedLine[1]);
                    long range = Long.parseLong(splittedLine[2]);

                    if(x >= source && x <source+range)
                    {
                        x += destination - source;
                        break;
                    }
                }
            }
            
            min = Math.min(x, min);
        }
        return min;
    }

    private static ArrayList<String> readLinesToArray(BufferedReader reader) throws IOException // read lines and write it to array
    {
        ArrayList<String> returnable = new ArrayList<>();
        reader.readLine(); // скип названия
        String line = reader.readLine();
        while (!line.isEmpty()) 
        {
            // System.out.println(line);
            returnable.add(line);
            line = reader.readLine(); 
            if(line == null) break;   
        }

        return returnable;
    }
}
