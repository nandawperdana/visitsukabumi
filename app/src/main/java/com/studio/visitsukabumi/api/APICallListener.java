package com.studio.visitsukabumi.api;

import com.studio.visitsukabumi.utils.commons.Enums;

import java.util.List;

/**
 * Created by nandawperdana on 5/10/2016.
 */
public interface APICallListener {
    void onAPICallSucceed(Enums.APIRoute route, RootResponseModel responseModel);

    void onAPICallSucceed(Enums.APIRoute route, List<RootResponseModel> responseModels);

    void onAPICallFailed(Enums.APIRoute route, Throwable throwable);
}
