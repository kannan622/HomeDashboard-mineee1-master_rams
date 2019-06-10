package com.demotxt.droidsrce.homedashboard;

public class sponsorsmodel {

    private String Name;
    private String link;
    private String Image;

    public sponsorsmodel(String Name, String link, String Image) {
        this.Name = Name;
        this.link = link;
        this.Image = Image;
    }

    public String getName() {
        return Name;
    }

    public String getlink() {
        return link;
    }

    public String getImage() {
        return Image;
    }
}
