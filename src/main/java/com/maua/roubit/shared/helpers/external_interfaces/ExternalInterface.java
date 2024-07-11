package com.maua.roubit.shared.helpers.external_interfaces;

import java.util.Map;

public class ExternalInterface {
    public interface IRequest {
        Map<String, Object> getData();
    }

    public interface IResponse {
        int getStatusCode();
        Map<String, Object> getData();
    }

}
