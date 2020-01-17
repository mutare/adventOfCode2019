package com.github.mutare.adventcalendar2019.day18;

import java.util.*;

import static com.google.common.primitives.Chars.asList;

public class KeyFinder {

    class State {
        public char[] robotsLocations;
        Map<Character, Collection<Character>> keys;
        List<Robot> robots;
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
            this.robots = new ArrayList<>();
            this.route = new ArrayList<>();
        }

        public State(State state) {
            this.keys = new HashMap<>();
            this.robots = new ArrayList<>();
            this.route = new ArrayList<>(state.route);

            for (Robot robot : state.robots) {
                this.robots.add(new Robot(robot));
            }
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
    List<List<Character>> routes = new LinkedList<>();
    Map<Set<Character>, Integer> pathsMinDistances = new HashMap<>();
    private int min = Integer.MAX_VALUE;
    private int routesCount;


    byte[] dr = new byte[]{-1, 0, 1, 0};
    byte[] dc = new byte[]{0, 1, 0, -1};
    Deque<State> q = new LinkedList<>();

    Set<State> seen = new HashSet<>();
    static Set<Character> allKeys = new HashSet<>();

    public KeyFinder(char[][] map) {
        this.map = map;
        int height = map.length;
        int width = map[0].length;
        State state = new State();
        for (byte r = 0; r < height; r++)
            for (byte c = 0; c < width; c++) {
                if (map[r][c] == '@') {
                    state.robots.add(new Robot(c, r));
                    initialRobots.add(new Robot(c, r));
                }
                if ('a' <= map[r][c] && map[r][c] <= 'z') {
                    allKeys.add(map[r][c]);
                    allKeysLocations.put(map[r][c], Location.of(c, r));
                }
            }
        state.init();
        //state.robots = new ArrayList<>(initialRobots);
        initialState = new State(state);

        q.add(state);

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
        List<Character> route = new LinkedList<>();

        Distance(List<Character> route, int value) {
            this.route = route;
            this.value = value;
        }
    }

    private Distance getMidMin(MidPath path, State state) {
        Map<MidPath, Distance> mids = new HashMap<>();
        List<Character> route = null;
        Character minHead = null;
        //if (path.tail.size() > 10) System.out.println(state.keys);
        int min = Integer.MAX_VALUE;
        if (path.tail.size() == 1)
            return new Distance(new LinkedList<>(asList(path.tail.iterator().next())), getDistanceFor(path.head, path.tail.iterator().next(), state));
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
            newstate.robots.get(getNoOfRobot(c)).location = availablePositions.get(getNoOfRobot(c)).get(c);
            subPath.robots = new ArrayList<>(newstate.robots);
            subPath.state = newstate;
            Distance distance = midDistances.get(subPath);
            if (distance == null) {
                distance = getMidMin(subPath, newstate);
                if (null == midDistances.put(subPath, distance) && midDistances.size() % 100000 == 0)
                    System.out.println("Midmin size : " + midDistances.size());
            } else {
                reuse++;
                if (reuse % 100000 == 0) System.out.println("Reuse count : " + reuse);
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
            int noOfRobot = getNoOfRobot(c);
            newstate.robotsLocations[noOfRobot] = c;
            newstate.robots.get(noOfRobot).location = availablePositions.get(noOfRobot).get(c);
            subPath.robots = new ArrayList<>(newstate.robots);
            subPath.robotsLocations = newstate.robotsLocations;
            subPath.state = newstate;

            if (state.route.equals(asList("ehiabcdfgkjln".toCharArray()))) {
                System.out.println("x");
            }

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
        State state = q.pop();
        MidPath path = new MidPath();
        path.tail = new HashSet<>(state.keys.keySet());
        Distance go = go(path, state);

        Collections.reverse(go.route);
        System.out.println(go.route);

        return go.value;
    }

    private void showMidsFor(int i) {
        System.out.println(" >>> " + i);
        midDistances.entrySet().stream()
                .filter(midPathDistanceEntry -> midPathDistanceEntry.getKey().tail.size() == i)
                .forEach(midPathDistanceEntry -> System.out.println(asList(midPathDistanceEntry.getKey().robotsLocations) + " - " + midPathDistanceEntry.getKey().head + " - " + midPathDistanceEntry.getKey().tail + " : " + midPathDistanceEntry.getValue().value));

    }

    public int getShortestPathSteps2() {
        State state = q.pop();
        List<Character> minRoute = null;
        Character minHead = null;
        Map<MidPath, Distance> mids = new HashMap<>();
        state.keys.entrySet().stream().filter(characterCollectionEntry -> characterCollectionEntry.getValue().isEmpty()).map(Map.Entry::getKey).forEach(c -> {
            MidPath mid = new MidPath();
            mid.tail = new HashSet<>(state.keys.keySet());
            mid.tail.remove(c);
            mid.head = c;

            State newstate = new State(state);
            newstate.route.add(c);
            newstate.keys.remove(c);
            newstate.keys.values().forEach(characters -> characters.remove(Character.toUpperCase(c)));
            newstate.keys.values().forEach(characters -> characters.remove(Character.toLowerCase(c)));
            newstate.robots.get(getNoOfRobot(c)).location = availablePositions.get(getNoOfRobot(c)).get(c);
            mid.robots = new ArrayList<>(newstate.robots);
            mid.state = newstate;
            mids.putIfAbsent(mid, getMidMin(mid, newstate));
        });

        int min = Integer.MAX_VALUE;
        for (Map.Entry<MidPath, Distance> e : mids.entrySet()) {
            int noOfRobot = getNoOfRobot(e.getKey().head);
            Location location = initialRobots.get(noOfRobot).location;
            int distance = getDistanceFor(location, e.getKey().head);
            if (min > (e.getValue().value + distance)) {
                min = e.getValue().value + distance;
                minRoute = e.getValue().route;
                minHead = e.getKey().head;
            }
        }
        minRoute.add(minHead);
        Collections.reverse(minRoute);
        System.out.println(minRoute);

        for (Map.Entry<MidPath, Distance> mp : midDistances.entrySet()) {
            System.out.println(mp.getKey().head + " - " + mp.getKey().state.route + "- " + mp.getKey().tail + " : " + mp.getValue().value);
        }
        return min;
    }


    class MidPath {
        public State state;
        char head;
        Set<Character> tail;
        List<Robot> robots;
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

    private int getDistanceForRoute(List<Character> route) {
        List<Robot> robots = new LinkedList<>();
        initialRobots.stream().map(Robot::new).forEach(robots::add);
        for (Character c : route) {
            int r = getNoOfRobot(c);
            Location location0 = robots.get(r).location;
            Location location1 = availablePositions.get(r).get(c);
            robots.get(r).location = location1;
            robots.get(r).distance += distances.get(r).get(Path.of(location0, location1));
        }
        return getDistance(robots);
    }

    private int getDistanceFor(char c0, char c1, State state) {
        Location location0 = availablePositions.get(getNoOfRobot(c0)).get(c0);
        Location location1 = availablePositions.get(getNoOfRobot(c1)).get(c1);
        Integer oneRobotDistance = distances.get(getNoOfRobot(c1)).get(Path.of(location0, location1));
        if (oneRobotDistance != null) return oneRobotDistance;
        Location location2 = null;
        for (int i = state.route.size() - 1; i >= 0; i--) {
            char c = state.route.get(i);
            if (availablePositions.get(getNoOfRobot(c1)).containsKey(c)) {
                location2 = availablePositions.get(getNoOfRobot(c1)).get(c);
                break;
            }
        }
        return getDistanceFor(location2 != null ? location2 : state.robots.get(getNoOfRobot(c1)).location, c1);
    }

    private int getDistanceFor(Location location0, char c1) {
        Location location1 = availablePositions.get(getNoOfRobot(c1)).get(c1);
        return distances.get(getNoOfRobot(c1)).get(Path.of(location0, location1));
    }


    private int getNoOfRobot(Character c) {
        for (int i = 0; i < availablePositions.size(); i++) {
            if (availablePositions.get(i).containsKey(c)) return i;
        }
        return 0;//TODO//throw new RuntimeException("not found");
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

    public int getDistance(List<Robot> robots) {
        return robots.stream().map(robot -> robot.distance).map(Short::intValue).reduce(Integer::sum).orElseThrow();
    }
}
