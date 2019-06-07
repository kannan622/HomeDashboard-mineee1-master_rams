package com.demotxt.droidsrce.homedashboard;

public class roll_of_honour_model {

    private String membername;
    private String photo;

    private String role;

    private String businesssince_year;


    public roll_of_honour_model(String membername, String photo, String role, String businesssince_year) {
        // TODO Auto-generated constructor stub

        this.membername = membername;
        this.photo = photo;
        this.role = role;
        this.businesssince_year = businesssince_year;

    }

    public String getmembername() {
        return membername;
    }

    public void setmembername(String membername) {
        this.membername = membername;
    }

    public String getphoto() {
        return photo;
    }

    public void setphoto(String photo) {
        this.photo = photo;
    }


    public String getrole() {
        return role;
    }

    public void setrole(String role) {
        this.role = role;
    }


    public String getbusinesssince_year() {
        return businesssince_year;
    }

    public void setbusinesssince_year(String businesssince_year) {
        this.businesssince_year = businesssince_year;
    }


}
