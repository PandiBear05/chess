
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

//you will need to implement two functions in this file.
public class Barbarian extends Piece {
  public boolean captured;

  public Barbarian (boolean isWhite, String img_file) {
    super(isWhite, img_file);
    captured = false;
  }

  // TO BE IMPLEMENTED!
  // return a list of every square that is "c" by this piece. A square is
  // controlled
  // if the piece capture into it legally.
  public ArrayList<Square> getControlledSquares(Square[][] board, Square start) {

    ArrayList<Square> controlled = new ArrayList<Square>();
    if (!getColor()) {
      if (start.getCol() + 1 <= 7 && start.getCol() != 7) {
        Square controlling = board[start.getRow()][start.getCol() + 1];
        if (!controlling.isOccupied()
            || (controlling.isOccupied() && controlling.getOccupyingPiece().getColor() != color)) // Right
          controlled.add(controlling);
      }
      if (start.getCol() - 1 <= 7 && start.getCol() != 0) {
        Square controlling = board[start.getRow()][start.getCol() - 1];
        if (!controlling.isOccupied()
            || (controlling.isOccupied() && controlling.getOccupyingPiece().getColor() != color)) // left
          controlled.add(controlling);
      }
      if (start.getCol() + 1 <= 7 && start.getRow() - 1 <= 7 && start.getRow() != 0 && start.getCol() != 7) { // Up left
        Square controlling = board[start.getRow() - 1][start.getCol() - 1];
        if (!controlling.isOccupied()
            || (controlling.isOccupied() && controlling.getOccupyingPiece().getColor() != color))
          controlled.add(controlling);
      }
      if (start.getCol() + 1 <= 7 && start.getRow() - 1 <= 7 && start.getRow() != 0 && start.getCol() != 7) { // Up
                                                                                                              // Right
        Square controlling = board[start.getRow() - 1][start.getCol() + 1];
        if (!controlling.isOccupied()
            || (controlling.isOccupied() && controlling.getOccupyingPiece().getColor() != color))
          controlled.add(controlling);
      }
    }
    if (start.getCol() + 1 <= 7 && start.getRow() - 1 <= 7 && start.getRow() != 0 && start.getCol() != 7) { // Up
      Square controlling = board[start.getRow() - 1][start.getCol()];
      if (!controlling.isOccupied()
          || (controlling.isOccupied() && controlling.getOccupyingPiece().getColor() != color))
        controlled.add(controlling);
    }

    else if (getColor()) {
      if (start.getCol() + 1 <= 7 && start.getCol() != 7) {
        Square controlling = board[start.getRow()][start.getCol() + 1];
        if (!controlling.isOccupied()
            || (controlling.isOccupied() && controlling.getOccupyingPiece().getColor() != color)) // Right
          controlled.add(controlling);
      }
      if (start.getCol() - 1 <= 7 && start.getCol() != 0) {
        Square controlling = board[start.getRow()][start.getCol() - 1];
        if (!controlling.isOccupied()
            || (controlling.isOccupied() && controlling.getOccupyingPiece().getColor() != color)) // left
          controlled.add(controlling);
      }
      if (start.getCol() + 1 <= 7 && start.getRow() + 1 <= 7 && start.getRow() != 0 && start.getCol() != 7) { // down
                                                                                                              // left
        Square controlling = board[start.getRow() + 1][start.getCol() - 1];
        if (!controlling.isOccupied()
            || (controlling.isOccupied() && controlling.getOccupyingPiece().getColor() != color))
          controlled.add(controlling);
      }
      if (start.getCol() + 1 <= 7 && start.getRow() + 1 <= 7 && start.getRow() != 0 && start.getCol() != 7) { // down
                                                                                                              // Right
        Square controlling = board[start.getRow() + 1][start.getCol() + 1];
        if (!controlling.isOccupied()
            || (controlling.isOccupied() && controlling.getOccupyingPiece().getColor() != color))
          controlled.add(controlling);
      }
    }
    if (start.getCol() + 1 <= 7 && start.getRow() + 1 <= 7 && start.getRow() != 0 && start.getCol() != 7) { // down
      Square controlling = board[start.getRow() + 1][start.getCol()];
      if (!controlling.isOccupied()
          || (controlling.isOccupied() && controlling.getOccupyingPiece().getColor() != color))
        controlled.add(controlling);
    }
    return controlled;
  }

  // TO BE IMPLEMENTED!
  // implement the move function here
  // it's up to you how the piece moves, but at the very least the rules should be
  // logical and it should never move off the board!
  // returns an arraylist of squares which are legal to move to
  // please note that your piece must have some sort of logic. Just being able to
  // move to every square on the board is not
  // going to score any points.

  // removePiece put setDisplay getCol getRow isOccupied getOccupyingPiece
  // getColor

  public ArrayList<Square> getLegalMoves(Board b, Square start) {

    ArrayList<Square> moves = new ArrayList<Square>();
    if (!getColor()) {
      if (start.getCol() + 1 <= 7 && start.getCol() != 7) {
        if (!b.getSquareArray()[start.getRow()][start.getCol() + 1].isOccupied() ||
            b.getSquareArray()[start.getRow()][start.getCol() + 1].getOccupyingPiece().getColor() != color) { // move to
                                                                                                              // right
          moves.add(b.getSquareArray()[start.getRow()][start.getCol() + 1]);
        }
      }
      if (start.getCol() - 1 <= 7 && start.getCol() != 0) { // move to left
        if (!b.getSquareArray()[start.getRow()][start.getCol() - 1].isOccupied() ||
            b.getSquareArray()[start.getRow()][start.getCol() - 1].getOccupyingPiece().getColor() != color) {
          moves.add(b.getSquareArray()[start.getRow()][start.getCol() - 1]);
        }
      }
      if (start.getCol() + 1 <= 7 && start.getRow() - 1 <= 7 && start.getRow() != 0 && start.getCol() != 7) { // up
                                                                                                              // right
        if (!b.getSquareArray()[start.getRow() - 1][start.getCol() + 1].isOccupied() ||
            b.getSquareArray()[start.getRow() - 1][start.getCol() + 1].getOccupyingPiece().getColor() != color) {
          moves.add(b.getSquareArray()[start.getRow() - 1][start.getCol() + 1]);
        }
      }
      if (start.getCol() - 1 <= 7 && start.getRow() - 1 <= 7 && start.getRow() != 0 && start.getCol() != 0) { // up Left
        if (!b.getSquareArray()[start.getRow() - 1][start.getCol() - 1].isOccupied() ||
            b.getSquareArray()[start.getRow() - 1][start.getCol() - 1].getOccupyingPiece().getColor() != color) {
          moves.add(b.getSquareArray()[start.getRow() - 1][start.getCol() - 1]);
        }
      }
      if (start.getCol() <= 7 && start.getRow() - 1 <= 7 && start.getRow() != 0) { // up
        if (!b.getSquareArray()[start.getRow() - 1][start.getCol()].isOccupied() ||
            b.getSquareArray()[start.getRow() - 1][start.getCol()].getOccupyingPiece().getColor() != color) {
          moves.add(b.getSquareArray()[start.getRow() - 1][start.getCol()]);
        }
      }
    }

    if (getColor()) {

      if (start.getCol() + 1 <= 7 && start.getCol() != 7) { // move to right
        if (!b.getSquareArray()[start.getRow()][start.getCol() + 1].isOccupied() ||
            b.getSquareArray()[start.getRow()][start.getCol() + 1].getOccupyingPiece().getColor() != color) {
          moves.add(b.getSquareArray()[start.getRow()][start.getCol() + 1]);
        }
      }

      if (start.getCol() - 1 <= 7 && start.getCol() != 0) { // move to left
        if (!b.getSquareArray()[start.getRow()][start.getCol() - 1].isOccupied() ||
            b.getSquareArray()[start.getRow()][start.getCol() - 1].getOccupyingPiece().getColor() != color) {
          moves.add(b.getSquareArray()[start.getRow()][start.getCol() - 1]);
        }
      }

      if (start.getCol() + 1 <= 7 && start.getRow() + 1 <= 7 && start.getCol() != 7 && start.getRow() != 7) { // down
                                                                                                              // right
        if (!b.getSquareArray()[start.getRow() + 1][start.getCol() + 1].isOccupied() ||
            b.getSquareArray()[start.getRow() + 1][start.getCol() + 1].getOccupyingPiece().getColor() != color) {
          moves.add(b.getSquareArray()[start.getRow() + 1][start.getCol() + 1]);
        }
      }

      if (start.getCol() - 1 <= 7 && start.getRow() + 1 <= 7 && start.getCol() != 0 && start.getRow() != 7) { // down
                                                                                                              // Left
        if (!b.getSquareArray()[start.getRow() + 1][start.getCol() - 1].isOccupied() ||
            b.getSquareArray()[start.getRow() + 1][start.getCol() - 1].getOccupyingPiece().getColor() != color) {
          moves.add(b.getSquareArray()[start.getRow() + 1][start.getCol() - 1]);
        }
      }

      if (start.getCol() <= 7 && start.getRow() + 1 <= 7 && start.getRow() != 7) { // up
        if (!b.getSquareArray()[start.getRow() + 1][start.getCol()].isOccupied() ||
            b.getSquareArray()[start.getRow() + 1][start.getCol()].getOccupyingPiece().getColor() != color) {
          moves.add(b.getSquareArray()[start.getRow() + 1][start.getCol()]);
        }
      }
    }
    if (captured) {
      moves.add(start);
    }
    return moves;
  }
}
