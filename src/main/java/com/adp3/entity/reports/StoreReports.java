package com.adp3.entity.reports;
/*
*Zangwa Steve
* 217136664
* Class Description : StoreReport
* Using builder pattern
 */

import java.io.Serializable;

public class StoreReports implements Serializable {
    private String reportID, reportDesc;
    public StoreReports(Builder builder){
        this.reportID = builder.reportID;
        this.reportDesc = builder.reportDesc;

    }

    public StoreReports(){}

    public String getReportID() {
        return reportID;
    }
    public String getReportDesc() {
        return reportDesc;
    }
    @Override
    public String toString() {
        return "Store_Reports{" +
                "ReportID='" + reportID + '\'' +
                ", ReportDesc='" + reportDesc + '\'' +
                                '}';
    }

    public void getReportID(String reportID) {
        this.reportID = reportID;
    }

    public void getReportDesc(String reportDesc) {
        this.reportDesc = reportDesc;
    }


    public static class Builder {
       private String reportID,storeID, reportDesc;

        public Builder setReportID(String reportID) {
            this.reportID = reportID;
            return this;

        }


        public Builder setReportDesc(String reportDesc) {
            this.reportDesc = reportDesc;
            return this;
        }


        public StoreReports build() { return new StoreReports(this);
        }
        public Builder copy (StoreReports storeReports){
            this.reportID = storeReports.reportID;
            this.reportDesc = storeReports.reportDesc;
            return this;
        }


    }


}


