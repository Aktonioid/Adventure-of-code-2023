package adventure.days.day_7;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// класс для указывания особенностей комбинации

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Combo implements Comparable<Combo>{
    String comboString;
    int comboPower; // те цифры, которые идут после комбинации карт
    int compareCardPower; // какая карта далее приведены значения нужно просто для удобства сортировки
                            // 2 - 2, 3-3, ..., K-13, A-14
    String compareCard; // какая карта является самой сильной в руке(В целом можно и без этого, прост визуально будет удобней)
    @Override
    
    public int compareTo(Combo o) {
        for(int i = 0; i < comboString.length(); i++){
            int cardPower = DaySevenSolution.cardsPowerMap.get(comboString.substring(i, i+1));
            int cardPowerO = DaySevenSolution.cardsPowerMap.get(o.comboString.substring(i, i+1));
            if(cardPower != cardPowerO)
                {
                    return cardPower - cardPowerO;
                }
        }
        return 0;
    }
}
