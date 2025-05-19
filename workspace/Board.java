
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;

//You will be implmenting a part of a function and a whole function in this document. Please follow the directions for the 
//suggested order of completion that should make testing easier.
@SuppressWarnings("serial")
public class Board extends JPanel implements MouseListener, MouseMotionListener {
    // Resource location constants for piece images
    private static final String RESOURCES_WBISHOP_PNG = "wbishop.png";
    private static final String RESOURCES_BBISHOP_PNG = "bbishop.png";
    private static final String RESOURCES_WKNIGHT_PNG = "wknight.png";
    private static final String RESOURCES_BKNIGHT_PNG = "bknight.png";
    private static final String RESOURCES_WROOK_PNG = "wrook.png";
    private static final String RESOURCES_BROOK_PNG = "brook.png";
    private static final String RESOURCES_WKING_PNG = "wking.png";
    private static final String RESOURCES_BKING_PNG = "bking.png";
    private static final String RESOURCES_BQUEEN_PNG = "bqueen.png";
    private static final String RESOURCES_WQUEEN_PNG = "wqueen.png";
    private static final String RESOURCES_WPAWN_PNG = "wpawn.png";
    private static final String RESOURCES_BPAWN_PNG = "bpawn.png";
    private static final String RESOURCES_bbarbarian_PNG = "BBarbarian.png";
    private static final String RESOURCES_wbarbarian_PNG = "WBarbarian.png";
    private static final String RESOURCES_wpope_PNG = "wpope.png";
    private static final String RESOURCES_bpope_PNG = "bpope.png";
    private static final String RESOURCES_blackrocket_PNG = "blackrocket.png";
    private static final String RESOURCES_whiterocket_PNG = "whiterocket.png";
    private static final String RESOURCES_wantipawn_PNG = "AntiPawnWhite.png";
    private static final String RESOURCES_bantipawn_PNG = "AntiPawnBlack.png";
    private static final String RESOURCES_wmonk_PNG = "wmonk.png";
    private static final String RESOURCES_bmonk_PNG = "bmonk.png";
    private static final String RESOURCES_wjester_PNG = "wjester.png";
    private static final String RESOURCES_bjester_PNG = "bjester.png";
    
    // Logical and graphical representations of board
    private final Square[][] board;
    private final GameWindow g;

    // contains true if it's white's turn.
    private boolean whiteTurn;

    // if the player is currently dragging a piece this variable contains it.
    private Piece currPiece;
    private Square fromMoveSquare;

    // used to keep track of the x/y coordinates of the mouse.
    private int currX;
    private int currY;

    public Board(GameWindow g) {
        this.g = g;
        board = new Square[8][8];
        setLayout(new GridLayout(8, 8, 0, 0));

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        // TO BE IMPLEMENTED FIRST

        // for (.....)
        // populate the board with squares here. Note that the board is composed of 64
        // squares alternating from
        // white to black.
        boolean isWhite = true;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Square(this, isWhite, i, j);
                this.add(board[i][j]);
                isWhite = !isWhite;
            }
            isWhite = !isWhite;
        }

        initializePieces();

        this.setPreferredSize(new Dimension(400, 400));
        this.setMaximumSize(new Dimension(400, 400));
        this.setMinimumSize(this.getPreferredSize());
        this.setSize(new Dimension(400, 400));

        whiteTurn = true;

    }

    // set up the board such that the black pieces are on one side and the white
    // pieces are on the other.
    // since we only have one kind of piece for now you need only set the same
    // number of pieces on either side.
    // it's up to you how you wish to arrange your pieces.
    private void initializePieces() {
        // KINGS
        board[0][4].put(new KingButLessCode(true, RESOURCES_WKING_PNG));
        board[7][4].put(new KingButLessCode(false, RESOURCES_BKING_PNG));
        // QUEENS
        board[0][3].put(new WeepingQueen(true, RESOURCES_WQUEEN_PNG));
        board[7][3].put(new WeepingQueen(false, RESOURCES_BQUEEN_PNG));
        // "PAWNS"
            board[0][0].put(new Jester(false, RESOURCES_wjester_PNG));
            board[1][1].put(new Jester(false, RESOURCES_wjester_PNG));
            board[1][2].put(new Jester(false, RESOURCES_wjester_PNG));
            board[1][3].put(new Jester(true, RESOURCES_wjester_PNG));
            board[1][4].put(new Jester(false, RESOURCES_wjester_PNG));
            board[1][5].put(new Jester(true, RESOURCES_wjester_PNG));
            board[1][6].put(new Jester(false, RESOURCES_wjester_PNG));
            board[1][7].put(new Jester(true, RESOURCES_wjester_PNG));
            board[6][0].put(new Jester(false, RESOURCES_bjester_PNG));
            board[6][1].put(new Jester(false, RESOURCES_bjester_PNG));
            board[6][2].put(new Jester(false, RESOURCES_bjester_PNG));
            board[6][3].put(new Jester(false, RESOURCES_bjester_PNG));
            board[6][4].put(new Jester(false, RESOURCES_bjester_PNG));
            board[6][5].put(new Jester(false, RESOURCES_bjester_PNG));
            board[6][6].put(new Jester(false, RESOURCES_bjester_PNG));
            board[6][7].put(new Jester(false, RESOURCES_bjester_PNG));
            // "BISHOPS"
        board[0][2].put(new Pope(true, RESOURCES_wpope_PNG));
        board[0][5].put(new Pope(true, RESOURCES_wpope_PNG));
        board[7][2].put(new Pope(false, RESOURCES_bpope_PNG));
        board[7][5].put(new Pope(false, RESOURCES_bpope_PNG));
        // "KNIGHTS"
        board[7][1].put(new Barbarian(false, RESOURCES_bbarbarian_PNG));
        board[7][6].put(new Barbarian(false, RESOURCES_bbarbarian_PNG));
        board[0][6].put(new Barbarian(true, RESOURCES_wbarbarian_PNG));
        board[0][1].put(new Barbarian(true, RESOURCES_wbarbarian_PNG));
        // "ROOKS"
         board[7][0].put(new RookRocket(false, RESOURCES_blackrocket_PNG));
         board[7][7].put(new RookRocket(false, RESOURCES_blackrocket_PNG));
         board[0][0].put(new RookRocket(true, RESOURCES_whiterocket_PNG));
         board[0][7].put(new RookRocket(true, RESOURCES_whiterocket_PNG));
    }

    public Square[][] getSquareArray() {
        return this.board;
    }

    public boolean getTurn() {
        return whiteTurn;
    }

    public void setCurrPiece(Piece p) {
        this.currPiece = p;
    }

    public Piece getCurrPiece() {
        return this.currPiece;
    }

    @Override
    public void paintComponent(Graphics g) {

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Square sq = board[x][y];
                if (sq == fromMoveSquare)
                    sq.setBorder(BorderFactory.createLineBorder(Color.GREEN));
                sq.paintComponent(g);

            }
        }
        if (currPiece != null) {
            if ((currPiece.getColor() && whiteTurn)
                    || (!currPiece.getColor() && !whiteTurn)) {
                final Image img = currPiece.getImage();
                g.drawImage(img, currX, currY, null);
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        currX = e.getX();
        currY = e.getY();

        Square sq = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));

        if (sq.isOccupied()) {
            currPiece = sq.getOccupyingPiece();
            fromMoveSquare = sq;
            if (!currPiece.getColor() && whiteTurn)
                return;
            if (currPiece.getColor() && !whiteTurn)
                return;
            sq.setDisplay(false);
        }
        repaint();
    }

    // TO BE IMPLEMENTED!
    // should move the piece to the desired location only if this is a legal move.
    // use the pieces "legal move" function to determine if this move is legal, then
    // complete it by
    // moving the new piece to it's new board location.

    @Override
    public void mouseReleased(MouseEvent e) {

        Square endSquare = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));
        Piece deadSquare = endSquare.getOccupyingPiece(); // to get the endsquare to place it back if isInCheck = true

        if (fromMoveSquare != null && currPiece != null && whiteTurn == currPiece.getColor()) {
            if (currPiece.getLegalMoves(this, fromMoveSquare).contains(endSquare)) {
                if (currPiece instanceof Barbarian) {
                    Barbarian b = (Barbarian) currPiece;
                    if (b.captured) { // 1/3 chance to capture another piece if Barbarian captured a piece
                        int rng = (int) (Math.random() * 13); // Generates a random number between 0 and 12
                        if (endSquare.isOccupied()) {
                            b.captured = true;
                        } else {
                            whiteTurn = !whiteTurn;
                            b.captured = false;
                        }
                        // Captures another piece if barbarian hits the 1/3 chance and lets it go again
                        if (rng <= 12) {
                            fromMoveSquare.removePiece();
                            endSquare.put(b);
                            if (isInCheck(!whiteTurn) && currPiece instanceof KingButLessCode) {
                                fromMoveSquare.put(b);
                                endSquare.removePiece();
                                endSquare.put(deadSquare);
                            }
                            // kills Barbarian if RNG got the 2/3 chance to not capture another piece
                        } else if (endSquare.isOccupied()
                                && endSquare.getOccupyingPiece().getColor() == !b.getColor()) {
                            fromMoveSquare.removePiece();
                            whiteTurn = !whiteTurn;
                        }
                    } else {
                        if (currPiece instanceof Barbarian && endSquare.isOccupied()) { // checks if barbarian captured
                                                                                        // a piece
                            b.captured = true;
                        } else {
                            b.captured = false;
                        }
                        if (currPiece instanceof Barbarian && b.captured) { // if barbarian captured
                                                                            // a piece, it will be
                                                                            // the same player's turn
                            whiteTurn = currPiece.getColor();
                        } else {
                            whiteTurn = !whiteTurn;
                        }
                        endSquare.removePiece();
                        fromMoveSquare.removePiece();
                        endSquare.put(b);
                        if (isInCheck(!whiteTurn) && currPiece instanceof KingButLessCode) {
                            fromMoveSquare.put(currPiece);
                            endSquare.removePiece();
                            endSquare.put(deadSquare);
                            whiteTurn = currPiece.getColor();
                        }
                    }
                } else {

                    fromMoveSquare.removePiece();
                    endSquare.put(currPiece);
                    whiteTurn = !whiteTurn;
                    if (isInCheck(!whiteTurn) && currPiece instanceof KingButLessCode) {
                        fromMoveSquare.put(currPiece);
                        endSquare.removePiece();
                        endSquare.put(deadSquare);
                        whiteTurn = currPiece.getColor();
                    }
                }

            }
        }
        for (Square[] row : board) {
            for (Square s : row) {
                s.setBorder(null);
            }
        }
        fromMoveSquare.setDisplay(true);
        repaint();
        currPiece = null;
        return;
    }

    public boolean isInCheck(boolean kingColor) {
        Square kingSquare = null;
        outer: for (Square[] row : board) {
            for (Square square : row) {
                if (square.isOccupied() && square.getOccupyingPiece() instanceof KingButLessCode) {
                    Piece king = square.getOccupyingPiece();
                    if (king.getColor() == kingColor) {
                        kingSquare = square;
                        break outer;
                    }
                }

            }
        }
        for (Square[] row2 : board) {
            for (Square square2 : row2) {
                if (square2.isOccupied() && square2.getOccupyingPiece().getColor() != kingColor) {
                    Piece check = square2.getOccupyingPiece();
                    ArrayList<Square> controlledSquares = check.getLegalMoves(this, square2);
                    if (controlledSquares.contains(kingSquare)) {
                        return true;
                    }

                }
            }
        }
        return false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        currX = e.getX() - 24;
        currY = e.getY() - 24;

        if (currPiece != null && whiteTurn == currPiece.getColor()) {
            for (Square s : currPiece.getLegalMoves(this, fromMoveSquare)) {
                s.setBorder(BorderFactory.createLineBorder(Color.CYAN));

            }
        }
        repaint();

    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}