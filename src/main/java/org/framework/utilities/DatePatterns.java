package org.framework.utilities;

public enum DatePatterns {
	YYYY_MM_DD("yyyy-MM-dd"),
    EEEE_MMMMMMMMM_d("EEEE, MMMMMMMMM d"),
    EEEE_MMMMMMMMM_dd("EEEE, MMMMMMMMM dd"),
    M_DD_YYYY("M/dd/yyyy"),
    d("d"),
    dd("dd"),
    EEEE("EEEE"),
    MMMMMMMMM_d_yyy("MMMMMMMMM d, yyy"),
    MM_dd_yyyy_HH_mm("MM/dd/yyyy HH:mm"),
    MM_dd_yyyy_HH_mm_ss_PM("MM/dd/yyyy HH:mm:ss a"),
    MM_dd_yyyy_HH_mm_sss("MM/dd/yyyy HH:mm:ss"),
    MMM_dd_yyyy_HH_mm_ss("EEE MMM dd yyyy HH:mm:ss"),
    MM_dd_yyyy_HH_mm_ss("yyyy-MM-dd HH:mm:ss"),
    MM_dd_HH_mm_ss("MM-dd_HH-mm-ss"),
    EEEE_MMM_d("EEEE MMM d"),
    MM("MM"),
    YYYY("YYYY"),
    MMM_DD_YYYY("MMM dd yyyy"),
    MM_dd_yyyy("MM/dd/yyyy"),
    MM_DD("MMMM dd"),
    E_D_MMM_Y("E, d MMM y HH"),
    E_DD_MMM_Y("E, dd MMM y HH"),
    E_D_MMM_Y_HH_MM("E, d MMM y HH:mm"),
    MMMM_dd_y("MMMM dd, y"),
    EEE_MMMM_d("EEE, MMMM d"),
    MMM_d("MMM d"),
    MMMM_d("MMMM d"),
    MMDDYYYYHHmmss("MMddYYYYHHmmss");

    private String datePattern;

    private DatePatterns(String datePattern) {
          this.datePattern = datePattern;
    }

    public String getPattern() {
          return datePattern;
    }
}
