
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
    private static final String RESOURCES_barbarian_PNG = "BBarbarian.png";
    private static final String RESOURCES_wbarbarian_PNG = "WBarbarian.png";

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

        for (int i = 0; i < 8; i++) {
            board[3][i].put(new Piece(true, RESOURCES_WPAWN_PNG)); // White pieces on second last row
            board[0][i].put(new Piece(true, RESOURCES_WKING_PNG)); // White pieces on last row
        }
        for (int i = 0; i < 8; i++) {
            board[4][i].put(new Barbarian(true, RESOURCES_wbarbarian_PNG)); // White pieces on second last row
            board[7][i].put(new Barbarian(false, RESOURCES_barbarian_PNG)); // White pieces on last row
            
        }
        
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
        if (fromMoveSquare != null && whiteTurn == currPiece.getColor()) {
            if (currPiece != null && currPiece.getLegalMoves(this, fromMoveSquare).contains(endSquare) ) {
                if(currPiece instanceof Barbarian){
                    Barbarian b = (Barbarian) currPiece;
                if (b.captured) {
                    int rng = (int) (Math.random() * 13); // Generates a random number between 0 and 12
                    if (endSquare.isOccupied()){
                    b.captured = true;
                    } else {
                    b.captured = false;
                    }

                    if (rng <= 3) {
                        fromMoveSquare.removePiece();
                        endSquare.put(b);


                    } else if (endSquare.isOccupied() && endSquare.getOccupyingPiece().getColor() == !b.getColor()) {
                        fromMoveSquare.removePiece();
                        whiteTurn = !whiteTurn;

                        for (Square[] row : board) {
                            for (Square s : row) {
                                s.setBorder(null);
                            }
                        }
                        fromMoveSquare.setDisplay(true);
                
                        currPiece = null;
                        repaint();

                        return;
                    }
                    
                    if(!b.captured){
                        whiteTurn= !whiteTurn;
                    }
                    
                    
                }

                else {

                    fromMoveSquare.removePiece();
                    if (currPiece instanceof Barbarian &&  endSquare.isOccupied()) { // checks if barbarian
                                                                                          // captured a
                                                                                          // piece
                        b.captured = true;
                    } else {
                        b.captured = false;
                    }
                    if (currPiece instanceof Barbarian &&  b.captured) { // if barbarian captured
                                                                                      // a piece, it will be
                                                                                      // the same player's turn
                        whiteTurn = currPiece.getColor();
                    } else {
                        whiteTurn = !whiteTurn;
                    }
                }
            }
                    fromMoveSquare.removePiece();
                    endSquare.put(currPiece);
        }
            
        }
        for (Square[] row : board) {
            for (Square s : row) {
                s.setBorder(null);
            }
        }
        fromMoveSquare.setDisplay(true);

        currPiece = null;
        repaint();
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