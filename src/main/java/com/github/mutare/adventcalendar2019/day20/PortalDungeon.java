package com.github.mutare.adventcalendar2019.day20;

import java.util.*;

public class PortalDungeon {
    char[][] map;
    Deque<State> q = new LinkedList<>();
    int[] dr = new int[]{1, 0, -1, 0};
    int[] dc = new int[]{0, 1, 0, -1};
    Map<String, List<State>> portals;
    int width;
    int height;

    class State {
        public int distance;

        public State(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.distance = d;
        }

        int x;
        int y;

        public State(int x, int y) {
            this(x, y, 0);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return x == state.x &&
                    y == state.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    PortalDungeon(char[][] map) {
        this.map = map;
        this.width = map[0].length;
        this.height = map.length;
        portals = getPortals();
        q.add(findEntrance());
    }

    private Map<String, List<State>> getPortals() {
        Map<String, List<State>> portals = new HashMap<>();
        for (int j = 1; j < map.length - 1; j++) {
            for (int i = 1; i < map[2].length - 1; i++) {
                if (isLetter(i, j) && isLetter(i + 1, j) && isPass(i - 1, j)) {
                    char c0 = getCharAt(i, j);
                    char c1 = getCharAt(i + 1, j);
                    String portalName = sort("" + c0 + c1);
                    portals.putIfAbsent(portalName, new ArrayList<>());
                    portals.get(portalName).add(new State(i - 1, j));
                } else if (isLetter(i, j) && isLetter(i - 1, j) && isPass(i + 1, j)) {
                    char c0 = getCharAt(i - 1, j);
                    char c1 = getCharAt(i, j);
                    String portalName = sort("" + c0 + c1);
                    portals.putIfAbsent(portalName, new ArrayList<>());
                    portals.get(portalName).add(new State(i + 1, j));
                } else if (isLetter(i, j) && isLetter(i, j - 1) && isPass(i, j + 1)) {
                    char c0 = getCharAt(i, j - 1);
                    char c1 = getCharAt(i, j);
                    String portalName = sort("" + c0 + c1);
                    portals.putIfAbsent(portalName, new ArrayList<>());
                    portals.get(portalName).add(new State(i, j + 1));
                } else if (isLetter(i, j) && isLetter(i, j + 1) && isPass(i, j - 1)) {
                    char c0 = getCharAt(i, j);
                    char c1 = getCharAt(i, j + 1);
                    String portalName = sort("" + c0 + c1);
                    portals.putIfAbsent(portalName, new ArrayList<>());
                    portals.get(portalName).add(new State(i, j - 1));
                }
            }
        }

        return portals;
    }

    private boolean isPass(int x, int y) {
        return getCharAt(x, y) == '.';
    }


    public int findShortestPath() {
        Set seen = new HashSet();
        while (!q.isEmpty()) {
            State state = q.pop();
            if (seen.contains(state)) continue;
            seen.add(state);

            for (int i = 0; i < 4; i++) {
                State newState = new State(state.x + dc[i], state.y + dr[i], state.distance + 1);
                if (newState.x >= width || newState.x < 0 || newState.y >= height || newState.y < 0) continue;
                if (isPass(newState.x, newState.y)) {
                    q.add(newState);
                }
                if (isLetter(newState)) {
                    char c0 = getCharAt(newState.x, newState.y);
                    char c1 = findSecondLetter(newState);
                    String portalName = "" + c0 + c1;
                    if (portalName.equals("ZZ")) return newState.distance - 1;
                    if (portalName.equals("AA")) continue;
                    portalName = sort(portalName);
                    List<State> states = portals.get(portalName);
                    State state2 = states.stream().filter(state1 -> state1.x != state.x && state1.y != state.y).findFirst().orElseThrow();
                    newState = new State(state2.x, state2.y, state.distance + 1);
                    q.add(newState);
                }
            }
        }

        return 0;
    }

    private String sort(String portalName) {
        char[] chars = portalName.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    private boolean isWall(State state) {
        return getCharAt(state.x, state.y) == '#';
    }

    private boolean isLetter(State state) {
        return isLetter(state.x, state.y);
    }

    private boolean isLetter(int x, int y) {
        return getCharAt(x, y) >= 'A' && getCharAt(x, y) <= 'Z';
    }

    private char findSecondLetter(State state) {
        for (int i = 0; i < 4; i++) {
            State next = new State(state.x + dc[i], state.y + dr[i]);
            if (isLetter(next)) {
                return map[next.y][next.x];
            }
        }
        throw new RuntimeException();
    }

    private State findEntrance() {
        return portals.get("AA").get(0);
    }


    private char getCharAt(int x, int y) {
        try {
            return map[y][x];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException(e);
        }
    }
}
