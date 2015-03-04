package org.test.chesstask.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Utilities {

    private Utilities() {}

    public static <T> Collection<T[]> permutations(T[] a, int n) {
        List<T[]> result = new ArrayList<T[]>();

        if (n == 1) {
            result.add(Arrays.copyOf(a, a.length));
        } else {
            for (int i = 0; i < n; i++) {
                swap(a, i, n - 1);
                for (T[] t : permutations(a, n - 1)) {
                    if (!contains(result, t)) {
                        result.add(t);
                    }
                }
                swap(a, i, n - 1);
            }
        }
        return result;
    }

    public static <T> void swap(T[] a, int i, int j) {
        T c;
        c = a[i];
        a[i] = a[j];
        a[j] = c;
    }

    public static <T> boolean contains(Collection<T[]> in, T[] what) {
        for (T[] t : in) {
            if (Arrays.equals(t, what)) {
                return true;
            }
        }
        return false;
    }
}
