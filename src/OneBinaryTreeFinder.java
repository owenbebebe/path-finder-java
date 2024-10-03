/*
 * File: OneBinaryTreeFinder.java
 * Description: Use two indexes in PathNode tree as input
 *              return a path from starting index to ending index
 * BUGS:
 * NOTE:
 * First Created on Date: 2024/04/21 by Author: Owen Li
 * Last Modified on Date: 2024/04/21 by Author: Owen Li
 * Fix on Date:
 * Code Review Record on Date:
 * Copy right (c) DMS, Delta Electronics, INC.
 * All rights reserved
 * */

import java.util.*;

public class OneBinaryTreeFinder {

    OneBinaryTreeFinder() {
    }

    // ********************
    // Find common parent of both nodes
    public ArrayList<String> runOneBinaryTree(int startNodeIndex, int endNodeIndex) {
        ArrayList<String> upPathList = new ArrayList<>();
        ArrayList<String> endPathList = new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();

        // Find common parent of both nodes
        while (startNodeIndex != endNodeIndex) {
            if (startNodeIndex > endNodeIndex) {
                startNodeIndex = startNodeIndex/2;
                upPathList.add(GlobalConstants.G_PATH_UP);
            }

            // target index > starting index
            else {

                // check if endNodeIndex is even number add Left to path
                if (0 == endNodeIndex % 2) {
                    endPathList.add(GlobalConstants.G_PATH_LEFT);
                }

                // endNodeIndex is odd so add Right to path
                else {
                    endPathList.add(GlobalConstants.G_PATH_RIGHT);
                }
                endNodeIndex = endNodeIndex/2;
            }
        }

        // add every "Up"s from upPathList
        for (int i = 0; i < upPathList.size(); i++) {
            result.add(upPathList.get(i));
        }

        // add every paths from endPathList
        for (int i = 0; i < endPathList.size(); i++) {
            result.add(endPathList.get(i));
        }
        return result;
    }
}
