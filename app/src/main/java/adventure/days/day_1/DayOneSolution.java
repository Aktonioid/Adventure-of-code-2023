package adventure.days.day_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//
public class DayOneSolution 
{
    public static int SolutionPartOne() throws IOException
    {

        Path path = Path.of("src\\main\\java\\adventure\\days\\day_1\\input.txt").toAbsolutePath();

        BufferedReader reader = Files.newBufferedReader(path);

        int result = 0;

        String line = reader.readLine();
        while (line != null) 
        {
            line = line.replaceAll("[a-zA-Z]", "");
            result += Integer.parseInt(""+line.charAt(0)+line.charAt(line.length()-1));
            line = reader.readLine();
        }

        return result;
    }  

    public static int SolutionPartTwo() throws IOException
    {
        Path path = Path.of("src\\main\\java\\adventure\\days\\day_1\\input.txt").toAbsolutePath();

        BufferedReader reader = Files.newBufferedReader(path);

        int result = 0;

        HashMap<String,Integer> map = new HashMap<>();

        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);
        map.put("six", 6);
        map.put("seven", 7);
        map.put("eight", 8);
        map.put("nine", 9);

        String line = reader.readLine();
        while (line != null) 
        {
            ArrayList<Integer> integers = new ArrayList<>();
            for(int i = 0; i<line.length(); i++)
            {
                try
                {
                    integers.add((Integer.parseInt(""+line.charAt(i))));
                }
                catch(Exception e)
                {
                    for(String k : map.keySet())
                    {
                        
                        if(line.substring(i).startsWith(k))
                        {
                            integers.add(map.get(k));
                        }

                    }
                }
            }

            result += Integer.parseInt(""+integers.get(0)+integers.get(integers.size()-1));
            line = reader.readLine();
            // System.out.println();
        }

        return result;
    }
}
