import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int dimension;
    final int[] board;
    private int blankIndex = 0;
    private int manhattan;

    public Board(int[][] blocks) {
        dimension = blocks[0].length;
        board = new int[(dimension * dimension) + 1];
        int index = 1;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (blocks[i][j] == 0) {
                    blankIndex = index;
                }
                board[index++] = blocks[i][j];
            }
        }
        computeManhattan();
    }

    Board(int[] neighborBoard, int dimension, int blankIndex) {
        this.board = neighborBoard;
        this.dimension = dimension;
        this.blankIndex = blankIndex;

        computeManhattan();
    }

    public int dimension() {
        return dimension;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(dimension);
        sb.append(System.lineSeparator());
        for (int i = 1; i < board.length; i++) {
            sb.append(" ");
            sb.append(board[i]);
            if (i % dimension == 0) sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    public int hamming() {

        int hamming = 0;
        for (int i = 1; i < board.length; i++) {
            if (board[i] != 0 && board[i] != i) hamming++;
        }
        return hamming;
    }

    public int manhattan() {
        return manhattan;
    }

    private void computeManhattan() {
        for (int i = 1; i < board.length; i++) {
            if (board[i] != 0 && board[i] != i) {
                int rowOffset = Math.abs((board[i] - i) / dimension);
                int colOffset = Math.abs(board[i] - i) - (dimension * rowOffset);
                manhattan += rowOffset + colOffset;
            }
        }
    }

    public boolean isGoal() {
        for (int i = 1; i < board.length - 1; i++) {
            if (board[i] != i) return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object y) {
        // Not doing any validation of y

        for (int i = 1; i < board.length; i++) {
            if (board[i] != ((Board)y).board[i]) return false;
        }

        return true;
    }

    public static void main(String[] args) {

    }

    public Iterable<Board> neighbors() {
        List boardList = new ArrayList<Board>();
        swapWithRight(boardList);
        swapWithBelow(boardList);
        swapWithAbove(boardList);
        swapWithLeft(boardList);


        return boardList;
    }

    private void swapWithLeft(List boardList) {
     // check if on left edge
// NEED TO IMPLEMENT THIS
    }

    private void swapWithAbove(List boardList) {
//        if (blankIndex <= dimension) return;
// NEED TO IMPLEMENT THIS
    }

    private void swapWithBelow(List boardList) {
// NEED TO ERROR CHECK IF ON BOTTOM ROW
        int[] neighborBoard = new int[board.length];
        System.arraycopy(board, 0, neighborBoard, 0, board.length);
        int newBlankIndex = blankIndex + dimension;
        neighborBoard[blankIndex] = neighborBoard[newBlankIndex];
        neighborBoard[newBlankIndex] = 0;

        Board neighbor = new Board(neighborBoard, dimension, newBlankIndex);
        boardList.add(neighbor);
    }

    private void swapWithRight(List boardList) {
// NEED TO IMPLEMENT THIS
        int[][] neighbor1 = new int[][] { {1, 0, 2}, {3, 4, 5}, {6, 7, 8} };
        Board b1 = new Board(neighbor1);
        boardList.add(b1);
    }
}
