package com.github.mutare.adventcalendar2019.day18;

import java.util.*;

import static com.google.common.primitives.Chars.asList;

public class KeyFinder {

    class State {
        public char[] robotsLocations;
        Map<Character, Collection<Character>> keys;
        List<Character> route;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return Objects.equals(keys, state.keys) &&
                    Objects.equals(route, state.route) &&
                    Objects.equals(robotsLocations, state.robotsLocations);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(robotsLocations);
        }

        public State() {
            this.keys = new HashMap<>();
            this.route = new ArrayList<>();
        }

        public State(State state) {
            this.keys = new HashMap<>();
            this.route = new ArrayList<>(state.route);

            for (Character character : state.keys.keySet()) {
                this.keys.put(character, new ArrayList<>(state.keys.get(character)));
            }
            this.robotsLocations = Arrays.copyOf(state.robotsLocations, state.robotsLocations.length);
        }

        public void init() {
            for (int i = 0; i < initialRobots.size(); i++) {
                keys.putAll(getRobotObstacles(initialRobots.get(i)));
            }
            robotsLocations = new char[initialRobots.size()];
        }


        private Map<Character, Collection<Character>> getRobotObstacles(Robot robot) {
            Map<Character, Collection<Character>> foundKeys = new HashMap<>();
            Deque<Scanner> q = new LinkedList<>();
            Set<Scanner> seen = new HashSet<>();
            q.add(new Scanner(robot.location));
            while (!q.isEmpty()) {
                Scanner scanner = q.pop();
                if (seen.contains(scanner)) continue;
                seen.add(scanner);
                if (isWall(scanner.location)) continue;
                if (isDoor(scanner.location))
                    scanner.doors.add(getCharAt(scanner.location));
                if (isKey(scanner.location)) {
                    foundKeys.put(getCharAt(scanner.location), new LinkedList<>(scanner.doors));//putIfAbsent????
                    scanner.doors.add(getCharAt(scanner.location));
                }
                for (int i = 0; i < dr.length; i++) {
                    Scanner newScanner = new Scanner(scanner);
                    newScanner.location.x += dc[i];
                    newScanner.location.y += dr[i];
                    q.add(newScanner);
                }
            }
            return foundKeys;
        }
    }

    private final char[][] map;
    List<Map<Character, Location>> availablePositions = new ArrayList<>();
    List<Map<Path, Integer>> distances = new ArrayList<>();
    Map<Character, Location> allKeysLocations = new HashMap<>();
    State initialState;
    List<Robot> initialRobots = new LinkedList<>();

    byte[] dr = new byte[]{-1, 0, 1, 0};
    byte[] dc = new byte[]{0, 1, 0, -1};

    public KeyFinder(char[][] map) {
        this.map = map;
        int height = map.length;
        int width = map[0].length;
        State state = new State();
        for (byte r = 0; r < height; r++)
            for (byte c = 0; c < width; c++) {
                if (map[r][c] == '@') {
                    initialRobots.add(new Robot(c, r));
                }
                if ('a' <= map[r][c] && map[r][c] <= 'z') {
                    allKeysLocations.put(map[r][c], Location.of(c, r));
                }
            }
        state.init();
        initialState = new State(state);

        for (Robot robot : initialRobots) {
            Map<Character, Location> available = getAvailablePositions(robot.location);
            availablePositions.add(available);
            distances.add(getPaths(available));
        }
    }

    private Map<Path, Integer> getPaths(Map<Character, Location> available) {
        Map<Path, Integer> distances = new HashMap<>();
        for (Character c0 : available.keySet()) {
            for (Character c1 : available.keySet()) {
                if (c0.equals(c1)) continue;
                Path path = Path.of(available.get(c0), available.get(c1));
                distances.put(path, getDistance(available.get(c0), available.get(c1)));
            }
        }
        return distances;
    }

    private int getDistance(Location location, Location location1) {
        Deque<Robot> q = new LinkedList<>();
        Set<Location> seen = new HashSet<>();
        q.add(new Robot(location));
        while (!q.isEmpty()) {
            Robot robot = q.pop();
            if (seen.contains(robot.location)) continue;
            seen.add(robot.location);
            if (isWall(robot.location)) continue;
            if (robot.location.equals(location1)) {
                return robot.distance;
            }
            for (int i = 0; i < dr.length; i++) {
                q.add(Robot.of(Location.of((byte) (robot.location.x + dc[i]), (byte) (robot.location.y + dr[i])), (short) (robot.distance + 1)));
            }
        }
        throw new RuntimeException("not this way");
    }

    private Map<Character, Location> getAvailablePositions(Location startLocation) {
        Map<Character, Location> available = new HashMap<>();
        Deque<Location> q = new LinkedList<>();
        Set<Location> seen = new HashSet<>();
        q.add(startLocation);

        while (!q.isEmpty()) {
            Location location = q.pop();
            if (seen.contains(location)) continue;
            seen.add(location);
            if (isWall(location)) continue;
            if (isKey(location) || isDoor(location) || isRobot(location)) {
                available.putIfAbsent(map[location.y][location.x], location);
            }
            for (int i = 0; i < dr.length; i++) {
                q.add(Location.of((byte) (location.x + dc[i]), (byte) (location.y + dr[i])));
            }
        }

        return available;
    }

    int reuse = 0;


    class Distance {
        int value;
        List<Character> route;

        Distance(List<Character> route, int value) {
            this.route = route;
            this.value = value;
        }
    }

    Map<MidPath, Distance> midDistances = new HashMap<>();


    Distance go(MidPath path, State state) {
        Map<MidPath, Distance> mids = new HashMap<>();
        List<Character> route = null;
        Character minHead = null;

        int min = Integer.MAX_VALUE;
        if (path.tail.size() == 1) {
            return new Distance(new LinkedList<>(asList(path.tail.iterator().next())), getDistanceFor(path.head, path.tail.iterator().next(), state));
        }

        state.keys.entrySet().stream().filter(characterCollectionEntry -> characterCollectionEntry.getValue().isEmpty()).map(Map.Entry::getKey).forEach(c -> {
            MidPath subPath = new MidPath();
            subPath.tail = new HashSet<>(state.keys.keySet());
            subPath.tail.remove(c);
            subPath.head = c;

            State newstate = new State(state);
            newstate.route.add(c);
            newstate.keys.remove(c);
            newstate.keys.values().forEach(characters -> characters.remove(Character.toUpperCase(c)));
            newstate.keys.values().forEach(characters -> characters.remove(Character.toLowerCase(c)));
            newstate.robotsLocations[getNoOfRobot(c)] = c;
            subPath.robotsLocations = newstate.robotsLocations;

            Distance distance = midDistances.get(subPath);
            if (distance == null) {
                distance = go(subPath, newstate);
                if (null == midDistances.put(subPath, distance) && midDistances.size() % 10000 == 0)
                    System.out.println("Midmin size : " + midDistances.size());
            } else {
                reuse++;
                if (reuse % 10000 == 0) System.out.println("Reuse count : " + reuse);
            }
            mids.put(subPath, distance);
        });
        for (Map.Entry<MidPath, Distance> e : mids.entrySet()) {
            int distance = getDistanceFor(path.head, e.getKey().head, state);
            if (min > (e.getValue().value + distance)) {
                min = e.getValue().value + distance;
                route = e.getValue().route;
                minHead = e.getKey().head;
            }
        }

        LinkedList<Character> newroute = new LinkedList<>(route);
        newroute.add(minHead);
        return new Distance(newroute, min);
    }

    public int getShortestPathSteps() {
        MidPath path = new MidPath();
        path.tail = new HashSet<>(initialState.keys.keySet());
        Distance go = go(path, initialState);

        Collections.reverse(go.route);
        System.out.println(go.route);

        return go.value;
    }

    class MidPath {
        char head;
        Set<Character> tail;
        char[] robotsLocations;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MidPath midPath = (MidPath) o;
            return Arrays.equals(robotsLocations, midPath.robotsLocations) &&
                    tail.containsAll(midPath.tail) && tail.size() == midPath.tail.size();
        }

        @Override
        public int hashCode() {
            return Objects.hash(head);
        }
    }

    private int getDistanceFor(char c0, char c1, State state) {
        int noOfRobot = getNoOfRobot(c1);
        Location location0 = c0 == '\u0000' ? null : availablePositions.get(getNoOfRobot(c0)).get(c0);
        Location location1 = availablePositions.get(noOfRobot).get(c1);
        Integer oneRobotDistance = distances.get(noOfRobot).get(Path.of(location0, location1));
        if (oneRobotDistance != null) return oneRobotDistance;
        Location location2 = null;
        for (int i = state.route.size() - 1; i >= 0; i--) {
            char c = state.route.get(i);
            if (availablePositions.get(noOfRobot).containsKey(c)) {
                location2 = availablePositions.get(noOfRobot).get(c);
                break;
            }
        }
        return getDistanceFor(location2 != null ? location2 : getRobotLocation(state, noOfRobot), c1);
    }

    private Location getRobotLocation(State state, int noOfRobot) {
        char c = state.robotsLocations[noOfRobot];
        if (c != '\u0000') {
            return availablePositions.get(noOfRobot).get(c);
        } else {
            return initialRobots.get(noOfRobot).location;
        }
    }

    private int getDistanceFor(Location location0, char c1) {
        Location location1 = availablePositions.get(getNoOfRobot(c1)).get(c1);
        return distances.get(getNoOfRobot(c1)).get(Path.of(location0, location1));
    }

    private int getNoOfRobot(Character c) {
        for (int i = 0; i < availablePositions.size(); i++) {
            if (availablePositions.get(i).containsKey(c)) return i;
        }
        throw new RuntimeException("not found");
    }

    private boolean isKey(Location location) {
        char c = map[location.y][location.x];
        return (c >= 'a' && c <= 'z');
    }

    private boolean isDoor(Location location) {
        char c = map[location.y][location.x];
        return c >= 'A' && c <= 'Z';
    }

    private boolean isWall(Location location) {
        return map[location.y][location.x] == '#';
    }

    private boolean isRobot(Location location) {
        return map[location.y][location.x] == '@';
    }

    private char getCharAt(Location location) {
        return map[location.y][location.x];
    }
}
