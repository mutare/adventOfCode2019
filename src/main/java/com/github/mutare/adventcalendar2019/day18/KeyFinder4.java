package com.github.mutare.adventcalendar2019.day18;

import java.util.*;

public class KeyFinder4 {
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

    static class Path {
        Location location0;
        Location location1;

        static Path of(Location location0, Location location1) {
            Path path = new Path();
            path.location0 = location0;
            path.location1 = location1;
            return path;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Path path = (Path) o;
            return location0.equals(path.location0) &&
                    location1.equals(path.location1);
        }

        @Override
        public int hashCode() {
            return Objects.hash(location0, location1);
        }
    }

    static class Location {
        static Location of(byte x, byte y) {
            Location location = new Location();
            location.x = x;
            location.y = y;
            return location;
        }

        private byte x;
        private byte y;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Location location = (Location) o;
            return x == location.x &&
                    y == location.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static class Robot {
        private Location location;
        private short distance;

        public Robot(byte c, byte r) {
            this.location = Location.of(c, r);
        }

        public Robot(Robot robot) {
            this.location = Location.of(robot.location.x, robot.location.y);
            this.distance = robot.distance;
        }

        public Robot(Location location) {
            this.location = location;
        }

        public static Robot of(Location location, short distance) {
            Robot robot = new Robot(location);
            robot.distance = distance;
            return robot;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Robot robot = (Robot) o;
            return this.location.equals(robot.location);
        }

        @Override
        public int hashCode() {
            int hash = 17;
            hash *= 31 * location.hashCode();
            return hash;
        }

    }

    class State {
        Map<Character, Collection<Character>> keys;
        //List<Character> route;
        //List<Robot> robots;

        public State() {
            //this.route = new ArrayList<>();
            //this.robots = new ArrayList<>();
            this.keys = new HashMap<>();
        }

        public State(State state) {
            //this.robots = new ArrayList<>();
            this.keys = new HashMap<>();
            //this.route = new ArrayList<>(state.route);

//            for (Robot robot : state.robots) {
//                this.robots.add(new Robot(robot));
//            }
            for (Character character : state.keys.keySet()) {
                this.keys.put(character, new ArrayList<>(state.keys.get(character)));
            }
        }

//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            State state = (State) o;
//            return this.route.equals(state.route);
//        }
//
//        @Override
//        public int hashCode() {
//            int hash = 17;
//            hash *= 31 * route.hashCode();
//            return hash;
//        }

        @Override
        public String toString() {
            return "State{" +
                    "keys=" + keys +
                    '}';
        }


        public void init() {
            for (int i = 0; i < initialRobots.size(); i++) {
                keys.putAll(getRobotKeys(initialRobots.get(i)));
            }

        }

        class Scanner {
            Location location;
            Set<Character> doors = new HashSet<>();

            public Scanner(Location location) {
                this.location = location;
            }

            public Scanner(Scanner scanner) {
                this.location = Location.of(scanner.location.x, scanner.location.y);
                this.doors = new HashSet<>(scanner.doors);
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Scanner scanner = (Scanner) o;
                return location.equals(scanner.location);
            }

            @Override
            public int hashCode() {
                int hash = 17;
                hash *= 31 * location.hashCode();
                return hash;
            }
        }

        private Map<Character, Collection<Character>> getRobotKeys(Robot robot) {
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
                if (isKey(scanner.location))
                    foundKeys.put(getCharAt(scanner.location), new LinkedList<>(scanner.doors));//putIfAbsent????
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

    byte[] dr = new byte[]{-1, 0, 1, 0};
    byte[] dc = new byte[]{0, 1, 0, -1};
    Deque<State> q = new LinkedList<>();

    Set<State> seen = new HashSet<>();
    static Set<Character> allKeys = new HashSet<>();

    public KeyFinder4(char[][] map) {
        this.map = map;
        int height = map.length;
        int width = map[0].length;
        State state = new State();
        for (byte r = 0; r < height; r++)
            for (byte c = 0; c < width; c++) {
                if (map[r][c] == '@') {
                    //state.robots.add(new Robot(c, r));
                    initialRobots.add(new Robot(c, r));
                }
                if ('a' <= map[r][c] && map[r][c] <= 'z') {
                    allKeys.add(map[r][c]);
                    allKeysLocations.put(map[r][c], Location.of(c, r));
                }
            }
        state.init();
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

    private Integer getMidMin(MidPath path, State state) {
        Map<MidPath, Integer> mids = new HashMap<>();
        //if (path.tail.size() > 10) System.out.println(state.keys);
        int min = Integer.MAX_VALUE;
        if (path.tail.size() == 1) return getDistanceFor(path.head, path.tail.iterator().next());
        state.keys.entrySet().stream().filter(characterCollectionEntry -> characterCollectionEntry.getValue().isEmpty()).map(Map.Entry::getKey).forEach(c -> {
            MidPath subPath = new MidPath();
            subPath.tail = new HashSet<>(state.keys.keySet());
            subPath.tail.remove(c);
            subPath.head = c;
            State newstate = new State(state);
            newstate.keys.remove(c);
            newstate.keys.values().forEach(characters -> characters.remove(Character.toUpperCase(c)));
            Integer distance = midDistances.get(subPath);
            if (distance == null) {
                distance = getMidMin(subPath, newstate);
                if (null == midDistances.put(subPath, distance) && midDistances.size() % 100 == 0)
                    System.out.println("Midmin size : " + midDistances.size());
            } else {
                reuse++;
                if (reuse%1000 == 0)System.out.println("Reuse count : " + reuse);
            }
            mids.put(subPath, distance);
        });

        for (Map.Entry<MidPath, Integer> e : mids.entrySet()) {
            int distance = getDistanceFor(path.head, e.getKey().head);
            if (min > (e.getValue() + distance)) {
                min = e.getValue() + distance;
            }
        }

        return min;
    }

    Map<MidPath, Integer> midDistances = new HashMap<>();

    public int getShortestPathSteps() {
        State state = q.pop();
        Map<MidPath, Integer> midDistances = new HashMap<>();
        state.keys.entrySet().stream().filter(characterCollectionEntry -> characterCollectionEntry.getValue().isEmpty()).map(Map.Entry::getKey).forEach(c -> {
            MidPath mid = new MidPath();
            mid.tail = new HashSet<>(state.keys.keySet());
            mid.tail.remove(c);
            mid.head = c;
            State newstate = new State(state);
            newstate.keys.remove(c);
            newstate.keys.values().forEach(characters -> characters.remove(Character.toUpperCase(c)));
            midDistances.putIfAbsent(mid, getMidMin(mid, newstate));
        });

        int min = Integer.MAX_VALUE;
        for (Map.Entry<MidPath, Integer> e : midDistances.entrySet()) {
            int noOfRobot = getNoOfRobot(e.getKey().head);
            Location location = initialRobots.get(noOfRobot).location;
            int distance = getDistanceFor(location, e.getKey().head);
            if (min > (e.getValue() + distance)) {
                min = e.getValue() + distance;
            }
        }

        return min;

        //go(q.pop(), 0, new LinkedList<>());
//
        /*
        int count = 0;
        while (!q.isEmpty()) {
            State state = q.pop();

            //if (seen.contains(state)) continue;
            //seen.add(state);
            count++;
            if (count % 10000 == 0) System.out.println(seen.size() + " : " + q.size());
//
////
//            //System.out.println(state);
//
            if (state.keys.isEmpty()) {
                routes.add(state.route);
                continue;
            }
            state.keys.entrySet().stream().filter(characterCollectionEntry -> characterCollectionEntry.getValue().isEmpty()).map(Map.Entry::getKey).forEach(c -> {
                State newState = new State(state);
                newState.route.add(c);
                newState.keys.remove(c);
                newState.keys.values().forEach(characters -> characters.remove(Character.toUpperCase(c)));
                q.add(newState);
            });
        }
*/
//        int min = Integer.MAX_VALUE;
////        for (List<Character> route : routes) {
////            int distance = getDistanceForRoute(route);
////            if (distance < min) min = distance;
////        }
////        return min;
        //return min;
        //return min;
    }


    class MidPath {
        char head;
        Set<Character> tail;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MidPath midPath = (MidPath) o;
            return head == midPath.head &&
                    tail.equals(midPath.tail);
        }

        @Override
        public int hashCode() {
            return Objects.hash(head, tail);
        }
    }

    private int go(State state, int n, List<Character> route) {
        for (Map.Entry<Character, Collection<Character>> entry : state.keys.entrySet()) {
            if (!entry.getValue().isEmpty()) continue;
            char c = entry.getKey();
            //if (n < 4) System.out.println(n + " : " + c);

            State newState = new State(state);
            newState.keys.remove(c);
            newState.keys.values().forEach(characters -> characters.remove(Character.toUpperCase(c)));
            List<Character> newRoute = new LinkedList<>(route);
            newRoute.add(c);
            if (newState.keys.isEmpty()) {
                int distance = getDistanceForRoute(newRoute);
                if (distance < this.min) {
                    this.min = distance;
                    System.out.println(newRoute + "(" + distance + ")");
                }
                routesCount++;
                return distance;
            } else if (getDistanceForRoute(route) > min) {
                return -1;
            } else {
                go(newState, n + 1, newRoute);
            }
        }
        return 0;
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

    private int getDistanceFor(char c0, char c1) {
        Location location0 = availablePositions.get(getNoOfRobot(c0)).get(c0);
        Location location1 = availablePositions.get(getNoOfRobot(c1)).get(c1);
        return distances.get(getNoOfRobot(c1)).get(Path.of(location0, location1));
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

    public int getDistance(List<Robot> robots) {
        return robots.stream().map(robot -> robot.distance).map(Short::intValue).reduce(Integer::sum).orElseThrow();
    }
}
