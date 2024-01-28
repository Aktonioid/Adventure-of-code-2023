package adventure.days.day_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;


public class DayTwoSolution 
{
    public static int PartOneSolution() throws IOException
    {
        Path path =Path.of("src/main/java/adventure/days/day_2/input.txt");

        BufferedReader reader = Files.newBufferedReader(path);
        
        int result = 0;// sum of ids where game is possible

        String line = reader.readLine();

        HashMap<String, Integer> max =new HashMap<>(); 

        max.put("red", 12);
        max.put("green", 13);
        max.put("blue", 14);

        cycle: while(line != null)
        {
            // System.out.println(line.split(":")[1]);
            String[] spplittedLine = line.split(":");// избавление от части game /d:
            String gameNumber = spplittedLine[0].split(" ")[1];// получаем номер игры

            line = spplittedLine[1];
            spplittedLine = line.split(";");// разделение на отдельные сеты

            int idsCount = Integer.parseInt(gameNumber);// переводим номер игры в int

            for(int i = 0; i < spplittedLine.length; i++)
            {       
                String[] checkString = spplittedLine[i].trim().replaceAll(",", "").split(" ");

                HashMap<String, Integer> ballsCount = new HashMap<>();// мапа для получения сравнения значений в сете и максимальному колличеству кубиков

                ballsCount.put("red", 0);
                ballsCount.put("green", 0);
                ballsCount.put("blue", 0);

                for(int j = 0;j < checkString.length; j+= 2)//заполняем мапу
                {
                    ballsCount.put(checkString[j+1], ballsCount.get(checkString[j+1])+Integer.parseInt(checkString[j]));
                }
                for(String key : ballsCount.keySet())
                {
                    // проверяем на то больше ли колличество шаров, чем в рюкзаке
                    if(ballsCount.get(key) > max.get(key))
                    {
                        line = reader.readLine();
                        continue cycle;
                    }
                }
            }

            result+= idsCount;
            // break;
            line = reader.readLine();
        }

        return result;
    }

    public static int SolutionPartTwo() throws IOException
    {
        Path path =Path.of("src/main/java/adventure/days/day_2/input.txt");

        BufferedReader reader = Files.newBufferedReader(path);
        
        int result = 0;//

        String line = reader.readLine();


        while (line != null) 
        {        
            int gamePower = 1;

            HashMap<String, Integer> max =new HashMap<>(); 
            max.put("red", 0);
            max.put("green", 0);
            max.put("blue", 0);

            String[] spplittedLine = line.split(":");// избавление от части game /d:

            line = spplittedLine[1];
            spplittedLine = line.split(";");// разделяем инры на сеты

            for(int i = 0; i < spplittedLine.length; i++)
            {       
                String[] checkString = spplittedLine[i].trim().replaceAll(",", "").split(" ");// сеты делим на массив из значений кол-во мячей-цвет


                for(int j = 0;j < checkString.length; j+= 2)//заполняем мапу
                {
                    if(max.get(checkString[j+1]) < Integer.parseInt(checkString[j]))
                    {
                        max.put(checkString[j+1], Integer.parseInt(checkString[j]));
                    }
                }
                
            }

            for(String key : max.keySet())
            {
                gamePower *=max.get(key);
            }

            result+= gamePower;

            line = reader.readLine();
        }



        return result;
    }
    
}
