package lk.ijse.dep.service;

public class BoardImpl implements Board {
    ////////attribute///////////////
    private final Piece[][] pieces;

    private final BoardUI boardUI;
////////////////////////////////////////////////////////////////////////////////

    public BoardImpl(BoardUI boardUI) {
        this.boardUI = boardUI;
        ////////api hadapu 2d array ekata agayan labdima/////////////
        this.pieces = new Piece[NUM_OF_COLS][NUM_OF_ROWS];
/////////////2d wala null value walata piece.Empty samana kirima//////////
        for (int i = 0; i < NUM_OF_COLS; i++) {
            for (int j = 0; j < NUM_OF_ROWS; j++) {
                pieces[i][j] = Piece.EMPTY;
            }
        }
    }
///////////////////////////////////////////////////////////////////////////////
    ///////// bordUI private asscess modify eka nisa getters walin value return kirima//////////

    @Override
    public BoardUI getBoardUI() {
        return boardUI;
    }

    ////////////////////////////////////////////////////////////////////////////
    @Override
    public int findNextAvailableSpot(int col) {
        //bolayak dann puluwn tenak check kirima
        for (int i = 0; i < NUM_OF_ROWS; i++) {
            if (pieces[col][i] == Piece.EMPTY)
                return i;
        }
        return -1;
    }
//////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean isLegalMove(int col) {
        // colum ek emptyd kiyla check kirima
        if (findNextAvailableSpot(col) != -1) {
            return true;
        } else {
            return false;
        }
    }
////////////////////////////////////////////////////////////////////////

    @Override
    public boolean existLegalMoves() {
        for (int i = 0; i < pieces.length; i++) {
            if (findNextAvailableSpot(i) != -1) {
                return true;
            }
        }
        return false;
    }

    ///////////////////////////////////////////////////////////////////
    @Override
    public void updateMove(int col, Piece move) {
        pieces[col][findNextAvailableSpot(col)] = move;
    }

    //////////////////////////////////////////////////////////////////////
    @Override
    public void updateMove(int col, int row, Piece move) {
        pieces[col][row] = move;
    }

    @Override
    public Piece[][] getPiece() {
        return pieces;
    }
//////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////findwinner////////////////////////////////////////

    public Winner findWinner() {
        int humanPlayerCount = 0, aiPlayerCount = 0;


        for (int i = 0; i < NUM_OF_COLS; i++) {
            humanPlayerCount = 0;
            aiPlayerCount = 0;

            for (int j = 0; j < NUM_OF_ROWS; j++) {
                if (pieces[i][j] == (Piece.BLUE)) {
                    humanPlayerCount++;
                    aiPlayerCount = 0;
                } else if (pieces[i][j] == (Piece.GREEN)) {
                    aiPlayerCount++;
                    humanPlayerCount = 0;
                } else {
                    humanPlayerCount = 0;
                    aiPlayerCount = 0;
                }
                if (humanPlayerCount == 4) {
                    return new Winner(Piece.BLUE, i, (j - 3), i, j);
                } else if (aiPlayerCount == 4) {
                    return new Winner(Piece.GREEN, i, (j - 3), i, j);
                }
            }
        }
        for (int i = 0; i < NUM_OF_ROWS; i++) {
            humanPlayerCount = 0;
            aiPlayerCount = 0;

            for (int j = 0; j < NUM_OF_COLS; j++) {
                if (pieces[j][i] == (Piece.BLUE)) {
                    humanPlayerCount++;
                    aiPlayerCount = 0;
                } else if (pieces[j][i] == (Piece.GREEN)) {
                    aiPlayerCount++;
                    humanPlayerCount = 0;
                } else {
                    humanPlayerCount = 0;
                    aiPlayerCount = 0;
                }
                if (humanPlayerCount == 4) {
                    return new Winner(Piece.BLUE, (j - 3), i, j, i);
                } else if (aiPlayerCount == 4) {
                    return new Winner(Piece.GREEN, (j - 3), i, j, i);
                }
            }
        }
        return new Winner(Piece.EMPTY);
    }

}