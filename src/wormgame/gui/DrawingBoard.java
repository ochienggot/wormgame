/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wormgame.gui;

import javax.swing.JPanel;
import java.awt.*;
import java.util.List;

import wormgame.game.WormGame;
import wormgame.gui.Updatable;
import wormgame.domain.Apple;
import wormgame.domain.Worm;
import wormgame.domain.Piece;

/**
 *
 * @author ngot
 */

public class DrawingBoard extends JPanel implements Updatable{
    private WormGame wormGame;
    private int pieceLength;

    public DrawingBoard(WormGame wormGame, int pieceLength) {
	this.wormGame = wormGame;
	this.pieceLength = pieceLength; // equal length and height
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        List<Piece> wormPieces = wormGame.getWorm().getPieces();
        for (Piece p : wormPieces) {
            int x = p.getX();
            int y = p.getY();
            g.fill3DRect(x*pieceLength, y*pieceLength, pieceLength, pieceLength, true);
        }

        g.setColor(Color.RED);
        g.fillOval(wormGame.getApple().getX()*pieceLength, wormGame.getApple().getY()*pieceLength, pieceLength, pieceLength);
    }

    @Override
    public void update() {
	super.repaint();
    }
}
