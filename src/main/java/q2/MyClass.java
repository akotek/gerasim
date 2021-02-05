package q2;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MyClass {
    private Date time;
    private String name;
    private List<Long> numbers;
    private List<String> strings;

    public MyClass(Date time, String name, List<Long> numbers, List<String> strings) {
        this.time = time;
        this.name = name;
        this.numbers = numbers;
        this.strings = strings;
    }

//    1. for current use-case (removeString) - no need to override
//    2. no need to use instanceof
//    3. no need to downcast
//    public boolean equals(Object obj) {
//        if (obj instanceof MyClass) {
//            return name.equals(((MyClass) obj).name);
//        }
//        return false;
//    }

    public String toString() {
        // 1. use StringBuilder for efficiency:
//        String out = name;
//        for (long item : numbers) {
//            out += " " + item;
//        }
//        return out;
        StringBuilder out = new StringBuilder(name);
        for (long item : numbers) {
            out.append(" ").append(item);
        }
        return out.toString();
    }

    public void removeString(String str) {
        // 1. algorithm is wrong @ see test (arrayList changes idx)
        // 2. NPE if remove then idx > current.size
//        for (int i = 0; i < strings.size(); i++) {
//            if (strings.get(i).equals(str)) {
//                strings.remove(i);
//            }
        // alternatively use 'stream.filter with f=!equals(str)'
        Iterator<String> iter = strings.iterator();
        while (iter.hasNext()) {
            if (iter.next().equals(str)) {
                iter.remove();
            }
        }
    }

    public boolean containsNumber(long number) {
        // 1. == is reference, equals is value comparison
        // 2. check long(primitive) and not Long(object)
        // 3. use existing contains()
//        for (long num : numbers) {
//            if (num == number) {
//                return true;
//            }
//        }
//        return false;
        return numbers.contains(number);
    }

    public boolean isHistoric() {
        // 1. NPE, nil guard
        // return time.before(new Date());
        return time != null && time.before(new Date());
    }

    public List<String> getStrings() {
        return strings;
    }
}
