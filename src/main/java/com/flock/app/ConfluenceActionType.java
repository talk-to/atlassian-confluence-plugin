package com.flock.app;

public enum ConfluenceActionType {
    COMMENT_CREATE("comment-create"),
    COMMENT_UPDATE("comment-update"),
    COMMENT_REMOVE("comment-remove"),

    PAGE_CREATE("page-create"),
    PAGE_COPY("page-copy"),
    PAGE_CREATE_FROM_TEMPLETE("page-create-from-template"),
    PAGE_INFO_VIEW("page-info-view"),
    PAGE_LIST_VIEW("page-list-view"),
    PAGE_MOVE_COMPLETE("page-move-complete"),
    PAGE_MOVE("page-move"),
    PAGE_REMOVE("page-remove"),
    PAGE_RESTORE("page-restore"),
    PAGE_TRASHED("page-trashed"),
    PAGE_UPDATE("page-update"),
    PAGE_VIEW("page-view"),

    SPACE_CREATE("space-create"),
    SPACE_ARCHIVED("space-archive"),
    SPACE_LOGO_UPDATE("space-logo-update"),
    SPACE_PERMISSION_UPDATE("space-permission-update"),
    SPACE_REMOVE("space-remove"),
    SPACE_TRASH_EMPTY("space-trash-empty"),
    SPACE_TRASH_VIEW("space-trash-view"),
    SPACE_UNARCHIVED("space-unarchived"),
    SPACE_UPDATE("space-update"),
    SPACE_WILL_REMOVE("space-will-remove");

    private String nameString;

    ConfluenceActionType(String nameString) {
        this.nameString = nameString;
    }

    public String getNameString() {
        return nameString;
    }
}
