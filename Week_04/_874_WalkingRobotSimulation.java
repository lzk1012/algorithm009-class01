package com.xx.leetcode.week04.homework;

import java.util.HashSet;
import java.util.Set;

/**
 * 874. 模拟行走机器人
 *
 * @author lzk1012
 * @version 1.0
 * @date 2020/6/12 14:57
 */
public class _874_WalkingRobotSimulation {
    public static void main(String[] args) {
        new _874_WalkingRobotSimulation().robotSim(new int[]{-2,8,3,7,-1}, new int[][]{});
    }
    public int robotSim(int[] commands, int[][] obstacles) {
        int pointX = 0;
        int pointY = 0;
        int[][] direction = {{0,1},{1,0},{0,-1},{-1,0}};// 分别代表北、东、南、西
        // 当前方向
        int crtDirIndex = 0;
        int result = 0;
        // 用于保存障碍物，方便后续遍历获取
        Set<Long> obstacleSet = new HashSet<>();
        for (int[] obstacle: obstacles) {
            long ox = obstacle[0];
            long oy = obstacle[1];
            obstacleSet.add((ox << 16) + oy);
        }


        for(int command : commands){
            switch (command){
                case -2:
                    // PS. -1取模，还是-1
                    crtDirIndex = (crtDirIndex + 3) % direction.length;
                    break;
                case -1:
                    crtDirIndex = (crtDirIndex + 1) % direction.length;
                    break;
                default:
                    int[] thisDir = direction[crtDirIndex];
                    for (int k = 0; k < command; ++k) {
                        int newX = pointX + thisDir[0];
                        int newY = pointY + thisDir[1];
                        long code = (((long) newX) << 16) + ((long) newY );
                        if (!obstacleSet.contains(code)) {
                            pointX = newX;
                            pointY = newY;
                            result = Math.max(result, pointX*pointX + pointY*pointY);
                        }
                    }
                    break;
            }
        }

        return result;
    }
}
