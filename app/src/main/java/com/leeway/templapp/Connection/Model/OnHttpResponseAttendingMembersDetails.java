package com.leeway.templapp.Connection.Model;

import java.util.List;

/**
 * Created by work on 7/26/2017.
 */

public interface OnHttpResponseAttendingMembersDetails {
    void OnSuccessAttendingMembersDetails(boolean stautus, List<MemberInfo> memberInfos);

    void OnFaildAttendingMembersDetails(String errorMessage);
}
