package com.interlink.cds.jsonView;

public class CompanyView {
    public interface Worker  {
    }

    public interface CompanyDetails extends CompanyView.Worker, StatusView.Details {}
}
