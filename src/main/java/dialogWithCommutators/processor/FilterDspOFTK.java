package dialogWithCommutators.processor;

public class FilterDspOFTK {
    private String value;
    private String[] data = new String[2];

    public FilterDspOFTK(String value) {
        this.value = value;
    }

    public String[] getData() {
        if (value.contains("Free") || value.contains("Busy") || value.contains("Block") || value.contains("Fault")) {
            data[0] = value.split("\\s+")[1];
            data[1] = value.split("\\s+")[2];
        } else if (value.contains("Installation number")){
            data[0] = value.split("\\s+")[1] + " " + value.split("\\s+")[2];
            data[1] = value.split("\\s+")[3];
        }
        if (data[0] != null) {
            System.out.println("===> FilterDspOFTK ---> method getData --> data: " + data[0] + " " + data[1]);
            return data;
        }else {
            return data;
        }
    }
}
