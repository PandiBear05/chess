//
//Anna Matschiner
//
//The Weeping Queen
//
//Acts like a regular queen, but can must keep going until it hits a border or another piece, if the piece that is hit belongs to the other team it can be taken
//

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

//you will need to implement two functions in this file.
public class WeepingQueen extends Piece {
    
    public WeepingQueen(boolean color, String img_file) {
        super(color, img_file);
    }
    
    
    
    //Given board contains 2D array of valid squares with no null values, start is not null
    //Returns arrayList of all the squares the current piece controls(protects).
    public ArrayList<Square> getControlledSquares(Square[][] board, Square start) {
    	
    ArrayList<Square> controlled = new ArrayList<Square>();
	
	int k=start.getCol();

	Piece temp = null;
	
	int l=start.getRow();

	
	while (k<7&&temp==null) {
		k++;
		if(k!=start.getCol()) {
			temp = board[l][k].getOccupyingPiece();
		}
	}
	
	if (board[l][k].getOccupyingPiece()==null){
		controlled.add(board[l][k]);
	} else if (k>=start.getCol()+1) {
    	controlled.add(board[l][k-1]); 
 	}
	
	if ((board[l][k].getOccupyingPiece()!=null)) { 
			controlled.add(board[l][k]);
	}
	

	

	//Check to the left           
	k=start.getCol();

	temp = null;
	
	l=start.getRow();

	
	while (k>0&&temp==null) {
		k--;
		if(k!=start.getCol()) {
			temp = board[l][k].getOccupyingPiece();
		}
	}
	
	if (board[l][k].getOccupyingPiece()==null){
		controlled.add(board[l][k]);
	} else if (k<=start.getCol()-1) {
    	controlled.add(board[l][k+1]); 
 	}
	
	if ((board[l][k].getOccupyingPiece()!=null) 
		 ) { 
			controlled.add(board[l][k]);
	}
	
	//Check to the up
	k=start.getRow();
	
	temp = null;
	
	l=start.getCol();
	
	while (k>0&&temp==null) {
		k--;
		if(k!=start.getRow()) {
			temp = board[k][l].getOccupyingPiece();
		}
	}
	
	if (board[k][l].getOccupyingPiece()==null){
		controlled.add(board[k][l]);
	} else if (k<=start.getRow()-1) {
    	controlled.add(board[k+1][l]); 
 	}
	
	if ((board[k][l].getOccupyingPiece()!=null)) { 
			controlled.add(board[k][l]);
	}
	
	
	//Check to the down
	k=start.getRow();
	
	temp = null;
	
	l=start.getCol();
	
	while (k<7&&temp==null) {
		k++;
		if(k!=start.getRow()) {
			temp = board[k][l].getOccupyingPiece();
		}
	}
	
	if (board[k][l].getOccupyingPiece()==null){
		controlled.add(board[k][l]);
	} else if (k>=start.getRow()+1) {
    	controlled.add(board[k-1][l]); 
 	}
	
	if ((board[k][l].getOccupyingPiece()!=null)) { 
			controlled.add(board[k][l]);
	}
	
	//Check down/right
	k=start.getRow();
	
	temp = null;
	
	l=start.getCol();
	
	while (k<7&&l<7&&temp==null) {
		k++;
		l++;
		if(k!=start.getRow()&&l!=start.getCol()) {
			temp = board[k][l].getOccupyingPiece();
		}
	}
	
	if (board[k][l].getOccupyingPiece()==null){
		controlled.add(board[k][l]);
	} else if (k>=start.getRow()+1&&l>=start.getCol()+1) {
    	controlled.add(board[k-1][l-1]); 
 	}
	
	if ((board[k][l].getOccupyingPiece()!=null)) { 
			controlled.add(board[k][l]);
	}
	
	//Check up/left
	k=start.getRow();
	
	temp = null;
	
	l=start.getCol();
	
	while (k>0&&l>0&&temp==null) {
		k--;
		l--;
		if(k!=start.getRow()&&l!=start.getCol()) {
			temp = board[k][l].getOccupyingPiece();
		}
	}
	
	if (board[k][l].getOccupyingPiece()==null){
		controlled.add(board[k][l]);
	} else if (k<=start.getRow()-1&&l<=start.getCol()-1) {
    	controlled.add(board[k+1][l+1]); 
 	}
	
	if ((board[k][l].getOccupyingPiece()!=null)) { 
			controlled.add(board[k][l]);
	}
	
	//Check down/left
	k=start.getRow();
	
	temp = null;
	
	l=start.getCol();
	
	while (k<7&&l>0&&temp==null) {
		k++;
		l--;
		if(k!=start.getRow()&&l!=start.getCol()) {
			temp = board[k][l].getOccupyingPiece();
		}
	}
	
	if (board[k][l].getOccupyingPiece()==null){
		controlled.add(board[k][l]);
	} else if (k>=start.getRow()+1&&l<=start.getCol()-1) {
    	controlled.add(board[k-1][l+1]); 
 	}
	
	if ((board[k][l].getOccupyingPiece()!=null) 
		 ) { 
			controlled.add(board[k][l]);
	}
	
	//Check up/right
	k=start.getRow();
	
	temp = null;
	
	l=start.getCol();
	
	while (k>0&&l<7&&temp==null) {
		k--;
		l++;
		if(k!=start.getRow()&&l!=start.getCol()) {
			temp = 
					board[k][l].getOccupyingPiece();
		}
	}
	
	if (board[k][l].getOccupyingPiece()==null){
		controlled.add(board[k][l]);
	} else if (k<=start.getRow()-1&&l>=start.getCol()+1) {
    	controlled.add(board[k+1][l-1]); 
 	}
	
	if ((board[k][l].getOccupyingPiece()!=null)) { 
			controlled.add(board[k][l]);
	}
	
	
	//Check for starting place
	int i=0;
	int j=0;
	while (i<8&&j<8&&board[i][i].getOccupyingPiece()==null) {
		controlled.add(board[i][j]); 
		j++;
		i++;
	}
	while(controlled.contains(start)) {
		controlled.remove(start);
	}
	//Check down/left
    return controlled;
    }
    
    
    //Given board has no null values, start is not null
    //Returns arrayList of all the squares the current piece can legally move to.
    @Override
    public ArrayList<Square> getLegalMoves(Board b, Square start){
    	
    	
    	ArrayList<Square> moves = new ArrayList<Square>();
    	
    	//if(start.getCol()+1<8) {
    	//	 moves.add(b.getSquareArray()[start.getRow()][start.getCol()+1]); 
    	//}
    	
    	//Check to the right
    	
    	int k=start.getCol();

    	Piece temp = null;
    	
    	int l=start.getRow();

    	
    	while (k<7&&temp==null) {
    		k++;
    		if(k!=start.getCol()) {
    			temp = b.getSquareArray()[l][k].getOccupyingPiece();
    		}
    	}
    	
    	if (b.getSquareArray()[l][k].getOccupyingPiece()==null){
    		moves.add(b.getSquareArray()[l][k]);
    	} else if (k>=start.getCol()+1) {
        	moves.add(b.getSquareArray()[l][k-1]); 
     	}
    	
    	if ((b.getSquareArray()[l][k].getOccupyingPiece()!=null) 
    		&&b.getSquareArray()[l][k].getOccupyingPiece().getColor()!=start.getOccupyingPiece().getColor()) { 
    			moves.add(b.getSquareArray()[l][k]);
    	}
    	
  
    	

    	//Check to the left           
    	k=start.getCol();

    	temp = null;
    	
    	l=start.getRow();

    	
    	while (k>0&&temp==null) {
    		k--;
    		if(k!=start.getCol()) {
    			temp = b.getSquareArray()[l][k].getOccupyingPiece();
    		}
    	}
    	
    	if (b.getSquareArray()[l][k].getOccupyingPiece()==null){
    		moves.add(b.getSquareArray()[l][k]);
    	} else if (k<=start.getCol()-1) {
        	moves.add(b.getSquareArray()[l][k+1]); 
     	}
    	
    	if ((b.getSquareArray()[l][k].getOccupyingPiece()!=null) 
    		&&b.getSquareArray()[l][k].getOccupyingPiece().getColor()!=start.getOccupyingPiece().getColor()) { 
    			moves.add(b.getSquareArray()[l][k]);
    	}
    	
    	//Check to the up
    	k=start.getRow();
    	
    	temp = null;
    	
    	l=start.getCol();
    	
    	while (k>0&&temp==null) {
    		k--;
    		if(k!=start.getRow()) {
    			temp = b.getSquareArray()[k][l].getOccupyingPiece();
    		}
    	}
    	
    	if (b.getSquareArray()[k][l].getOccupyingPiece()==null){
    		moves.add(b.getSquareArray()[k][l]);
    	} else if (k<=start.getRow()-1) {
        	moves.add(b.getSquareArray()[k+1][l]); 
     	}
    	
    	if ((b.getSquareArray()[k][l].getOccupyingPiece()!=null) 
    		&&b.getSquareArray()[k][l].getOccupyingPiece().getColor()!=start.getOccupyingPiece().getColor()) { 
    			moves.add(b.getSquareArray()[k][l]);
    	}
    	
    	
    	//Check to the down
    	k=start.getRow();
    	
    	temp = null;
    	
    	l=start.getCol();
    	
    	while (k<7&&temp==null) {
    		k++;
    		if(k!=start.getRow()) {
    			temp = b.getSquareArray()[k][l].getOccupyingPiece();
    		}
    	}
    	
    	if (b.getSquareArray()[k][l].getOccupyingPiece()==null){
    		moves.add(b.getSquareArray()[k][l]);
    	} else if (k>=start.getRow()+1) {
        	moves.add(b.getSquareArray()[k-1][l]); 
     	}
    	
    	if ((b.getSquareArray()[k][l].getOccupyingPiece()!=null) 
    		&&b.getSquareArray()[k][l].getOccupyingPiece().getColor()!=start.getOccupyingPiece().getColor()) { 
    			moves.add(b.getSquareArray()[k][l]);
    	}
    	
    	//Check down/right
    	k=start.getRow();
    	
    	temp = null;
    	
    	l=start.getCol();
    	
    	while (k<7&&l<7&&temp==null) {
    		k++;
    		l++;
    		if(k!=start.getRow()&&l!=start.getCol()) {
    			temp = b.getSquareArray()[k][l].getOccupyingPiece();
    		}
    	}
    	
    	if (b.getSquareArray()[k][l].getOccupyingPiece()==null){
    		moves.add(b.getSquareArray()[k][l]);
    	} else if (k>=start.getRow()+1&&l>=start.getCol()+1) {
        	moves.add(b.getSquareArray()[k-1][l-1]); 
     	}
    	
    	if ((b.getSquareArray()[k][l].getOccupyingPiece()!=null) 
    		&&b.getSquareArray()[k][l].getOccupyingPiece().getColor()!=start.getOccupyingPiece().getColor()) { 
    			moves.add(b.getSquareArray()[k][l]);
    	}
    	
    	//Check up/left
    	k=start.getRow();
    	
    	temp = null;
    	
    	l=start.getCol();
    	
    	while (k>0&&l>0&&temp==null) {
    		k--;
    		l--;
    		if(k!=start.getRow()&&l!=start.getCol()) {
    			temp = b.getSquareArray()[k][l].getOccupyingPiece();
    		}
    	}
    	
    	if (b.getSquareArray()[k][l].getOccupyingPiece()==null){
    		moves.add(b.getSquareArray()[k][l]);
    	} else if (k<=start.getRow()-1&&l<=start.getCol()-1) {
        	moves.add(b.getSquareArray()[k+1][l+1]); 
     	}
    	
    	if ((b.getSquareArray()[k][l].getOccupyingPiece()!=null) 
    		&&b.getSquareArray()[k][l].getOccupyingPiece().getColor()!=start.getOccupyingPiece().getColor()) { 
    			moves.add(b.getSquareArray()[k][l]);
    	}
    	
    	//Check down/left
    	k=start.getRow();
    	
    	temp = null;
    	
    	l=start.getCol();
    	
    	while (k<7&&l>0&&temp==null) {
    		k++;
    		l--;
    		if(k!=start.getRow()&&l!=start.getCol()) {
    			temp = b.getSquareArray()[k][l].getOccupyingPiece();
    		}
    	}
    	
    	if (b.getSquareArray()[k][l].getOccupyingPiece()==null){
    		moves.add(b.getSquareArray()[k][l]);
    	} else if (k>=start.getRow()+1&&l<=start.getCol()-1) {
        	moves.add(b.getSquareArray()[k-1][l+1]); 
     	}
    	
    	if ((b.getSquareArray()[k][l].getOccupyingPiece()!=null) 
    		&&b.getSquareArray()[k][l].getOccupyingPiece().getColor()!=start.getOccupyingPiece().getColor()) { 
    			moves.add(b.getSquareArray()[k][l]);
    	}
    	
    	//Check up/right
    	k=start.getRow();
    	
    	temp = null;
    	
    	l=start.getCol();
    	
    	while (k>0&&l<7&&temp==null) {
    		k--;
    		l++;
    		if(k!=start.getRow()&&l!=start.getCol()) {
    			temp = b.getSquareArray()[k][l].getOccupyingPiece();
    		}
    	}
    	
    	if (b.getSquareArray()[k][l].getOccupyingPiece()==null){
    		moves.add(b.getSquareArray()[k][l]);
    	} else if (k<=start.getRow()-1&&l>=start.getCol()+1) {
        	moves.add(b.getSquareArray()[k+1][l-1]); 
     	}
    	
    	if ((b.getSquareArray()[k][l].getOccupyingPiece()!=null) 
    		&&b.getSquareArray()[k][l].getOccupyingPiece().getColor()!=start.getOccupyingPiece().getColor()) { 
    			moves.add(b.getSquareArray()[k][l]);
    	}
    	
    	
    	//Check for starting place
    	int i=0;
    	int j=0;
    	while (i<8&&j<8&&b.getSquareArray()[i][i].getOccupyingPiece()==null) {
    		moves.add(b.getSquareArray()[i][j]); 
    		j++;
    		i++;
    	}
    	while(moves.contains(start)) {
    		moves.remove(start);
    	}
    	//Check down/left
    	return moves;
    }
    
    @Override
    public String toString() {
	return ("A" + getColor() + " Weeping Queen");
    }
}















