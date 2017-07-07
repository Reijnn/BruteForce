package com.reijnn;

import java.util.ArrayList;

/**
 * Created by niels on 07/07/2017.
 */
public class Generator {

    public Generator() {
    }

    public ArrayList<String> generateCombinations(int arraySize, ArrayList<String> possibleValues) {
        int carry;
        ArrayList<String> list = new ArrayList();
        String b = "";

        int[] indices = new int[arraySize];
        do {
            for (int index : indices) {
                b += possibleValues.get(index);
            }
            list.add(b);
            b = "";

            carry = 1;
            for (int i = indices.length - 1; i >= 0; i--) {
                if (carry == 0)
                    break;

                indices[i] += carry;
                carry = 0;

                if (indices[i] == possibleValues.size()) {
                    carry = 1;
                    indices[i] = 0;
                }
            }
        }
        while (carry != 1);
        return list;
    }
}