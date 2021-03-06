package Model;//package Model;

/**
 * Abstract representation of a Fire Cell.
 *
 * @author jz192
 */



public class FireCell extends Cell {
    private double probCatchFire;
    public FireCell(Point position, FireGrid grid, CellStates.FireStates state, double probCatchFire) {
        super(position, grid, state);
        this.probCatchFire = probCatchFire;
    }

    public void setProbCatchFire(double probCatchFire) {
        this.probCatchFire = probCatchFire;
    }

    @Override
    protected void calculateNextState() {
        switch ((CellStates.FireStates) currentState) {
            case BURNING:
                nextState = CellStates.FireStates.EMPTY;
                break;
            case EMPTY:
                nextState = CellStates.FireStates.EMPTY;
                break;
            case TREE:
                boolean burningNeighbors = false;
                for (Directions.FourDirections direction : Directions.FourDirections.values()) {
                    Point neighborPosition = getPosition().add(direction.getDirection());
                    if (!grid.outOfBounds(neighborPosition)) {
                        if (grid.getCell(neighborPosition).getCurrentState() == CellStates.FireStates.BURNING) {
                            burningNeighbors = true;
                        }
                    }
                }
                if (burningNeighbors && random.nextDouble() < probCatchFire) {
                    nextState = CellStates.FireStates.BURNING;
                }
                else {
                    nextState = CellStates.FireStates.TREE;
                }
        }
    }


    @Override
    protected void updateState() {
        if (nextState != currentState) {
            currentState = nextState;
        }
    }
}
