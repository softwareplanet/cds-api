package com.interlink.cds.jsonView;

public class JobView {

    public interface ShotDeatils{}

    public interface Worker extends ShotDeatils{
    }

    public interface Manager extends Worker {
    }

    public interface AssigneeJob extends JobView.Worker, UserView.Manager, StatusView.Worker, PhoneView.Worker, CompanyView.Worker {

    }



}
