package edu.ufl.cise.cs1.controllers;

import game.controllers.AttackerController;
import game.models.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class StudentAttackerController implements AttackerController {
    public void init(Game game) {

    }

    public void shutdown(Game game) {
    }

    public int update(Game game, long timeDue) {
        List pills = game.getPillList();
        List powerPills = game.getPowerPillList();
        List<Defender> defenders = game.getDefenders();
        Attacker gator = game.getAttacker();
        //All pill node lists and actor lists initialized and called for simpler access, albeit a bit slower
        Defender[] vulnerableDefenders = new Defender[4];
        boolean vulnerablesExist = false;
        int i = 0;
        while (i <= 3) {
            if (defenders.get(i).isVulnerable()) {
                vulnerableDefenders[i] = defenders.get(i);
                vulnerablesExist = true;
            }
            i++;
        }
        //Create an array to store defenders that are currently vulnerable
        Defender[] invulnerableDefenders = new Defender[4];
        boolean invulnerablesExist = false;
        i = 0;
        while (i <= 3) {
            if (!defenders.get(i).isVulnerable()) {
                invulnerableDefenders[i] = defenders.get(i);
                invulnerablesExist = true;
            }
            i++;
        }
        //Another array to differentiate invulnerable defenders because lists are immutable
        Node closestPill = betterGetTarget(pills, game);
        Node closestPowerPill = null;
        if (powerPills.size() > 0) {
            closestPowerPill = betterGetTarget(powerPills, game);
        }
        Actor closestDefender = null;
        if (invulnerablesExist && arrayGetTarget(invulnerableDefenders, game) != -1) {
            closestDefender = invulnerableDefenders[arrayGetTarget(invulnerableDefenders, game)];
        }
        Actor closestVulnerableDefender = null;
        if (vulnerablesExist && arrayGetTarget(vulnerableDefenders, game) != -1) {
            closestVulnerableDefender = vulnerableDefenders[arrayGetTarget(vulnerableDefenders, game)];
        }
        //Get the closest of each list to use in decision making later, returns null if there are none left
        int distanceToPowerPill = -1;
        if (powerPills.size() > 0) {
            distanceToPowerPill = gator.getLocation().getPathDistance(closestPowerPill);
        }
        int distanceToDefender = -1;
        if (invulnerablesExist) {
            distanceToDefender = gator.getLocation().getPathDistance(closestDefender.getLocation());
        }
        //Get distance to power pills and the nearest invulnerable defender to use in decision making later
        int action = 0;
        final int defenderThreshold = 3;
        final int pillThreshold = 1;
        //Initialize the action integer and thresholds for defender distances and pill approach distances
        if (distanceToDefender < defenderThreshold && distanceToDefender != -1) {
            action = gator.getNextDir(closestDefender.getLocation(), false);
            //If within 3 units of a defender, run away
        } else {
            if (closestVulnerableDefender != null) {
                action = gator.getNextDir(closestVulnerableDefender.getLocation(), true);
                //If there are vulnerable defenders, eat 'em!
            } else {
                if (closestPowerPill != null) {
                    if (distanceToPowerPill > pillThreshold) {
                        action = gator.getNextDir(closestPowerPill, true);
                    } else {
                        if (distanceToDefender > defenderThreshold + pillThreshold || distanceToDefender == -1) {
                            action = gator.getNextDir(closestPowerPill, false);
                        } else {
                            action = gator.getNextDir(closestPowerPill, true);
                        }
                    }
                    //If there are power pills, get close to them until a defender gets close, then go to the pill.
                } else {
                    action = gator.getNextDir(closestPill, true);
                    //Standard pill seeking behavior
                }
            }
        }
        return action;
    }

    public static Node betterGetTarget(List<Node> node, Game game) {
        if (node.size() == 0) {
            return null;
        }
        int minDistance = Integer.MAX_VALUE;
        int minIndex = 0;
        for (int i = 0; i < node.size(); i++) {
            int currentDistance = node.get(i).getPathDistance(game.getAttacker().getLocation());
            if (currentDistance < minDistance) {
                minDistance = currentDistance;
                minIndex = i;
            }
        }
        return node.get(minIndex);
    }
    //Implemented the better target finder from Slack, as recommended by the TA's

    public static int arrayGetTarget(Defender[] array, Game game) {
        if (Arrays.toString(array).equals("[null, null, null, null]")) {
            return -1;
        }
        int minDistance = Integer.MAX_VALUE;
        int minIndex = 0;
        for (int i = 0; i <= 3; i++) {
            if (array[i] != null) {
                int currentDistance = array[i].getLocation().getPathDistance(game.getAttacker().getLocation());
                if (currentDistance < minDistance) {
                    minDistance = currentDistance;
                    minIndex = i;
                }
            }
        }
        return minIndex;
    }
    //Altered minimum distance search function for defender arrays from the better target finder function
}