/*
 * File: ErrorCode.java
 * Description: a enum that stores error code
 * BUGS:
 * NOTE:
 * First Created on Date: 2024/04/20 by Author: Owen Li
 * Last Modified on Date: 2024/04/20 by Author: Owen Li
 * Fix on Date:
 * Code Review Record on Date:
 * Copy right (c) DMS, Delta Electronics, INC.
 * All rights reserved
 * */
public enum ErrorCode {

    SUCCESS(0, "Success !"),
    ERR_STRING_NULL(-1, "Input String is NULL"),
    ERR_STRING_EMPTY(-2, "Input String is empty"),
    ERR_ROOT_NULL(-3, "Root node cannot be NULL"),
    ERR_TREE_INCOMPLETE(-4, "Input String does not represent a complete binary tree"),
    ERR_STRING_TOOLARGE(-5, "Input String is too large of a size"),
    ERR_STARTNODE_NULL(-6, "Start node cannot be NULL"),
    ERR_STARTNODE_INDEX_NOT_FOUND(-7, "Start node index does not exist"),
    ERR_ENDNODE_INDEX_NOT_FOUND(-8, "ENd node index does not exist"),
    ERR_ENDNODE_NULL(-7, "End node cannot be NULL");


    private final int code;
    private final String description;

    private ErrorCode(int code, String description) {
        this.code = code;
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    public int getCode() {
            return code;
    }

    @Override
    public String toString() {
            return code + ": " + description;
    }
}
