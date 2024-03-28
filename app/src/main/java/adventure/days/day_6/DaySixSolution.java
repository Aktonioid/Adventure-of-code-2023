package adventure.days.day_6;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class DaySixSolution {
    
    // accedentaly it is solution for part one and part two
    public static int partOneSolution() throws IOException
    {
        Path path = Path.of("src/main/java/adventure/days/day_6/input.txt");

        BufferedReader reader = Files.newBufferedReader(path);
        
        String line = reader.readLine();
        String[] times = line.split(":")[1].split(" ");
        
        line = reader.readLine();
        String[] records = line.split(":")[1].split(" ");

        int result = 1;
        int recordWays = 0;

        for(int i = 0; i < times.length; i++)
        {
            System.out.println(i);
            long duration = Long.parseLong(times[i]); // продолжительность гонки
            long record = Long.parseLong(records[i]); // рекорд

            recordWays = 0;

            for(long j = 0; j <= duration; j++)
            {
                long raceDuration = duration-j;// сколько еще гонка будет продолжаться
                long speed = j;
                if(speed*raceDuration > record)
                {
                    recordWays++;
                }
            }
            result *= recordWays;
        }

        System.out.println();

        return result;
    }
}
