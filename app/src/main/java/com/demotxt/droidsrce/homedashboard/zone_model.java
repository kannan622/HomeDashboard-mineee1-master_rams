package com.demotxt.droidsrce.homedashboard;

public class zone_model {

    private String firstname;
    private String position;

    private String address;

    private String phonenumber;


    public zone_model(String firstname, String position, String address, String phonenumber) {
        // TODO Auto-generated constructor stub

        this.firstname = firstname;
        this.position = position;
        this.address = address;
        this.phonenumber = phonenumber;

    }

    public String getfirstname() {
        return firstname;
    }

    public void setfirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getposition() {
        return position;
    }

    public void setposition(String position) {
        this.position = position;
    }


    public String getaddress() {
        return address;
    }

    public void setaddress(String address) {
        this.address = address;
    }


    public String getphonenumber() {
        return phonenumber;
    }

    public void setphonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }


}
