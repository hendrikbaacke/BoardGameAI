package AI.WeightOptimisation;

import AI.ReadMatrix;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.font.*;

public class Plotting extends JPanel {
    int[] data1 = new int[EvolutionaryAlgo.initialGenerationSize];
    int[] data2 = new int[EvolutionaryAlgo.initialGenerationSize];
    int[] data3 = new int[EvolutionaryAlgo.initialGenerationSize];
    final int PAD = 20;

    protected void paintComponent(Graphics g) {
        //fill data
        for(int k=0; k<GeneticLoop.Gensize; k++) {
            int[] moves = {0,0,0};
            int[] marbles = {0,0,0};
            for (int i = 1; i < EvolutionaryAlgo.initialGenerationSize; i++) {
                for (int j = 0; j < 3; j++) {
                    int[] result = ReadResult.ReadIn(System.getProperty("user.dir") + ReadMatrix.Slash + "AI" + ReadMatrix.Slash + "Results" + ReadMatrix.Slash + "AIResult" + k + "_" + (i + 1) + "_" + (j + 1) + ".txt");
                    moves[j] += result[0];
                    marbles[j] += result[1];
                }
            }
            for (int j = 0; j < 3; j++) {
                //moves[j] =(int) moves[j]/10;
                //marbles[j] =(int) marbles[j]/10;
                //System.out.println(marbles[j]);

            }
            data1[k] = moves[0];
            data2[k] = marbles[1];
            data3[k] = marbles[2];
        }

        for(int k=0; k<GeneticLoop.Gensize; k++) {
            System.out.println(data2[k]);
        }
/*
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth();
        int h = getHeight();
        // Draw ordinate.
        g2.draw(new Line2D.Double(PAD, PAD, PAD, h-PAD));

        // Draw labels.
        Font font = g2.getFont();
        FontRenderContext frc = g2.getFontRenderContext();
        LineMetrics lm = font.getLineMetrics("0", frc);
        float sh = lm.getAscent() + lm.getDescent();
        // Ordinate label.
        String s = "data";
        float sy = PAD + ((h - 2*PAD) - s.length()*sh)/2 + lm.getAscent();
        for(int i = 0; i < s.length(); i++) {
            String letter = String.valueOf(s.charAt(i));
            float sw = (float)font.getStringBounds(letter, frc).getWidth();
            float sx = (PAD - sw)/2;
            g2.drawString(letter, sx, sy);
            sy += sh;
        }

        // Draw lines.
        double xInc = (double)(w - 2*PAD)/(data1.length-1);
        double scale = (double)(h - 2*PAD)/(getMax(data2)-getMin(data1));

        Point2D.Double origin = new Point2D.Double(); // Axes origin.
        Point2D.Double offset = new Point2D.Double(); // Locate data.
        origin.y = PAD - scale*getMin(data1);
        offset.y = origin.y;

        // Draw abcissa.
        g2.draw(new Line2D.Double(PAD, origin.y, w-PAD, origin.y));

        g2.setPaint(Color.red);
        for(int i = 0; i < data1.length-1; i++) {
            double x1 = PAD + i*xInc;
            double y1 = offset.y + scale*data1[i];
            double x2 = PAD + (i+1)*xInc;
            double y2 = offset.y + scale*data1[i+1];
            g2.draw(new Line2D.Double(x1, y1, x2, y2));
        }

        g2.setPaint(Color.green.darker());
        for(int i = 0; i < data2.length-1; i++) {
            double x1 = PAD + i*xInc;
            double y1 = offset.y + scale*data2[i];
            double x2 = PAD + (i+1)*xInc;
            double y2 = offset.y + scale*data2[i+1];
            g2.draw(new Line2D.Double(x1, y1, x2, y2));
        }
        g2.setPaint(Color.blue.darker());
        for(int i = 0; i < data3.length-1; i++) {
            double x1 = PAD + i*xInc;
            double y1 = offset.y + scale*data3[i];
            double x2 = PAD + (i+1)*xInc;
            double y2 = offset.y + scale*data3[i+1];
            g2.draw(new Line2D.Double(x1, y1, x2, y2));
        }

        // Mark data points.
        g2.setPaint(Color.red);
        for(int i = 0; i < data1.length; i++) {
            double x = PAD + i*xInc;
            double y = offset.y + scale*data1[i];
            g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
        }
        g2.setPaint(Color.green.darker());
        for(int i = 0; i < data2.length; i++) {
            double x = PAD + i*xInc;
            double y = offset.y + scale*data2[i];
            g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
        }
        g2.setPaint(Color.blue.darker());
        for(int i = 0; i < data3.length; i++) {
            double x = PAD + i*xInc;
            double y = offset.y + scale*data3[i];
            g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
        }

 */
    }

    private int getMax(int[] data) {
        int max = -Integer.MAX_VALUE;
        for(int i = 0; i < data.length; i++) {
            if(data[i] > max)
                max = data[i];
        }
        return max;
    }
    private int getMin(int[] data) {
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < data.length; i++) {
            if(data[i] < min)
                min = data[i];
        }
        return min;
    }

    public static void main() {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new Plotting());
        f.setSize(400,400);
        f.setLocation(200,200);
        f.setVisible(true);
    }
}