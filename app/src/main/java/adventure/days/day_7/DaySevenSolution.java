package adventure.days.day_7;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;

public class DaySevenSolution {
    
    // мапа нужная для присваивания к масти карты её номера(2=2, 3=3,..., K=13, A=14)
    public static Map<String, Integer> cardsPowerMap = new HashMap<String, Integer>();
    static{
        cardsPowerMap.put("A", 14);
            cardsPowerMap.put("K", 13);
            cardsPowerMap.put("Q", 12);
            cardsPowerMap.put("J", 11);
            cardsPowerMap.put("T", 10);
            cardsPowerMap.put("9", 9);
            cardsPowerMap.put("8", 8);
            cardsPowerMap.put("7", 7);
            cardsPowerMap.put("6", 6);
            cardsPowerMap.put("5", 5);
            cardsPowerMap.put("4", 4);
            cardsPowerMap.put("3", 3);
            cardsPowerMap.put("2", 2);
    }

    public static int partOneSolution() throws IOException
    {
        Path path = Path.of("src/main/java/adventure/days/day_7/input.txt");

        BufferedReader reader = Files.newBufferedReader(path);

        String line = reader.readLine();

        ArrayList<Combo> noParis = new ArrayList<>(); // массив для записи комбинций со всеми разными картами
        ArrayList<Combo> onePair = new ArrayList<>(); // массив для записи комбинций с одной парой
        ArrayList<Combo> twoParis = new ArrayList<>(); // массив для записи комбинций с двумя парами
        ArrayList<Combo> fullHouses = new ArrayList<>(); // массив для записи комбинций с фулл хаусом 
        ArrayList<Combo> threeParis = new ArrayList<>(); // массив для записи комбинций с тремя одинковыми картами
        ArrayList<Combo> fourParis = new ArrayList<>(); // массив для записи комбинций с 4-мя одинаковыми картами
        ArrayList<Combo> fiveParis = new ArrayList<>(); // массив для записи комбинций с 5-ю одинаковыми картами 

        String[] cards = {"A", "K", "Q", "J", "T", "9", "8", "7", "6", "5", "4", "3", "2"};

        

        while (line != null) {
            
            Combo combo = new Combo();

            combo.comboString = line.split(" ")[0].trim();
            combo.comboPower = Integer.parseInt(line.split(" ")[1]);

            cycle:for(int i = 0; i < cards.length; i++){

                // проверка на то является ли пятью одинаковыми картами
                // если при замене карты на пустоту длина строки стала равна нулю, значит все карты одинаковые
                if((combo.comboString.replaceAll(cards[i], "").length()) == 0){
                    // System.out.println("five same " + combo.comboString);
                    combo.compareCard = cards[i];
                    combo.compareCardPower = cardsPowerMap.get(cards[i]);
                    fiveParis.add(combo);
                    break;
                }

                // проверка на то является ли комбинация 4-мя одинаковыми картами
                if((combo.comboString.replaceAll(cards[i], "").length()) == 1)
                {
                    // System.out.println("four same " + combo.comboString);
                    combo.compareCard = cards[i];
                    combo.compareCardPower = cardsPowerMap.get(cards[i]);
                    fourParis.add(combo);
                    break;
                }

                // проверка на то является ли комбинация фул-хаусом
                if((combo.comboString.replaceAll(cards[i], "").length()) == 2)
                {
                    String fullhouseCheck = combo.comboString.replaceAll(cards[i], "");
                    if(fullhouseCheck.charAt(0) == fullhouseCheck.charAt(1))
                    {
                        // System.out.println("fullhouse " + combo.comboString);
                        combo.compareCard = cards[i];
                        combo.compareCardPower = cardsPowerMap.get(cards[i]);
                        fullHouses.add(combo);
                        break cycle;
                    }
                }
                if((combo.comboString.replaceAll(cards[i], "").length()) == 3)
                {
                    String fullhouseCheck = combo.comboString.replaceAll(cards[i], "");
                    if((fullhouseCheck.charAt(0) == fullhouseCheck.charAt(1)) && (fullhouseCheck.charAt(0) == fullhouseCheck.charAt(2)))
                    {
                        // System.out.println("fullhouse " + combo.comboString);
                        combo.compareCard = cards[i];
                        combo.compareCardPower = cardsPowerMap.get(cards[i]);
                        fullHouses.add(combo);
                        break cycle;
                    }
                }
                // проверка на фул-хаус окончена

                // проверка на то является ли комбинация 3-мя одниковыми картками 
                if(combo.comboString.replaceAll(cards[i], "").length() == 2)
                {
                    // System.out.println("three same " + combo.comboString);
                    combo.compareCard = cards[i];
                    combo.compareCardPower = cardsPowerMap.get(cards[i]);
                    threeParis.add(combo);
                    break;
                }

                // проверка на то явялется ли кобниция 2-мя парами
                // так же проверка на то является ли комбинация одной парой, если не двумя парами
                // если не нашел вторую пару, то пара одна
                if(combo.comboString.replaceAll(cards[i], "").length() == 3)
                {
                    for(int j = i+1; j < cards.length; j++)
                    {
                        // проверка на то есть ли вторая пара
                        if(combo.comboString.replaceAll(cards[j], "").length() == 3){
                            // System.out.println("two pairs "+combo.comboString);
                            twoParis.add(combo);
                            if(cardsPowerMap.get(cards[j]) > cardsPowerMap.get(cards[i]))
                            {
                                combo.compareCard = cards[j];
                                combo.compareCardPower = cardsPowerMap.get(cards[j]);
                                break cycle;
                            }
                            combo.compareCard = cards[i];
                            combo.compareCardPower = cardsPowerMap.get(cards[i]);
                            break cycle;
                        }
                    }

                    // System.out.println("one pair "+combo.comboString);
                    combo.compareCard = cards[i];
                    combo.compareCardPower = cardsPowerMap.get(cards[i]);
                    onePair.add(combo);
                    break;
                }

                // если ВСЕ проверки по ВСЕМ картам провалиться, то пар нет 
                if((i == cards.length-1)){
                    // System.out.println("no pairs "+combo.getComboString());
                    combo.compareCard = combo.comboString.substring(0, 1);
                    combo.compareCardPower = cardsPowerMap.get(combo.comboString.substring(0, 1));
                    noParis.add(combo);
                    break;
                }

            }

            

            // Collections.sort(noParis, Comparator.comparingInt(Combo::getCompareCardPower));
            // Collections.sort(onePair, Comparator.comparingInt(Combo::getCompareCardPower));
            // Collections.sort(twoParis, Comparator.comparingInt(Combo::getCompareCardPower));
            // Collections.sort(fullHouses, Comparator.comparingInt(Combo::getCompareCardPower));
            // Collections.sort(threeParis, Comparator.comparingInt(Combo::getCompareCardPower));
            // Collections.sort(fourParis, Comparator.comparingInt(Combo::getCompareCardPower));
            // Collections.sort(fiveParis, Comparator.comparingInt(Combo::getCompareCardPower));

            Collections.sort(noParis);
            Collections.sort(onePair);
            Collections.sort(twoParis);
            Collections.sort(fullHouses);
            Collections.sort(threeParis);
            Collections.sort(fourParis);
            Collections.sort(fiveParis);

            line = reader.readLine();
        }

        // System.out.println("No pairs");
        //     for(int i = 0; i < noParis.size(); i++)
        //     {
        //         System.out.println(noParis.get(i).comboString);
        //     }
        //     System.out.println();

        //     System.out.println("onePair");
        //     for(int i = 0;  i < onePair.size();i++){
        //         System.out.println(onePair.get(i).comboString);
        //     }
        //     System.out.println("");
        //     System.out.println("twoPairs");
        //     for(int i = 0; i < twoParis.size() ;i++){
        //         System.out.println(twoParis.get(i).comboString);
        //     }
        //     System.out.println();
        //     System.out.println("fullHouse");
        //     for(int i = 0; i < fullHouses.size() ;i++){
        //         System.out.println(fullHouses.get(i).comboString);
        //     }
        //     System.out.println();
        //     System.out.println("threePairs");
        //     for(int i = 0; i < threeParis.size() ;i++){
        //         System.out.println(threeParis.get(i).comboString);
        //     }
        //     System.out.println();
        //     System.out.println("fourPairs");
        //     for(int i = 0; i < fourParis.size() ;i++){
        //         System.out.println(fourParis.get(i).comboString);
        //     }
        //     System.out.println();
        //     System.out.println("fiveParis");
        //     for(int i = 0; i < fiveParis.size() ;i++){
        //         System.out.println(fiveParis.get(i).comboString);
        //     }
        //     System.out.println();

        ArrayList<Combo> resultArray = new ArrayList<>();

        resultArray.addAll(noParis);
        resultArray.addAll(onePair);
        resultArray.addAll(twoParis);
        resultArray.addAll(threeParis);
        resultArray.addAll(fullHouses);
        resultArray.addAll(fourParis);
        resultArray.addAll(fiveParis);

        int result = 0;
        System.out.println(fullHouses.size());
        for(int i = 0; i < resultArray.size(); i++)
        {
            // System.out.println(resultArray.get(i).getComboString());
            // System.out.println(resultArray.get(i).getComboPower());
            result += resultArray.get(i).getComboPower() * (i+1);
        }
        return result;
    }

    
}
