/*
 * File: PathNode.java
 * Description: a class structure save left, right, and parent Node
 * BUGS:
 * NOTE:
 * First Created on Date: 2024/04/19 by Author: Owen Li
 * Last Modified on Date: 2024/04/19 by Author: Owen Li
 * Fix on Date:
 * Code Review Record on Date:
 * Copy right (c) DMS, Delta Electronics, INC.
 * All rights reserved
 * */

import java.util.*;

public class PathNode {
    // ********************
    // declare variables
    private Character c;
    private PathNode parent;
    private PathNode left;
    private PathNode right;
    // ********************
    // constructor
    public PathNode() {
        this.c = null;
        this.parent = null;
        this.left = null;
        this.right = null;
    }

    // ********************
    // getter and setter functions
    public Character getCharacter() {
        return c;
    }

    public void setCharacter(Character value) {
        this.c = value;
    }

    public PathNode getParent() {
        return parent;
    }

    public void setParent(PathNode value) {
        this.parent = value;
    }

    public PathNode getLeft() {
        return left;
    }

    public void setLeft(PathNode value) {
        this.left = value;
    }

    public PathNode getRight() {
        return right;
    }

    public void setRight(PathNode value) {
        this.right = value;
    }
}