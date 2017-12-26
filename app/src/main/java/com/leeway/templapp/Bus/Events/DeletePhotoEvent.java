package com.leeway.templapp.Bus.Events;

/**
 * Created by work on 7/31/2017.
 */

public class DeletePhotoEvent {
    private String sidebarImageId;

    public String getSidebarImageId() {
        return sidebarImageId;
    }

    public void setSidebarImageId(String sidebarImageId) {
        this.sidebarImageId = sidebarImageId;
    }

    public DeletePhotoEvent(String sidebarImageId) {
        this.sidebarImageId = sidebarImageId;
    }
}
