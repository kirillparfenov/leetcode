package dev.parfenov.leectode.medium;

// https://leetcode.com/problems/basic-calculator-ii/description/

import java.util.Map;
import java.util.Stack;

/**
 * Идея решения: Сделать 2 стека - для чисел и операторов. Сделать список приоритетов операций.
 * При проходе по выражению заглядывать на шаг назад в стек операторов.
 * Если в стеке операторов лежит более приоритетный оператор - выполняем операции.
 * <br>
 * "3+2*2" -> 7
 * <br>
 * " 3+5 / 2 " -> 5
 */
public class Problem227_Basic_Calculator {
    public static void main(String[] args) {
//        var s = " 3+5 / 2 ";
//        var s = "3+2*2";
        var s = "1+2*5/3+6/4*2";
        System.out.println(calculate(s));
    }

    private static int calculate(String s) {
        //операторы и их приоритеты
        var opPriority = Map.of(
                '+', 0,
                '-', 0,
                '*', 1,
                '/', 1
        );

        var opStack = new Stack<Character>();//стек операторов
        var numStack = new Stack<Integer>();//стек чисел

        for (int i = 0; i < s.length(); i++) {
            var c = s.charAt(i);

            if (Character.isDigit(c)) {
                //сканим число из символов
                // и позицию, на которую передвинулось i при скане
                var numAndPos = getNumAndPos(s, i);
                numStack.push(numAndPos[0]);
                i = numAndPos[1] - 1;

            } else if (opPriority.containsKey(c)) {
                //вычисляем выражение и кладем результат в numStack:
                // 1) пока стек с операторами не опустел
                // 2) и приоритет оператора в стеке выше/равен
                //приоритету текущего оператора
                while (!opStack.isEmpty()
                        && opPriority.get(opStack.peek()) >= opPriority.get(c))
                    calcFromStack(opStack, numStack);

                opStack.push(c);
            }
        }

        //если в стеке с операторами есть еще какие-то значения
        while (!opStack.isEmpty())
            calcFromStack(opStack, numStack);

        //в стеке остался результат вычислений
        return numStack.pop();
    }

    private static int[] getNumAndPos(String s, int i) {
        int k = i;
        i = i + 1;

        while (i < s.length() && Character.isDigit(s.charAt(i)))
            i++;

        return new int[]{Integer.parseInt(s.substring(k, i)), i};
    }

    private static void calcFromStack(Stack<Character> opStack, Stack<Integer> numStack) {
        var v2 = numStack.pop();
        var v1 = numStack.pop();

        var op = opStack.pop();

        switch (op) {
            case '+':
                numStack.push(v1 + v2);
                break;
            case '-':
                numStack.push(v1 - v2);
                break;
            case '*':
                numStack.push(v1 * v2);
                break;
            case '/':
                numStack.push(v1 / v2);
                break;
        }
    }
}
