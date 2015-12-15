/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wormgame.domain;

import java.util.List;
import java.util.ArrayList;

import wormgame.Direction;

/**
 *
 * @author ngot
 */
public class Worm {

    private Direction direction;
    private List<Piece> wormPieces;
    private boolean grow;

    public Worm(int originalX, int originalY, Direction originalDirection) {
        direction = originalDirection;
        wormPieces = new ArrayList<Piece>();
        wormPieces.add(new Piece(originalX, originalY));
        grow = false;
    }

    public Direction getDirection() {
	return direction;
    }

    public void setDirection(Direction dir) {
	direction = dir;
    }

    public int getLength() {
        return wormPieces.size();
    }

    public List<Piece> getPieces() {
	return wormPieces;
    }

	/**
	 * Helper method to add a piece to the head of the worm's Piece List
	 */
    public void addPiece() {
        Piece headPiece = wormPieces.get(wormPieces.size() - 1);

        if (direction == Direction.DOWN) {
            int y = headPiece.getY() + 1;
            wormPieces.add(new Piece(headPiece.getX(), y));

        } else if (direction == Direction.UP) {
            int y = headPiece.getY() - 1;
            wormPieces.add(new Piece(headPiece.getX(), y));

        } else if (direction == Direction.LEFT) {
            int x = headPiece.getX() - 1;
            wormPieces.add(new Piece(x, headPiece.getY()));

        } else if (direction == Direction.RIGHT) {
            int x = headPiece.getX() + 1;
            wormPieces.add(new Piece(x, headPiece.getY()));
        }
    }

	/**
	 * moves the Worm one piece forward
	 */
    public void move() {
        if (getLength() < 3) {
            addPiece();
        }
        else {
            if (grow) {
                addPiece();
                grow = false;
            } else {
                addPiece();
                wormPieces.remove(0);
            }
        }
    }

    /**
     * grows the worm by one piece. the worm grows together with the following move
     * method call; after the first move method call, the worm does not grow any more.
     */
    public void grow() {
        if (getLength() >= 3) {
            grow = true;
        }
    }

    /**
     * checks whether the worm runs into the parameter Piece
     * @param piece
     * @return true if worm runs into Piece, false otherwise
     */
    public boolean runsInto(Piece piece) {
        for (Piece p : wormPieces) {
            if (p.runsInto(piece)) {
            return true;

            }
        }
        return false;
    }

    /**
     * checks whether the worm runs into itself
     * @return true if any othe worm's Pieces runs into another piece, and false otherwise
     */
    public boolean runsIntoItself() {
        for (Piece p : wormPieces) {
            for (Piece p2 : wormPieces) {
            // Object equality desired
                if (!p.equals(p2) && p.runsInto(p2)) {
                    return true;
                }
            }
        }

	return false;
    }
}
