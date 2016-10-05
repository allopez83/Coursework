class EmailParse {
    public static void main (String[] args) {
        String input = "X-To: joe@gmail.com";

        // String input = "X-To: jskilli@ENRON.COM";

        String [] xTo = input.split("@");

// for (String split : xTo) {
//     if (split == xTo[0]) continue;
//     domain = split.substring(0, split.indexOf('\''));
//     System.out.println(domain);
// }

        String line = input;
        int end = -1;

        if (line.contains("X-To:")) {
            String [] addresses = line.split("@");
            for (String address : addresses) {
                if (address == addresses[0]) continue;

                // if (address.contains(","))
                //     end = address.indexOf(",") - 1;
                // else if (address.contains("\'"))
                //     end = address.indexOf("\'");
                // else if (address.contains(">"))
                //     end = address.indexOf(">");
                // else if (address.contains(" "))
                //     end = address.indexOf(" ");
                // else
                //     end = address.length();
                // if (address.contains("."))
                //     end = address.indexOf(".") + 4;

                System.out.println(address);
                char [] carr = address.toCharArray();
                String domain = "";
                for (char c : carr) {
                    if ((c+"").matches("[a-zA-Z.]+")) {
                        // System.out.println("match");
                        domain += c;
                    }
                    else {
                        // System.out.println("Break!");
                        break;
                    }
                }
                System.out.println(domain);
            }
        }

        // int i = 'm';
        // System.out.println('m' < 'n');
        // System.out.println('m' < 'l');
        // System.out.println('m' >= 'm');
        // System.out.println('m' >= 'l');
        // System.out.println('m' >= 'n');


        System.out.println(mVal("l"));
        System.out.println(mVal("m"));
        System.out.println(mVal("n"));
        System.out.println((int)'a');

        // String to = Text.toString()
        // if to.
        // to.split
    }

    private static int mVal(String s) {
        char first = s.charAt(0);
        if ('m' < first) // N-Z
            return 1;
        else if ('m' >= first) // A-M
            return 0;
        else
            return -2;
    }
}