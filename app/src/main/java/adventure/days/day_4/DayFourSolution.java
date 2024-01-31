package adventure.days.day_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class DayFourSolution 
{
    public static int PartOneSolution() throws IOException
    {
        Path path = Path.of("src/main/java/adventure/days/day_4/input.txt");

        BufferedReader reader = Files.newBufferedReader(path);

        String line = reader.readLine();

        String[] splittedLine = {};


        ArrayList<Integer> fstHalf = new ArrayList<Integer>();    
        
        ArrayList<Integer> secHalf = new ArrayList<>();

        int result = 0;

        while (line != null) 
        {
            fstHalf.clear();
            secHalf.clear();

            line = line.split(":")[1];

            splittedLine = line.replace("|", ",").split(",");

            fstHalf = new ArrayList<>(Stream.of(splittedLine[0].trim().split(" ")).filter(f -> !f.isEmpty()).map(Integer::valueOf).collect(Collectors.toList()));
            secHalf = new ArrayList<>(Stream.of(splittedLine[1].trim().split(" ")).filter(f -> !f.isEmpty()).map(Integer::valueOf).collect(Collectors.toSet()));

            int curScore =0;
            int count = 0;
            for(int i  =0 ; i < secHalf.size(); i++)
            {
                if(fstHalf.contains(secHalf.get(i)))
                {
                    count++;
                }
            }
            
            curScore = (int)Math.pow(2, count-1);
            result += curScore;
            
            line = reader.readLine();
        }

        return result;
    }    

    public static int PartTwoSolution() throws IOException
    {
        Path path = Path.of("src/main/java/adventure/days/day_4/input.txt");

        ArrayList<String> cards = new ArrayList<>(Files.readAllLines(path));

        HashMap<String, Integer> copyCount = new HashMap<>();

        for(int i = 0; i < cards.size(); i++)
        {
            
            String[] splittedLine = cards.get(i).split(":");

            splittedLine[1] = splittedLine[1].replace("|", ",");

            ArrayList<Integer> winNumbers = new ArrayList<>(Stream // победные числа(левая часть)
                                                                .of(splittedLine[1].split(",")[0].split(" "))
                                                                .filter(n -> !n.isEmpty())
                                                                .map(Integer::valueOf)
                                                                .collect(Collectors.toList()));
            ArrayList<Integer> nums = new ArrayList<>(Stream //карты, числа, которые есть на карте(правая часть)
                                                        .of(splittedLine[1].split(",")[1].split(" "))
                                                        .filter(n -> !n.isEmpty())
                                                        .map(Integer::valueOf)
                                                        .collect(Collectors.toList()));
            int count = 0;

            //пробегаемя по выйгрышным и смотрим есть ли они в наших картах, так как так, по идее должно быть меньше итераций
            for(Integer num : winNumbers)
            {
                if(nums.contains(num))
                {
                    count++;
                }
            }
            int maxIndex = i+count;

            if(maxIndex > cards.size()-1)
            {
                maxIndex = cards.size()-1;
            }

            int addCopies = 1;// переменная чтобы определить сколько копий создавать

            if(copyCount.get(splittedLine[0]) != null) // Если копии этой карты имеются, то мы добавляем к след картам еще и копии по резульатам имеющихся у нас копий
            {
                addCopies += copyCount.get(splittedLine[0]);
            }

            for(int j = i+1; j <= maxIndex; j++)
            {
                String gameNumber = cards.get(j).split(":")[0];

                // добавляем в мапу с колличеством копий необходимое колличество копий, если копий нет, создаем
                copyCount.put(gameNumber,
                             copyCount.getOrDefault(gameNumber, 0) + addCopies);
            }


            
        }

        int result = cards.size();

        for(String key : copyCount.keySet())
        {
            System.out.print(key+"  ");
            System.out.println(copyCount.get(key));
            result += copyCount.get(key);
        }

        return result;
    }
}
