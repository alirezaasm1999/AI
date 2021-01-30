package AI;

import Game.Board;


public class MiniMaxCombined {

    private static final int MAX_DEPTH = 12;

    private MiniMaxCombined() {
    }

   
    public static int miniMax(Board board, int depth, int alpha, int beta,
            boolean isMax) {
        int boardVal = evaluateBoard(board, depth);

        if (Math.abs(boardVal) > 0 || depth == 0 
                || !board.anyMovesAvailable()) {
            return boardVal;
        }

        if (isMax) {
            int highestVal = Integer.MIN_VALUE;
            for (int row = 0; row < board.getWidth(); row++) {
                for (int col = 0; col < board.getWidth(); col++) {
                    if (!board.isTileMarked(row, col)) {
                        board.setMarkAt(row, col, 'X');
                        highestVal = Math.max(highestVal, miniMax(board,
                                depth - 1, alpha, beta, false));
                        board.setMarkAt(row, col, ' ');
                        alpha = Math.max(alpha, highestVal);
                        if (alpha >= beta) {
                            return highestVal;
                        }
                    }
                }
            }
            return highestVal;
        } else {
            int lowestVal = Integer.MAX_VALUE;
            for (int row = 0; row < board.getWidth(); row++) {
                for (int col = 0; col < board.getWidth(); col++) {
                    if (!board.isTileMarked(row, col)) {
                        board.setMarkAt(row, col, 'O');
                        lowestVal = Math.min(lowestVal, miniMax(board,
                                depth - 1, alpha, beta, true));
                        board.setMarkAt(row, col, ' ');
                        beta = Math.min(beta, lowestVal);
                        if (beta <= alpha) {
                            return lowestVal;
                        }
                    }
                }
            }
            return lowestVal;
        }
    }

   
    public static int[] getBestMove(Board board) {
        int[] bestMove = new int[]{-1, -1};
        int bestValue = Integer.MIN_VALUE;

        for (int row = 0; row < board.getWidth(); row++) {
            for (int col = 0; col < board.getWidth(); col++) {
                if (!board.isTileMarked(row, col)) {
                    board.setMarkAt(row, col, 'X');
                    int moveValue = miniMax(board, MAX_DEPTH, Integer.MIN_VALUE,
                            Integer.MAX_VALUE, false);
                    board.setMarkAt(row, col, ' ');
                    if (moveValue > bestValue) {
                        bestMove[0] = row;
                        bestMove[1] = col;
                        bestValue = moveValue;
                    }
                }
            }
        }
        return bestMove;
    }

    private static int evaluateBoard(Board board, int depth) {
        int rowSum = 0;
        int bWidth = board.getWidth();
        int Xwin = 'X' * bWidth;
        int Owin = 'O' * bWidth;

        for (int row = 0; row < bWidth; row++) {
            for (int col = 0; col < bWidth; col++) {
                rowSum += board.getMarkAt(row, col);
            }
            if (rowSum == Xwin) {
                return 10 + depth;
            } else if (rowSum == Owin) {
                return -10 - depth;
            }
            rowSum = 0;
        }

        rowSum = 0;
        for (int col = 0; col < bWidth; col++) {
            for (int row = 0; row < bWidth; row++) {
                rowSum += board.getMarkAt(row, col);
            }
            if (rowSum == Xwin) {
                return 10 + depth;
            } else if (rowSum == Owin) {
                return -10 - depth;
            }
            rowSum = 0;
        }

        
        rowSum = 0;
        for (int i = 0; i < bWidth; i++) {
            rowSum += board.getMarkAt(i, i);
        }
        if (rowSum == Xwin) {
            return 10 + depth;
        } else if (rowSum == Owin) {
            return -10 - depth;
        }

        rowSum = 0;
        int indexMax = bWidth - 1;
        for (int i = 0; i <= indexMax; i++) {
            rowSum += board.getMarkAt(i, indexMax - i);
        }
        if (rowSum == Xwin) {
            return 10 + depth;
        } else if (rowSum == Owin) {
            return -10 - depth;
        }

        return 0;
    }
}
