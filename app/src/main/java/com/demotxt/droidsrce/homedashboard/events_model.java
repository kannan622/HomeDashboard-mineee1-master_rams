package com.demotxt.droidsrce.homedashboard;

public class events_model {

    private String title;
    private String date;

    private String venue;

    private String meeting_chairman;

    private String mobile_no_meeting_chairman;
    private String co_chairman;


    public events_model(String title, String date, String venue, String meeting_chairman,String mobile_no_meeting_chairman,String co_chairman) {
        // TODO Auto-generated constructor stub

        this.title = title;
        this.date = date;
        this.venue = venue;
        this.meeting_chairman = meeting_chairman;
        this.mobile_no_meeting_chairman = mobile_no_meeting_chairman;
        this.co_chairman = co_chairman;

    }

    public String gettitle() {
        return title;
    }

    public void settitle(String title) {
        this.title = title;
    }

    public String getdate() {
        return date;
    }

    public void setdate(String date) {
        this.date = date;
    }


    public String getvenue() {
        return venue;
    }

    public void setvenue(String venue) {
        this.venue = venue;
    }


    public String getmeeting_chairman() {
        return meeting_chairman;
    }

    public void setmeeting_chairman(String meeting_chairman) {
        this.meeting_chairman = meeting_chairman;
    }

    public String getmobile_no_meeting_chairman() {
        return mobile_no_meeting_chairman;
    }

    public void setmobile_no_meeting_chairman(String mobile_no_meeting_chairman) {
        this.mobile_no_meeting_chairman = mobile_no_meeting_chairman;
    }

    public String getco_chairman() {
        return co_chairman;
    }

    public void setco_chairman(String co_chairman) {
        this.co_chairman = co_chairman;
    }

}
