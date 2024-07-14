package com.maua.roubit.shared.helpers.external_interfaces;

import java.util.Map;

class ExternalInterface {
    protected interface IRequest {
        Map<String, Object> getData();
    }

    protected interface IResponse {
        int getStatusCode();
        Map<String, Object> getData();
    }

}
