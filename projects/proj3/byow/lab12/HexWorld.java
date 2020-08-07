package byow.lab12;
import org.junit.Test;
import static org.junit.Assert.*;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int SideLength = 3;
    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);

    /**
     * Draw a single hexagon with side length s.
     */
    public static void addHexagon(int s, TETile tile, TETile[][] tiles, int x0, int y0) {
        int width = 3 * s - 2;
        int height = 2 * s;

        for (int y = y0; y < y0 + s; y += 1) {
            for (int x = x0 + s - (y - y0) - 1; x < x0 + (width + s)/2 + (y - y0); x += 1) {
                tiles[x][y] = TETile.colorVariant(tile, 25, 25 ,25 ,new Random());
                tiles[x][2 * y0 + height - y - 1] = TETile.colorVariant(tile, 25, 25 ,25 ,RANDOM);
            }
        }
    }

    /** Picks a RANDOM tile with a 33% change of being
     *  a wall, 33% chance of being a flower, and 33%
     *  chance of being empty space.
     */
    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(5);
        switch (tileNum) {
            case 0: return Tileset.TREE;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.GRASS;
            case 3: return Tileset.SAND;
            case 4: return Tileset.MOUNTAIN;
            default: return Tileset.NOTHING;
        }
    }

    /**
     * Draw all hexagons in the world with side length s.
     */
    public static void drawHexagon(int s, TETile[][] tiles) {
        for (int i = 0; i < 5; i += 1) {
            addHexagon(s, randomTile(), tiles, 4 * s - 2, i * 2 * s);
        }

        for (int i = 0; i < 4; i += 1) {
            addHexagon(s, randomTile(), tiles, 2 * s - 1, i * 2 * s + s);
            addHexagon(s, randomTile(), tiles, 6 * s - 3, i * 2 * s + s);
        }

        for (int i = 0; i < 3; i += 1) {
            addHexagon(s, randomTile(), tiles, 0, (i + 1) * 2 * s);
            addHexagon(s, randomTile(), tiles, 8 * s - 4, (i + 1) * 2 * s);
        }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        int HEIGHT = 10 * SideLength;
        int WIDTH = 11 * SideLength - 6;

        ter.initialize(WIDTH, HEIGHT);

        TETile[][] hexWorld = new TETile[WIDTH][HEIGHT];

        // fills in a block 14 tiles wide by 4 tiles tall
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                hexWorld[x][y] = Tileset.NOTHING;
            }
        }

        //addHexagon(SideLength, Tileset.FLOWER, hexWorld, 3, 3);
        drawHexagon(SideLength, hexWorld);

        // draws the world to the screen
        ter.renderFrame(hexWorld);
    }
}
