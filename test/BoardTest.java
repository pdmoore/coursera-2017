import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardTest {

    @Test
    public void Dimension_3by3() {
        int[][] blocks = new int[][] { {0, 1, 2}, {3, 4, 5}, {6, 7, 8} };
        Board b = new Board(blocks);
        assertEquals(3, b.dimension());
    }

    @Test
    public void Dimension_2by2() {
        int[][] blocks = new int[][] { {0, 1,}, {2, 3,}, {4, 5} };
        Board b = new Board(blocks);
        assertEquals(2, b.dimension());
    }

    @Test
    public void ToString() {
        int[][] blocks = new int[][] { {1, 2}, {3, 4}, {5, 0} };
        Board b = new Board(blocks);
        assertEquals("2\n 1 2\n 3 4\n", b.toString());
    }

    @Test
    public void Hamming_NoneInWrongPosition_NoMoves() {
        int[][] blocks = new int[][] { {1, 2, 3}, {4, 5, 6}, {7, 8, 0} };
        Board b = new Board(blocks);
        assertEquals(0, b.hamming(), "all blocks are in final position, hamming score is 0" );
    }

    @Test
    public void Hamming_SomeBlocksInWrongPosition_NoMoves() {
        int[][] blocks = new int[][] { {8, 1, 3}, {4, 0, 2}, {7, 6, 5} };
        Board b = new Board(blocks);
        assertEquals(5, b.hamming(), "Blocks in wrong positions: 8, 1, 0, 2, 5, 6: hamming score is 5" );
    }

    @Test
    public void IsGoal_NoneInWrongPosition() {
        int[][] blocks = new int[][] { {1, 2, 3}, {4, 5, 6}, {7, 8, 0} };
        Board b = new Board(blocks);
        assertEquals(true, b.isGoal(), "all blocks are in final position, isGoal is true" );
    }

    @Test
    public void IsGoal_SomeBlocksInWrongPosition() {
        int[][] blocks = new int[][] { {8, 1, 3}, {4, 0, 2}, {7, 6, 5} };
        Board b = new Board(blocks);
        assertEquals(false, b.isGoal(), "any block not in final positions, isGoal is false. " );
    }

    @Test
    public void Manhattan_CellIsAboveAndLeftOfEndPosition() {
        int dimension = 3;
        int index = 1;
        int value = 8;
        int row = Math.abs((value - index) / dimension);
        assertEquals(2,  row , "how many rows does value need to move");

        int col = (value - index) - (dimension * row);
        assertEquals(1, col, "how many cols does value need to move");

        int manhattan = row + col;
        assertEquals(3, manhattan, "manhattan is how many rows/cols off from desired location");
    }

    @Test
    public void Manhattan_2InLowerLeft() {
        int dimension = 2;
        int index = 3;
        int value = 2;
        int rowIndexShouldBeIn = (int) Math.ceil((double) index/dimension);
        int rowValueShouldBeIn = (int) Math.ceil((double) value/dimension);
        int row = Math.abs(rowValueShouldBeIn - rowIndexShouldBeIn);
        assertEquals(1, row, "2 needs to move up one row");

        int col = Math.abs(Math.abs(value-index) - (dimension * row));
        assertEquals(1, col, "2 needs to move right one col");

        index = 4;
        value = 3;
        rowIndexShouldBeIn = (int) Math.ceil((double) index/dimension);
        rowValueShouldBeIn = (int) Math.ceil((double) value/dimension);
        row = Math.abs(rowValueShouldBeIn - rowIndexShouldBeIn);
        assertEquals(0, row, "3 is in correct row");
        col = Math.abs(Math.abs(value-index) - (dimension * row));
        assertEquals(1, col, "3 needs to move right one column");
    }

    @Test
    public void Manhattan_CellIsBelowAndRightOfEndPosition() {
        int dimension = 3;
        int index = 6;
        int value = 2;
        int row = Math.abs((value - index) / dimension);
        assertEquals(1, row, "2 is one row below where it ought to be");

        int col = Math.abs(value-index) - (dimension * row);
        assertEquals(1, col, "2 is one col right of where it ought to be");

        int manhattan = row + col;
        assertEquals(2, manhattan, "manhattan is how many rows/cols off from desired location");
    }

    @Test
    public void Manhattan_NoneInWrongPosition_NoMoves() {
        int[][] blocks = new int[][] { {1, 2, 3}, {4, 5, 6}, {7, 8, 0} };
        Board b = new Board(blocks);
        assertEquals(0, b.manhattan(), "all blocks are in final position, manhattan score is 0" );
    }

    @Test
    public void Manhattan_SomeBlocksInWrongPosition_NoMoves() {
        int[][] blocks = new int[][] { {8, 1, 3}, {4, 0, 2}, {7, 6, 5} };
        Board b = new Board(blocks);
        assertEquals(10, b.manhattan(), "Blocks in wrong positions: 8, 1, 0, 2, 5, 6: manhattan score is 10" );
    }

    @Test
    public void Manhattan_2by2_BlocksInWrongPosition_NoMoves() {
        int[][] blocks = new int[][] { {1, 0}, {2, 3} };
        Board b = new Board(blocks);
        assertEquals(3, b.manhattan(), "2 has to move twice, 3 has to move once");
    }

    @Test
    public void Equals_BoardsAreEqual() {
        int[][] blocks = new int[][] { {1, 2, 3}, {4, 5, 6}, {7, 8, 0} };
        Board b = new Board(blocks);
        Board equalBoard = new Board(blocks);
        assertTrue(b.equals(equalBoard));
    }

    @Test
    public void Equals_BoardsNotEqual() {
        int[][] blocks = new int[][] { {1, 2, 3}, {4, 5, 6}, {7, 8, 0} };
        Board b = new Board(blocks);
        int[][] scrambled = new int[][] { {3, 1, 2}, {4, 5, 6}, {7, 8, 0} };
        Board unequalBoard = new Board(scrambled);
        assertFalse(b.equals(unequalBoard));
    }

    @Test
    public void Equals_Null_notEqual() {
        int[][] blocks = new int[][] { {1, 2, 3}, {4, 5, 6}, {7, 8, 0} };
        Board b = new Board(blocks);
        assertFalse(b.equals(null));
    }

    @Test
    public void Equals_NotaBoard_notEqual() {
        int[][] blocks = new int[][] { {1, 2, 3}, {4, 5, 6}, {7, 8, 0} };
        Board b = new Board(blocks);
        assertFalse(b.equals("This is not equals"));
    }

    @Test
    public void Equals_DimensionsDoNotAgree() {
        int[][] blocks = new int[][] { {1, 2, 3}, {4, 5, 6}, {7, 8, 0} };
        Board b = new Board(blocks);
        int[][] smallerBoard = new int[][] { {1, 2}, {3, 4} };
        Board unequalBoard = new Board(smallerBoard);
        assertFalse(b.equals(unequalBoard));
    }

    @Test
    public void Neighbors_TwoNeighbors_Right_and_Below() {
        int[][] blocks = new int[][] { {0, 1, 2}, {3, 4, 5}, {6, 7, 8} };
        Board b = new Board(blocks);
        Iterable<Board> iterable = b.neighbors();
        Iterator<Board> actualIter = iterable.iterator();
        Board actualBoard1 = actualIter.next();
        int[][] n1 = new int[][] { {1, 0, 2}, {3, 4, 5}, {6, 7, 8} };
        Board neighbor1 = new Board(n1);
        assertTrue(neighbor1.equals(actualBoard1), "first neighbor swaps the blank to the right");
        Board actualBoard2 = actualIter.next();

        int[][] n2 = new int[][] { {3, 1, 2}, {0, 4, 5}, {6, 7, 8} };
        Board neighbor2 = new Board(n2);
        assertTrue(neighbor2.equals(actualBoard2), "second neighbor swaps the blank to below");

        assertFalse(actualIter.hasNext(), "only two possible neighbors when blank is in a corner");
    }

    @Test
    public void Neighbors_TwoNeighbors_Left_and_Above() {
        int[][] blocks = new int[][] { {1, 2, 3}, {4, 5, 6}, {7, 8, 0} };
        Board b = new Board(blocks);
        Iterable<Board> iterable = b.neighbors();
        Iterator<Board> actualIter = iterable.iterator();

        Board actualBoard2 = actualIter.next();
        int[][] n2 = new int[][] { {1, 2, 3}, {4, 5, 0}, {7, 8, 6} };
        Board neighbor2 = new Board(n2);
        assertTrue(neighbor2.equals(actualBoard2), "first neighbor swaps the blank to above");

        Board actualBoard1 = actualIter.next();
        int[][] n1 = new int[][] { {1, 2, 3}, {4, 5, 6}, {7, 0, 8} };
        Board neighbor1 = new Board(n1);
        assertTrue(neighbor1.equals(actualBoard1), "second neighbor swaps the blank to the left");
    }

    @Test
    public void Neighbors_FourNeighbors_EachDirection() {
        int[][] blocks = new int[][] { {1, 2, 3}, {4, 0, 5}, {6, 7, 8} };
        Board b = new Board(blocks);

        int neighborCount = 0;
        for (Board b2 : b.neighbors()) {
            neighborCount++;
        }
        assertEquals(4, neighborCount, "blank is in center, can be swapped in 4 directions");
    }

    @Test
    public void Neighbors_bug_SwapBelowIndexOutOfBounds() {
        int[][] blocks = new int[][] { {1, 2}, {0, 3} };
        Board b = new Board(blocks);
        b.neighbors();
        assertTrue(true, "call to neighbors caused index out of bounds when blank was in lower left corner");
    }

    @Test
    public void Twin_blankInSecondRow() {
        int[][] blocks = new int[][] { {1, 2}, {0, 3} };
        Board b = new Board(blocks);
        Board actual = b.twin();
        assertEquals("2\n 2 1\n 0 3\n", actual.toString(), "twin should swap two blocks, but not the blank");
    }

    @Test
    public void Twin_blankInFirstRow() {
        int[][] blocks = new int[][] { {1, 0}, {2, 3} };
        Board b = new Board(blocks);
        Board actual = b.twin();
        assertEquals("2\n 2 0\n 1 3\n", actual.toString(), "twin should swap two blocks, but not the blank");
    }


}
