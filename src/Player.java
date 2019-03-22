import java.util.Arrays;

/**
 * @author Leonhard Gahr
 */
class Player {
    private final String name;
    private int countDartsThrown;
    private final Visit[] visits;

    Player(String name) {
        this.name = name;
        countDartsThrown = 0;
        visits = new Visit[10];
    }

    /**
     * Get the number of points the player needs to have exactly 501 points
     *
     * @return the remaining points
     */
    int getRemainingPoints() {
        return 501 - Arrays.stream(visits).mapToInt(visit -> visit == null ? 0 : visit.getValue()).sum();
    }

    /**
     * add a visit to the player
     *
     * @param visit the current visit
     * @return whether the visit was added or not
     */
    boolean addVisit(Visit visit) {
        countDartsThrown += visit.getFields().length;
        int tempRem = getRemainingPoints() - visit.getValue();

        if (tempRem < 0 || tempRem == 1) {
            // invalid visit, if the remaining points are lower equals 1
            return false;
        } else if (tempRem == 0 && !visit.getLastField().isDoubleField()) {
            // invalid visit, if the last move was't a double
            return false;
        }

        return appendVisit(visit);
    }

    /**
     * append a visit to the visits
     *
     * @param visit the visit to append
     * @return whether the visit could be appended or not
     */
    private boolean appendVisit(Visit visit) {
        for (int i = 0; i < visits.length; i++) {
            if (visits[i] == null) {
                visits[i] = visit;
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return this.name;
    }

    int getCountDartsThrown() {
        return countDartsThrown;
    }
}
