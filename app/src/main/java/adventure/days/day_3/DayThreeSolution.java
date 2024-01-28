package adventure.days.day_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;

public class DayThreeSolution 
{
    // не эффективно потому что в  лоб сделано
    public static int PartOneSolution() throws IOException
    {
        Path path =Path.of("src/main/java/adventure/days/day_3/input.txt");

        BufferedReader reader = Files.newBufferedReader(path);
        
        int result = 0;
        
        HashSet<String> set = new HashSet<>(Arrays.asList("1","2","3","4","5","6","7","8","9","0"));
        

        String line1 = reader.readLine(); // первая строка
        String line2 = reader.readLine(); // вторая строка
        String line3 = reader.readLine(); // третья строка

        int lineLen = line1.length();

        StringBuilder builder = new StringBuilder();
        boolean isNumber = false;
        boolean isPartNumber = false;

        // проверка первой строки
        for(int i = 0; i < lineLen; i++)
        {
            if(set.contains(""+line1.charAt(i)))
            {
                isNumber = true;
                builder.append(line1.charAt(i));
                if(i==0)
                {
                    if(
                        (!set.contains(""+line2.charAt(i)) && line2.charAt(i)!='.')||  // проверка символов на нижней строке не являются ли они символами
                        (!set.contains(""+line2.charAt(i+1)) && line2.charAt(i+1)!='.')|| // проверка символов на нижней строке не являются ли они символами
                        (!set.contains(""+line1.charAt(i+1)) && line1.charAt(i+1)!='.')  // проверка соседних символов на своей строке, не являются ли они спец символами)
                    )
                    {
                        isPartNumber = true;
                    }    
                    continue;
                }

                if(
                    (!set.contains(""+line2.charAt(i)) && line2.charAt(i)!='.')||  // проверка символов на нижней строке не являются ли они символами
                    (!set.contains(""+line2.charAt(i+1)) && line2.charAt(i+1)!='.')|| // проверка символов на нижней строке не являются ли они символами
                    (!set.contains(""+line2.charAt(i-1)) && line2.charAt(i-1)!='.')|| // проверка символов на нижней строке не являются ли они символами
                    
                    (!set.contains(""+line1.charAt(i-1)) && line1.charAt(i-1)!='.')|| // проверка соседних символов на своей строке, не являются ли они спец символами
                    (!set.contains(""+line1.charAt(i+1)) && line1.charAt(i+1)!='.')   // проверка соседних символов на своей строке, не являются ли они спец символами
                )
                {
                    isPartNumber = true;
                    continue;
                }
            }

            if(isNumber && !set.contains(""+line1.charAt(i)) && isPartNumber)
            {
                result += Integer.parseInt(builder.toString());
                System.out.println(builder.toString());
                builder = new StringBuilder();
                isNumber = false;
                isPartNumber = false;
                continue;
            }
            
            if(isNumber && !set.contains(""+line1.charAt(i)) && !isPartNumber)
            {
                builder = new StringBuilder();
            }
        }

        builder = new StringBuilder();
        isNumber = false;
        isPartNumber = false;
        
        // проверка второй строки
        for(int i = 0; i < lineLen; i++)
        {
            if(set.contains(""+line2.charAt(i)))
            {
                isNumber = true;
                builder.append(line2.charAt(i));
                if(i==0)
                {
                    if(
                        (!set.contains(""+line1.charAt(i)) && line1.charAt(i)!='.')||     // проверка символов на верхней строке не являются ли они символами
                        (!set.contains(""+line1.charAt(i+1)) && line1.charAt(i+1)!='.')|| // проверка символов на верхней строке не являются ли они символами
                        
                        (!set.contains(""+line2.charAt(i+1)) && line1.charAt(i+1)!='.')||   // проверка соседних символов на своей строке, не являются ли они спец символами)
                        
                        (!set.contains(""+line3.charAt(i)) && line3.charAt(i)!='.')||     // проверка символов на нижней строке не являются ли они символами
                        (!set.contains(""+line3.charAt(i+1)) && line3.charAt(i+1)!='.')   // проверка символов на нижней строке не являются ли они символами
                    )
                    {
                        isPartNumber = true;
                    }    
                    continue;
                }

                if(i == lineLen-1){
                    if(
                        (!set.contains(""+line1.charAt(i)) && line1.charAt(i)!='.')||  // проверка символов на верхней строке не являются ли они символами
                        (!set.contains(""+line1.charAt(i-1)) && line1.charAt(i-1)!='.')|| // проверка символов на верхней строке не являются ли они символами
                    
                        (!set.contains(""+line2.charAt(i-1)) && line2.charAt(i-1)!='.')|| // проверка соседних символов на своей строке, не являются ли они спец символами
                        
                        (!set.contains(""+line3.charAt(i)) && line3.charAt(i)!='.')||       // проверка символов на нижней строке не являются ли они символами
                        (!set.contains(""+line3.charAt(i-1)) && line3.charAt(i-1)!='.') // проверка символов на нижней строке не являются ли они символами
                    )
                    {
                        isPartNumber = true;
                        continue;
                    }
            }

                if(
                    (!set.contains(""+line1.charAt(i)) && line1.charAt(i)!='.')||  // проверка символов на верхней строке не являются ли они символами
                    (!set.contains(""+line1.charAt(i+1)) && line1.charAt(i+1)!='.')|| // проверка символов на верхней строке не являются ли они символами
                    (!set.contains(""+line1.charAt(i-1)) && line1.charAt(i-1)!='.')|| // проверка символов на верхней строке не являются ли они символами

                    (!set.contains(""+line2.charAt(i-1)) && line2.charAt(i-1)!='.')|| // проверка соседних символов на своей строке, не являются ли они спец символами
                    (!set.contains(""+line2.charAt(i+1)) && line2.charAt(i+1)!='.')||   // проверка соседних символов на своей строке, не являются ли они спец символами

                    (!set.contains(""+line3.charAt(i)) && line3.charAt(i)!='.')||       // проверка символов на нижней строке не являются ли они символами
                    (!set.contains(""+line3.charAt(i+1)) && line3.charAt(i+1)!='.')|| // проверка символов на нижней строке не являются ли они символами
                    (!set.contains(""+line3.charAt(i-1)) && line3.charAt(i-1)!='.') // проверка символов на нижней строке не являются ли они символами
                )
                {
                    isPartNumber = true;
                    continue;
                }
            }

            if(isNumber && !set.contains(""+line2.charAt(i)) && isPartNumber)
            {
                result += Integer.parseInt(builder.toString());
                System.out.println(builder.toString());
                builder = new StringBuilder();
                isNumber = false;
                isPartNumber = false;
                continue;
            }

            if(isNumber && !set.contains(""+line2.charAt(i)) && !isPartNumber)
            {
                builder = new StringBuilder();
            }
        }

        builder = new StringBuilder();
        isNumber = false;
        isPartNumber = false;

        // проверка третьей строки
        for(int i = 0; i < lineLen; i++)
        {
            if(set.contains(""+line3.charAt(i)))
            {
                isNumber = true;
                builder.append(line3.charAt(i));
                if(i==0)
                {
                    if(
                        (!set.contains(""+line2.charAt(i)) && line2.charAt(i)!='.')||     // проверка символов на верхней строке не являются ли они символами
                        (!set.contains(""+line2.charAt(i+1)) && line2.charAt(i+1)!='.')|| // проверка символов на верхней строке не являются ли они символами
                        (!set.contains(""+line3.charAt(i+1)) && line3.charAt(i+1)!='.')   // проверка соседних символов на своей строке, не являются ли они спец символами
                    )
                    {
                        isPartNumber = true;
                    }    
                    continue;
                }

                if(
                    (!set.contains(""+line2.charAt(i)) && line2.charAt(i)!='.')||     // проверка символов на верхней строке не являются ли они символами
                    (!set.contains(""+line2.charAt(i+1)) && line2.charAt(i+1)!='.')|| // проверка символов на верхней строке не являются ли они символами
                    (!set.contains(""+line2.charAt(i-1)) && line2.charAt(i-1)!='.')|| // проверка символов на верхней строке не являются ли они символами

                    (!set.contains(""+line3.charAt(i-1)) && line3.charAt(i-1)!='.')|| // проверка соседних символов на своей строке, не являются ли они спец символами
                    (!set.contains(""+line3.charAt(i+1)) && line3.charAt(i+1)!='.')   // проверка соседних символов на своей строке, не являются ли они спец символами
                )
                {
                    isPartNumber = true;
                    continue;
                }
            }

            if(isNumber && !set.contains(""+line3.charAt(i)) && isPartNumber)
            {
                result += Integer.parseInt(builder.toString());
                System.out.println(builder.toString());
                builder = new StringBuilder();
                isNumber = false;
                // isPartNumber = false;
                continue;
            }

            if(isNumber && !set.contains(""+line3.charAt(i)) && !isPartNumber)
            {
                builder = new StringBuilder();
            }
        }

        // --------
        // Начало цикла
        // --------
        line1 = line3;
        line2 = reader.readLine();
        line3 = reader.readLine();
        while (line1 != null) 
        {
            if(line2 == null)
            {
                break;
            }
            if(line3 == null)
            {
                for(int i = 0; i < lineLen; i++)
                {
                    if(set.contains(""+line2.charAt(i)))
                    {
                        isNumber = true;
                        builder.append(line2.charAt(i));
                        if(i==0)
                        {
                            if(
                                (!set.contains(""+line1.charAt(i)) && line1.charAt(i)!='.')||  // проверка символов на нижней строке не являются ли они символами
                                (!set.contains(""+line1.charAt(i+1)) && line1.charAt(i+1)!='.')|| // проверка символов на нижней строке не являются ли они символами
                                
                                (!set.contains(""+line2.charAt(i+1)) && line2.charAt(i+1)!='.')  // проверка соседних символов на своей строке, не являются ли они спец символами)
                            )
                            {
                                isPartNumber = true;
                            }    
                            continue;
                        }
        
                        if((!set.contains(""+line1.charAt(i)) && line1.charAt(i)!='.')||  // проверка символов на верхней строке не являются ли они символами
                        (!set.contains(""+line1.charAt(i+1)) && line1.charAt(i+1)!='.')|| // проверка символов на верхней строке не являются ли они символами
                        (!set.contains(""+line1.charAt(i-1)) && line1.charAt(i-1)!='.')|| // проверка символов на верхней строке не являются ли они символами
                        
                        (!set.contains(""+line2.charAt(i-1)) && line2.charAt(i-1)!='.')|| // проверка соседних символов на своей строке, не являются ли они спец символами
                        (!set.contains(""+line2.charAt(i+1)) && line2.charAt(i+1)!='.')   // проверка соседних символов на своей строке, не являются ли они спец символами
                        )
                        {
                            isPartNumber = true;
                            continue;
                        }
                    }
        
                    if(isNumber && !set.contains(""+line2.charAt(i)) && isPartNumber)
                    {
                        result += Integer.parseInt(builder.toString());
                        System.out.println(builder.toString());
                        builder = new StringBuilder();
                        isNumber = false;
                        isPartNumber = false;
                        continue;
                    }
                    
                    if(isNumber && !set.contains(""+line2.charAt(i)) && !isPartNumber)
                    {
                        builder = new StringBuilder();
                    }
                }
                break;
            }


            // проверка второй строки
            for(int i = 0; i < lineLen; i++)
            {
            if(set.contains(""+line2.charAt(i)))
            {
                isNumber = true;
                builder.append(line2.charAt(i));
                if(i==0)
                {
                    if(
                        (!set.contains(""+line1.charAt(i)) && line1.charAt(i)!='.')||     // проверка символов на верхней строке не являются ли они символами
                        (!set.contains(""+line1.charAt(i+1)) && line1.charAt(i+1)!='.')|| // проверка символов на верхней строке не являются ли они символами
                        
                        (!set.contains(""+line2.charAt(i+1)) && line1.charAt(i+1)!='.')||   // проверка соседних символов на своей строке, не являются ли они спец символами)
                        
                        (!set.contains(""+line3.charAt(i)) && line3.charAt(i)!='.')||     // проверка символов на нижней строке не являются ли они символами
                        (!set.contains(""+line3.charAt(i+1)) && line3.charAt(i+1)!='.')   // проверка символов на нижней строке не являются ли они символами
                    )
                    {
                        isPartNumber = true;
                    }    
                    continue;
                }

                if(
                    (!set.contains(""+line1.charAt(i)) && line1.charAt(i)!='.')||  // проверка символов на верхней строке не являются ли они символами
                    (!set.contains(""+line1.charAt(i+1)) && line1.charAt(i+1)!='.')|| // проверка символов на верхней строке не являются ли они символами
                    (!set.contains(""+line1.charAt(i-1)) && line1.charAt(i-1)!='.')|| // проверка символов на верхней строке не являются ли они символами

                    (!set.contains(""+line2.charAt(i-1)) && line2.charAt(i-1)!='.')|| // проверка соседних символов на своей строке, не являются ли они спец символами
                    (!set.contains(""+line2.charAt(i+1)) && line2.charAt(i+1)!='.')||   // проверка соседних символов на своей строке, не являются ли они спец символами
                    
                    (!set.contains(""+line3.charAt(i)) && line3.charAt(i)!='.')||       // проверка символов на нижней строке не являются ли они символами
                    (!set.contains(""+line3.charAt(i+1)) && line3.charAt(i+1)!='.')|| // проверка символов на нижней строке не являются ли они символами
                    (!set.contains(""+line3.charAt(i-1)) && line3.charAt(i-1)!='.') // проверка символов на нижней строке не являются ли они символами
                )
                {
                    isPartNumber = true;
                    continue;
                }
            }

            if(isNumber && !set.contains(""+line2.charAt(i)) && isPartNumber)
            {
                result += Integer.parseInt(builder.toString());
                System.out.println(builder.toString());
                builder = new StringBuilder();
                isNumber = false;
                isPartNumber = false;
                continue;
            }

            if(isNumber && !set.contains(""+line2.charAt(i)) && !isPartNumber)
            {
                builder = new StringBuilder();
            }
        }

            builder = new StringBuilder();
            isNumber = false;
            isPartNumber = false;

            // проверка третьей строки
            for(int i = 0; i < lineLen; i++)
            {
            if(set.contains(""+line3.charAt(i)))
            {
                isNumber = true;
                builder.append(line3.charAt(i));
                if(i==0)
                {
                    if(
                        (!set.contains(""+line2.charAt(i)) && line2.charAt(i)!='.')||     // проверка символов на верхней строке не являются ли они символами
                        (!set.contains(""+line2.charAt(i+1)) && line2.charAt(i+1)!='.')|| // проверка символов на верхней строке не являются ли они символами
                        (!set.contains(""+line3.charAt(i+1)) && line3.charAt(i+1)!='.')   // проверка соседних символов на своей строке, не являются ли они спец символами
                    )
                    {
                        isPartNumber = true;
                    }    
                    continue;
                }

                if(
                    (!set.contains(""+line2.charAt(i)) && line2.charAt(i)!='.')||     // проверка символов на верхней строке не являются ли они символами
                    (!set.contains(""+line2.charAt(i+1)) && line2.charAt(i+1)!='.')|| // проверка символов на верхней строке не являются ли они символами
                    (!set.contains(""+line2.charAt(i-1)) && line2.charAt(i-1)!='.')|| // проверка символов на верхней строке не являются ли они символами

                    (!set.contains(""+line3.charAt(i-1)) && line3.charAt(i-1)!='.')|| // проверка соседних символов на своей строке, не являются ли они спец символами
                    (!set.contains(""+line3.charAt(i+1)) && line3.charAt(i+1)!='.')   // проверка соседних символов на своей строке, не являются ли они спец символами
                )
                {
                    isPartNumber = true;
                    continue;
                }
            }

            if(isNumber && !set.contains(""+line3.charAt(i)) && isPartNumber)
            {
                result += Integer.parseInt(builder.toString());
                System.out.println(builder.toString());
                builder = new StringBuilder();
                isNumber = false;
                isPartNumber = false;
                continue;
            }

            if(isNumber && !set.contains(""+line3.charAt(i)) && !isPartNumber)
            {
                builder = new StringBuilder();
            }
        } 
        
            line1 = line3;
            line2 = reader.readLine();
            line3 = reader.readLine();
        }

        return result;
    }
}
