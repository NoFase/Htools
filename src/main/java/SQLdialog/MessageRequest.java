package SQLdialog;

public class MessageRequest {
    private String message;

    public String allPraUser(){
        message = "SELECT * FROM tbl_PRAUserData";
        return message;
    }
}
