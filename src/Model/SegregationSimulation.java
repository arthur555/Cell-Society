package Model;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class SegregationSimulation extends Simulation {
    //    No wrap arounds for Game of Life
    private static final boolean ROW_WRAP = false;
    private static final boolean COLUMN_WRAP = false;

    private double satisfactionThreshold;
    private int numEmptyCells;

    public SegregationSimulation(int numRows, int numColumns, Map<Point, CellStates.SegregrationStates> initialState, double threshold) {
        super(numRows, numColumns, initialState);
        this.satisfactionThreshold = threshold;
        initializeCellsThreshold();
        this.grid.setSwapQuota(numEmptyCells);
    }


    protected void initializeGrid() {
        this.grid = new SegregationGrid(numRows, numColumns, ROW_WRAP, COLUMN_WRAP);
    }

    /**
     * initialize Cells and put them on grid
     */
    protected void initializeCells(Map<Point, ? extends Enum> initialParam) {
        numEmptyCells = 0;
        for (Map.Entry entry : initialParam.entrySet()) {
            Point position = (Point) entry.getKey();
            CellStates.SegregrationStates state = (CellStates.SegregrationStates) entry.getValue();
            if (grid.getMatrix().get(position) != null) {
                throw new IllegalArgumentException("InitialState Duplicate Point Error");
            }
            if (state == CellStates.SegregrationStates.EMPTY) {
                grid.addEmptyPosition(position);
                numEmptyCells++;
            }
            SegregationCell cell = new SegregationCell(position, (SegregationGrid) grid, state, satisfactionThreshold);
            grid.getMatrix().put(position, cell);
        }
    }

    protected void initializeCellsThreshold() {
        for (Cell cell : this.grid.getMatrix().values()) {
            SegregationCell c = (SegregationCell) cell;
            c.setSatisfactionThreshold(this.satisfactionThreshold);
        }
    }

    protected void initializeStatistics() {
        this.statistics = new EnumMap<CellStates.SegregrationMood, Integer>(CellStates.SegregrationMood.class);
        statistics.put(CellStates.SegregrationMood.SATISFIED, 0);
        statistics.put(CellStates.SegregrationMood.DISSATISFIED, 0);
    }
    protected void initializeView() {
        this.view = new HashMap<Point, CellStates.SegregrationStates>();
    }

    public void step() {
        this.grid.setSwapQuota(numEmptyCells);
        for (Cell cell: grid.getMatrix().values()) {
            cell.calculateNextState();
        }
        for (Cell cell: grid.getMatrix().values()) {
            cell.updateState();
            if (cell.stateChanged) {
                cell.initializeNeighbors();
                cell.initializeNeighborsNeighbors();
            }
        }
    }
    public void render() {
        int numSatisfied = 0;
        int numDissatisfied = 0;
        view.clear();
        for (Map.Entry<Point, Cell> entry: grid.getMatrix().entrySet()) {
            SegregationCell cell = (SegregationCell) entry.getValue();
            if (cell.isSatisfied()) {
                numSatisfied++;
            }
            else {
                numDissatisfied++;
            }
            if (entry.getValue().stateChanged) {
                view.put(entry.getKey(), entry.getValue().currentState);
            }
        }
        statistics.put(CellStates.SegregrationMood.SATISFIED, numSatisfied);
        statistics.put(CellStates.SegregrationMood.DISSATISFIED, numDissatisfied);
    }


    public String toString() {
        return "Segregation Simulation";
    }
}
