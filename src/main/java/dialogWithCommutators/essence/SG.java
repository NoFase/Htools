package dialogWithCommutators.essence;

public class SG implements Essence{
    private int id;
    private String sgName;
    private String equipmentId;

    public SG(int id, String sgName, String equipmentId) {
        this.id = id;
        this.sgName = sgName;
        this.equipmentId = equipmentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSgName() {
        return sgName;
    }

    public void setSgName(String sgName) {
        this.sgName = sgName;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    @Override
    public String returnStringInDB(String tblName) {
        return "insert into "
                .concat(tblName)
                .concat(" (id, sgName, equipmentId) values (")
                .concat(id + ", '")
                .concat(sgName)
                .concat("', '")
                .concat(equipmentId)
                .concat("');");
    }
}
