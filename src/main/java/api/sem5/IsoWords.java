package api.sem5;

import java.util.HashMap;
import java.util.Stack;

public class IsoWords {
    public static void main(String[] args) {
        System.out.println(task3("<{a}+{(d*3)}>"));
        System.out.println(task3("{a+]}{(d*3)}"));
    }


        /*<aside>
📔 **Текст задачи:**
Написать программу, определяющую правильность расстановки скобок в выражении.

Пример 1: (a+(d*3)) - истина

Пример 2: [a+(1*3) - ложь

Пример 3: [6+(3*3)] - истина

Пример 4: {a}[+]{(d*3)} - истина

Пример 5: <{a}+{(d*3)}> - истина

Пример 6: {a+]}{(d*3)} - ложь

</aside>*/
    static boolean task3(String str){
        HashMap<Character, Character> newMap = createMap();
        Stack<Character> stack = new Stack<>();
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if(newMap.containsKey(c[i])){
                stack.push(c[i]);
            }
            if(newMap.containsValue(c[i])){
                if(stack.isEmpty() || c[i] != newMap.get(stack.pop())){
                    return false;
                }
            }
        }
        if(!stack.isEmpty()) return false;
        return true;
    }

    static HashMap<Character, Character> createMap() {
        HashMap<Character, Character> newMap = new HashMap<>();
        newMap.put('(', ')');
        newMap.put('[', ']');
        newMap.put('<', '>');
        newMap.put('{', '}');
        return newMap;
    }
}
