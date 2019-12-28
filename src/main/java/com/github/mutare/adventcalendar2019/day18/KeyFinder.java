package com.github.mutare.adventcalendar2019.day18;

import java.util.*;
import java.util.stream.Collectors;

import static com.github.mutare.adventcalendar2019.day18.Speleologist.Direction.*;
import static java.util.Arrays.stream;

public class KeyFinder {
    private Map<Key, List<Key>> vertices = new HashMap<>();
    private int[][] distances;
    private int[][] previous;
    private final List<Character> keysNames;
    private final Map<Way, Integer> ways;
    //List<Key> seen = new ArrayList<>();

    public void create(char c) {
        Key start = vertices.keySet().stream().filter(key -> key.name == c).findFirst().orElseThrow();
        for (int k = 0; k < vertices.get(start).size(); k++) {
            Key key = vertices.get(start).get(k);
            Set<Character> route = new HashSet<>();
            route.add(start.name);
            goTo(key, new HashSet<>(), route);
        }
        System.out.println("Done");
    }

    private void goTo(Key key, Set<Character> opened, Set<Character> route) {
        opened.add(Character.toUpperCase(key.name));
        route.add(key.name);
        List<Key> collect = vertices.keySet().stream()
                .filter(key1 -> key1.name != key.name)
                .filter(key1 -> key1.isAccessible(opened))
                .filter(key1 -> !route.contains(key1.name))
                .collect(Collectors.toList());
        for (Key k : collect) {
            vertices.get(key).add(k);
            Set<Character> characters = new HashSet<>(opened);
            goTo(k, characters, route);
        }
    }


    public int getShortestPathSteps() {

        Set<Key> seen = new HashSet<>();
        LinkedList<Key> q = new LinkedList<>();
        LinkedList<Integer> distance = new LinkedList<>();
        LinkedList<String> route = new LinkedList<>();

        Set<Integer> results = new HashSet<>();

        Key start = vertices.keySet().stream().filter(key -> key.name == '@').findFirst().get();
        for (Key k : vertices.get(start).stream().filter(key -> !route.contains(key.name)).collect(Collectors.toSet())) {
            q.add(k);
            distance.add(ways.get(Way.of(findInView('@', view), k.location)));
            route.add("@");
        }
        seen.add(start);

        while (!q.isEmpty()) {
            Key current = q.poll();
            int currentDistance = distance.poll();
            String currentRoute = route.poll();
            currentRoute += current.name;
System.out.println("Current route : " + currentRoute);
            if (new ArrayList<>(currentRoute.chars().mapToObj(value -> (char) value).collect(Collectors.toList())).containsAll(keysNames)) {
                results.add(currentDistance);
            }
            for (Key k : vertices.get(current).stream().filter(key -> !route.contains(key.name)).collect(Collectors.toSet())) {
                q.add(k);
                distance.add(currentDistance + ways.get(Way.of(current.location, k.location)));
                route.add(currentRoute);
            }
            seen.add(current);


        }
        return results.stream().min(Integer::compareTo).orElseThrow();
    }


    static class Key {
        private final Location location;
        char name;
        Set<Character> doors;

        public Key(char name, Set<Character> doors, Location location) {
            this.name = name;
            this.doors = doors;
            this.location = location;
        }

        public static Key of(char name, Set<Character> doorStack, Location location) {
            return new Key(name, new HashSet<Character>(doorStack), location);
        }

        public boolean isAccessible(Set<Character> openedDoors) {
            return !(doors.stream().filter(character -> !openedDoors.contains(character)).count() > 0);
        }

        @Override
        public int hashCode() {
            int hash = 17;
            hash *= 31 * name;
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (!(obj instanceof Key)) return false;
            Key key = (Key) obj;
            return key.name == this.name;
        }
    }

    private char[][] view;

    public KeyFinder(char[][] view) {
        this.view = view;
        ways = createKeyToKeyWays(copy(view));
        keys = scan(copy(view));
        keysNames = keys.stream().map(key -> key.name).collect(Collectors.toList());
        vertices.put(Key.of('@', new HashSet<>(getDoors(view)), findInView('@', view)), keys.stream().filter(key -> key.doors.isEmpty()).collect(Collectors.toList()));
        for (Key key : keys) {
            vertices.put(key, new ArrayList<>());
        }

        create('@');

    }

    private Set<Character> getDoors(char[][] map) {
        Set<Character> doors = new HashSet<>();
        for (int i = 0; i < view[0].length; i++)
            for (int j = 0; j < view.length; j++) {
                if (map[j][i] >= 65 && map[j][i] <= 90)
                    doors.add(map[j][i]);
            }
        return doors;
    }

    int stepsCount = -1;

    Set<Key> keys;


    private Map<Way, Integer> createKeyToKeyWays(char[][] map) {
        Map<Way, Integer> ways = new HashMap<>();
        List<Character> keys = new ArrayList<>();
        keys.add('@');
        for (int j = 0; j < map.length; j++)
            for (int i = 0; i < map[0].length; i++) {
                if (isDoor(map[j][i]))
                    map[j][i] = '.';
                if (isKey(map[j][i])) {
                    keys.add(map[j][i]);
                }
            }
        print(map);
        for (int i = 0; i < keys.size(); i++)
            for (int j = i + 1; j < keys.size(); j++) {
                Character key0 = keys.get(i);
                Character key1 = keys.get(j);
                ways.put(Way.of(findInView(key0, map), findInView(key1, map)), findWayBetweenKeys(map, key0, key1));
            }


        return ways;
    }


    private boolean isDoor(char c) {
        return c >= 65 && c <= 90;
    }

    private boolean isKey(char c) {
        return c >= 97 && c <= 122;
    }

    private boolean isSpeleologist(char c) {
        return c == '@';
    }

    int copies = 0;

    private char[][] copy(char[][] map) {
        return stream(map).map(char[]::clone).toArray(char[][]::new);
    }

    private void goTo(Character key, Location currentLocation, int steps, Set<Character> route, Set<Character> openedDoors) {

        //Set<Character> leftRoute = new HashSet<>(keysNames);
//        leftRoute.removeAll(route);
//        String s = leftRoute.stream().sorted().map(Object::toString).collect(Collectors.joining());
//        if (minForRoutes.get(s) != null) {
//            return;
//        }

        Location keyLocation = findInView(key, view);
        Way way = Way.of(currentLocation, keyLocation);
        int distance = ways.get(way);
        route.add(key);
        openedDoors.add(Character.toUpperCase(key));
        List<Key> collect = keys.stream()
                .filter(key1 -> key1.name != key)
                .filter(key1 -> !isKeyOnTheRoute(key1, route))
                .filter(key1 -> key1.isAccessible(openedDoors))
                .collect(Collectors.toList());
        if (collect.isEmpty()) {
            if (stepsCount == -1 || stepsCount > steps + distance)
                stepsCount = steps + distance;
        } else
            for (Key key2 : collect) {
                goTo(key2.name, keyLocation, steps + distance, new HashSet<>(route), new HashSet<>(openedDoors));
            }
    }

    private boolean isKeyOnTheRoute(Key key1, Set<Character> route) {
        return route.contains(key1.name);
    }

    private void print(char[][] map) {
        for (int j = 0; j < map.length; j++) {
            for (int i = 0; i < map[0].length; i++) {
                System.out.print(map[j][i]);
            }
            System.out.println();
        }
        System.out.println();
    }


    private Integer findWayBetweenKeys(char[][] map, Character c0, Character c1) {
        return findWaysFrom(map, c0, true).get(c1.toString());
    }

    private Map<String, Integer> findWaysFrom(char[][] map, Character c0, boolean fly) {
        Speleologist track = Speleologist.of(findInView(c0, map));
        Map<String, Integer> foundObjects = new HashMap<>();
        int[][] distances = new int[map.length][map[0].length];
        int distanceCounter = 0;

        Speleologist initialPosition = null;
        while (!track.equals(initialPosition)) {
            char ahead = whatIsAhead(track, map);

            if (canMove(ahead, fly)) {
                track.moveAhead();

                if (distances[track.location.y][track.location.x] == 0) {
                    distanceCounter++;
                    distances[track.location.y][track.location.x] = distanceCounter;
                } else {
                    distanceCounter = distances[track.location.y][track.location.x];
                }
                if (map[track.location.y][track.location.x] == c0) {
                    distanceCounter = 0;
                    distances[track.location.y][track.location.x] = distanceCounter;
                }

                char here = map[track.location.y][track.location.x];
                if (here >= 97 && here <= 122 && here != c0) {
                    foundObjects.put(String.valueOf(here), distances[track.location.y][track.location.x]);
                }

                if (initialPosition != null) {
                    turn(track, false);
                }
            } else {
                if (initialPosition == null) {
                    initialPosition = track.clone();
                }
                turn(track, true);
            }
        }
        return foundObjects;
    }


    private Set<Key> scan(char[][] map) {
        Speleologist track = Speleologist.of(findInView('@', map));
        Set<Character> doorStack = new HashSet<>();

        Set<Key> foundObjects = new HashSet<>();
        int[][] distances = new int[map.length][map[0].length];
        int distanceCounter = 0;

        Speleologist initialPosition = null;
        while (!track.equals(initialPosition)) {
            char ahead = whatIsAhead(track, map);


            if (canMove(ahead, true)) {
                track.moveAhead();

                if (ahead >= 97 && ahead <= 122) {
                    foundObjects.add(Key.of(ahead, doorStack, track.location.clone()));
                }

                if (ahead >= 65 && ahead <= 90) {
                    if (doorStack.contains(ahead)) doorStack.remove(ahead);
                    else doorStack.add(ahead);
                }

                if (distances[track.location.y][track.location.x] == 0) {
                    distanceCounter++;
                    distances[track.location.y][track.location.x] = distanceCounter;
                } else {
                    distanceCounter = distances[track.location.y][track.location.x];
                }
                if (map[track.location.y][track.location.x] == '@') {
                    distanceCounter = 0;
                    distances[track.location.y][track.location.x] = distanceCounter;
                }

                if (initialPosition != null) {
                    turn(track, false);
                }
            } else {
                if (initialPosition == null) {
                    initialPosition = track.clone();
                }
                turn(track, true);
            }
        }
        return foundObjects;
    }

    private boolean canMove(char ahead, boolean fly) {
        return fly ? ahead != '#' : (ahead != '#' && !isDoor(ahead));
    }

    private void turn(Speleologist speleologist, boolean clockwise) {
        switch (speleologist.direction) {
            case NORTH:
                speleologist.direction = clockwise ? EAST : WEST;
                break;
            case SOUTH:
                speleologist.direction = clockwise ? WEST : EAST;
                break;
            case EAST:
                speleologist.direction = clockwise ? SOUTH : NORTH;
                break;
            case WEST:
                speleologist.direction = clockwise ? NORTH : SOUTH;
                break;
        }
    }

    private char whatIsAhead(Speleologist speleologist, char[][] map) {
        int x = speleologist.location.x;
        int y = speleologist.location.y;
        switch (speleologist.direction) {
            case NORTH:
                y--;
                break;
            case SOUTH:
                y++;
                break;
            case EAST:
                x++;
                break;
            case WEST:
                x--;
                break;
        }
        return map[y][x];
    }

    private Location findInView(char c, char[][] map) {
        for (int i = 0; i < map[0].length; i++)
            for (int j = 0; j < map.length; j++) {
                if (map[j][i] == c) return Location.of(i, j);
            }
        return null;
    }
}
