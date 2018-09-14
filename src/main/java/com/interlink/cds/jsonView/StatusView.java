package com.interlink.cds.jsonView;

public class StatusView {
    public interface Details {};

    public interface Worker extends Details {}

    public interface Transition extends StatusView.Worker {
    }
}
