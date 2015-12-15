/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wormgame.domain;

/**
 *
 * @author ngot
 */
public class Piece {

    private int x;
    private int y;

    public Piece(int x, int y) {
	this.x = x;
	this.y = y;
    }

    public int getX() {
	return x;
    }

    public int getY() {
	return y;
    }

    /**
     * returns true if the object has the same coordinates as the Piece instance received
     * as parameter
     * @param piece: Piece object
     * @return true/false
     */
    public boolean runsInto(Piece piece) {
	return (x == piece.getX()) && (y == piece.getY());
    }

    @Override
    public String toString() {
	return "(" + x + "," + y + ")";
    }

    /*
    @Override
    public boolean equals(Object obj) {
	if (obj instanceof Piece) {
	    Piece other = (Piece) obj;
	    return this.x == other.getX() && this.y == other.getY();
	}
	return false;
    }

    @Override
    public int hashCode() {
	int result = 17;
	result = 31 * result + result * x;
	result = 31 * result + result * y;

	return result;
    }
    */
}
