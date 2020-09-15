package com.example.tollparking.api.api.park;

import java.io.Serializable;

public class Response implements Serializable {

    private int    status;
    private String statusDesc;
    private long   parkStartAsMillis;

    public Response() {
    }

    public Response(int status, String statusDesc, long parkStartAsMilis) {
        this.status            = status;
        this.statusDesc        = statusDesc;
        this.parkStartAsMillis = parkStartAsMilis;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public long getParkStartAsMillis() {
        return parkStartAsMillis;
    }

    public void setParkStartAsMillis(long parkStartAsMillis) {
        this.parkStartAsMillis = parkStartAsMillis;
    }
}
