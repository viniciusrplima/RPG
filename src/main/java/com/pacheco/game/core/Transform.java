package com.pacheco.game.core;

import javafx.scene.canvas.GraphicsContext;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Transform {

    private double matrix[][];

    public Transform() {
        this.matrix = identity();
    }

    public Transform(Transform transform) {
        this();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.matrix[i][j] = transform.matrix[i][j];
            }
        }
    }

    public void apply(GraphicsContext gc) {
        gc.setTransform(matrix[0][0], matrix[1][0], matrix[0][1],
                matrix[1][1], matrix[0][2], matrix[1][2]);
    }

    public Transform translate(Position position) {
        return translate(position.x, position.y);
    }

    public Transform translate(double x, double y) {
        matrix = multiply(matrix, translation(x, y));
        return this;
    }

    public Transform scale(double x, double y) {
        matrix = multiply(matrix, scaling(x, y));
        return this;
    }

    public Transform scale(double scale) {
        return scale(scale, scale);
    }

    public Transform rotate(double theta) {
        matrix = multiply(matrix, rotation(theta));
        return this;
    }

    public Transform multiply(Transform transform) {
        matrix = multiply(matrix, transform.matrix);
        return this;
    }

    private static double[][] multiply(double[][] m1, double[][] m2) {
        double[][] m = new double[3][3];

        m[0][0] = m1[0][0]*m2[0][0] + m1[0][1]*m2[1][0] + m1[0][2]*m2[2][0];
        m[0][1] = m1[0][0]*m2[0][1] + m1[0][1]*m2[1][1] + m1[0][2]*m2[2][1];
        m[0][2] = m1[0][0]*m2[0][2] + m1[0][1]*m2[1][2] + m1[0][2]*m2[2][2];

        m[1][0] = m1[1][0]*m2[0][0] + m1[1][1]*m2[1][0] + m1[1][2]*m2[2][0];
        m[1][1] = m1[1][0]*m2[0][1] + m1[1][1]*m2[1][1] + m1[1][2]*m2[2][1];
        m[1][2] = m1[1][0]*m2[0][2] + m1[1][1]*m2[1][2] + m1[1][2]*m2[2][2];

        m[2][0] = m1[2][0]*m2[0][0] + m1[2][1]*m2[1][0] + m1[2][2]*m2[2][0];
        m[2][1] = m1[2][0]*m2[0][1] + m1[2][1]*m2[1][1] + m1[2][2]*m2[2][1];
        m[2][2] = m1[2][0]*m2[0][2] + m1[2][1]*m2[1][2] + m1[2][2]*m2[2][2];

        return m;
    }

    private static double[][] identity() {
        return new double[][] {
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
    }

    private static double[][] translation(double x, double y) {
        return new double[][] {
                {1, 0, x},
                {0, 1, y},
                {0, 0, 1}
        };
    }

    private static double[][] scaling(double x, double y) {
        return new double[][] {
                {x, 0, 0},
                {0, y, 0},
                {0, 0, 1}
        };
    }

    private static double[][] rotation(double t) {
        return new double[][] {
                {cos(t), -sin(t), 0},
                {sin(t),  cos(t), 0},
                {      0,      0, 1}
        };
    }
}
