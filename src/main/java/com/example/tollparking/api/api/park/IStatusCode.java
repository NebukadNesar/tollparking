package com.example.tollparking.api.api.park;

public interface IStatusCode {

    enum Status {

        ACCEPTED(1, "Car accepted."), REFUSED(0, "Car refused");

        int    code;
        String description;

        Status(int code, String description) {
            this.code        = code;
            this.description = description;
        }
    }


    public Status accepted = Status.ACCEPTED;
    public Status refused  = Status.REFUSED;

}
