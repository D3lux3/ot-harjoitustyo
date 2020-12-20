package rottasimulaattori.game.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


public class MapHandler {


    /**
     * Handles the loaded map file saving
     * @param level Array of strings
     */
    private void handleLevelChange(String[] level) {
        for (int y = 0; y < level.length; y++) {
            for (int x = 0; x < level[y].length(); x++) {
                Level.setValue(x, y, level[y].charAt(x));
            }
        }
    }

    /**
     * Handles file loading
     * @param file File
     * @throws Exception Exception
     */
    public void loadMap(File file) throws Exception {
        String level[] = new String[Level.level.length];
        try {
            Scanner fileReader = new Scanner(file);
            int y = 0;
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                for (int i = 0; i < line.length(); i++) {
                    if (!validMapCheck(line.charAt(i))) {
                        throw new Exception("Map has illegal characters");
                    }
                }
                level[y] = line;
                y++;
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        handleLevelChange(level);
    }

    /**
     * Checks if the file contains any illegal characters.
     * @param c c
     * @return Boolean value
     */
    private boolean validMapCheck(char c) {
        switch (c) {
            case '0':
            case '1':
            case 'C':
            case 'G':
                return true;
            default:
                return false;
        }
    }

    /**
     * Handles saving of map to an file.
     * @param file File
     */
    public void saveLevel(File file) {
        try {
            PrintWriter pw = new PrintWriter(file);
            for (String line : Level.level) {
                pw.println(line);
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
