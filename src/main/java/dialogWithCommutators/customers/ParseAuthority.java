package dialogWithCommutators.customers;

public class ParseAuthority {
    public Boolean[] transcoding(String line) {
        char[] items = line.toCharArray();
        Boolean[] statusAuthority = new Boolean[items.length];
        for (int i = 0; i < 5; i++) {
            if (items[i] == '0') statusAuthority[1] = false;
            else statusAuthority[i] = true;
        }
        return statusAuthority;
    }
}
