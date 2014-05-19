/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connectfour.ki;

import connectfour.model.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Tom
 */
public class KIHard implements KI {

    @Override
    public int getNextStone(int[][] gameField, Player you, Player enemy) throws FieldIsFullException {
        List<Integer> columns = returnNotFullColumn(gameField);
        if (columns.size() == 0) {
            throw new FieldIsFullException();
        }
        return chooseColumn(columns);
    }

    private List<Integer> returnNotFullColumn(int[][] gameField) {
        List<Integer> columns = new ArrayList<>();

        for (int c = 0; c < gameField.length; c++) {
            int rowCount = gameField[c].length;

            if (gameField[c][rowCount - 1] == 0) {  //Prüfung für leeres Feld
                columns.add(c + 1);
            }
        }
        return columns;
    }

    private int chooseColumn(List<Integer> columns) {
        Random random = new Random();
        System.out.println(columns.size());
        int n = random.nextInt(columns.size());
        return columns.get(n);
    }
}
