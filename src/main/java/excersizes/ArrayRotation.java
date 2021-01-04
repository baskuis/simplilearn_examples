package excersizes;

public class ArrayRotation {

    public static void rotateArray() {

        String[] items = {
                "tree",
                "house",
                "boat",
                "cup",
                "water"
        };
        String[] rotatedItems = new String[items.length];

        int rotateBy = 11;

        for (int i = 0; i < items.length; i++) {
            String item = items[i];
            int newIndex = i + rotateBy;
            newIndex = newIndex % items.length;
//            if (newIndex > items.length - 1) {
//                newIndex = newIndex - items.length;
//            }
            rotatedItems[newIndex] = item;
        }

        System.out.println("original array ");
        for (String original : items) {
            System.out.println(original);
        }

        System.out.println("============");

        System.out.println("rotated by " + rotateBy);
        for (String rotated : rotatedItems) {
            System.out.println(rotated);
        }

        /**
         * [0] -- tree
         * [1] -- house
         * [2] -- boat
         * [3] -- cup
         * [4] -- water
         *
         * We want 'house' to be the first entry in the array
         * - tree will have to move to position 4
         */

        System.out.println("Hi - ready to go");

    }

}
