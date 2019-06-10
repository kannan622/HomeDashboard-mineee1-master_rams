package com.demotxt.droidsrce.homedashboard;

import android.content.Context;
import android.graphics.Rect;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;

public class Iconstant {


    // Live

//   public static String baseurl = "http://54.203.88.135/crm-api/api/v1/";

    //11111111111111111111111111111111111111111111111111111111111


    //<<<<<<<<<<<<<<<<<LIVE>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    // public static String baseurl = "http://34.215.32.32:3000/api/";
//<<<<<<<<<<<<<<<<<LIVE>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    public static String baseurl = "http://checea.com/";

    //<<<<<<<<<<<<<<<<<<<<TESTING local >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    //  public static String baseurl = "https://newyorkmultiserviceshub.com/api/";


    // 1111111111111111111111111111111111111111111111111111111111111111


    public static String office_bear = baseurl + "web_services/office_bearears.php";
    public static String chennaizone = baseurl + "web_services/zone_wise_members.php";
    public static String events = baseurl + "web_services/events.php";

    public static String notification = baseurl + "web_services/notifications.php";



    public static String role_of_honour = baseurl + "web_services/role_of_honour.php";

    public static String president_msg = baseurl + "web_services/feceat_president.php";
    public static String associations = baseurl + "web_services/associations.php";
    public static String joinus = baseurl + "web_services/join_us.php";

    public static String sponsors = baseurl + "web_services/sponsors.php";




    public static String category_list = baseurl + "form/get";

    public static String transaction_list = baseurl + "transaction/get_transaction";
    public static String apply_form = baseurl + "transaction/new_transaction";
    public static String update_transaction = baseurl + "transaction/update_transaction";

    public static String filters = baseurl + "user/role";

    public static String versioncode = "4";


    public static String red = "holo_gray_dark";

    public static String green = "holo_gray_dark";


    public static void setBackgroundColor(View view, Context mContext, String color) {
        int identifier = mContext.getResources().getIdentifier(color, "color", mContext.getPackageName());
        if (identifier != 0) {
            view.setBackgroundColor(mContext.getResources().getColor(identifier));
        }
    }

    public static void createSnackBarView(Context context, Snackbar snackbar) {
        final View snackBarView = snackbar.getView();
        setBackgroundColor(snackBarView, context, red);
        //To hide soft keyboard When snackbar displays
        final InputMethodManager im = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        snackBarView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            /**
             * This method is used to hide soft keyboard When snackbar displays
             * @return Nothing.
             */
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                snackBarView.getWindowVisibleDisplayFrame(r);
                int screenHeight = snackBarView.getRootView().getHeight();
                // r.bottom is the position above soft keypad or device button.
                // if keypad is shown, the r.bottom is smaller than that before.
                int keypadHeight = screenHeight - r.bottom;
                if (keypadHeight > screenHeight * 0.15) { // 0.15 ratio is perhaps enough to determine keypad height.
                    // keyboard is opened
                    im.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                } else {
                    // keyboard is closed
                }
            }
        });
       /* TextView tv = snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.parseColor("#ffff00"));

        snackbar.show();*/
    }


    public static void createSnackBarView1(Context context, Snackbar snackbar) {
        final View snackBarView = snackbar.getView();
        setBackgroundColor(snackBarView, context, green);
        //To hide soft keyboard When snackbar displays
        final InputMethodManager im = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        snackBarView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            /**
             * This method is used to hide soft keyboard When snackbar displays
             * @return Nothing.
             */
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                snackBarView.getWindowVisibleDisplayFrame(r);
                int screenHeight = snackBarView.getRootView().getHeight();
                // r.bottom is the position above soft keypad or device button.
                // if keypad is shown, the r.bottom is smaller than that before.
                int keypadHeight = screenHeight - r.bottom;
                if (keypadHeight > screenHeight * 0.15) { // 0.15 ratio is perhaps enough to determine keypad height.
                    // keyboard is opened
                    im.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                } else {
                    // keyboard is closed
                }
            }
        });
      /*  TextView tv = snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.parseColor("#ffff00"));
        snackbar.show();*/
    }


}
