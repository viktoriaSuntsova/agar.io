package models;

import java.awt.Point;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Математические функции, используемые в игре
 */
public class GameMath {
    
    /**
     * Переводит угол из градусов в радианы
     * @param angle угол в градусах
     * @return угол в радианах
     */
    public static double degreesToRadians(int angle) {
        return angle * Math.PI / 180.0;
    }
    
    /**
     * Переводит угол из радианов в градусы
     * @param angle угол в радианах
     * @return угол в градусах
     */
    public static int radiansToDegrees(double angle) {
        return (int) (angle * 180.0 / Math.PI);
    }
    
    /**
     * Возвращает расстояние между двумя точками
     * @param x1 координата х первой точки
     * @param y1 координата y первой точки
     * @param x2 координата х второй точки
     * @param y2 координата y второй точки
     * @return 
     */
    public static double distance(int x1, int y1, int x2, int y2) {
        int deltaX = 0;
        int deltaY = 0;
        if(x1>x2){
            deltaX = x1-x2;
        }
        else{
             deltaX = x2-x1;
        }
        if(y1>y2){
            deltaY = y1-y2;
        }
        else{
             deltaY = y2-y1;
        }
        return Math.sqrt( deltaX*deltaX + deltaY*deltaY);
    }
    
    /**
     * Возвращает расстояние между двумя точками
     * @param p1 координаты первой точки
     * @param p2 координаты второй точки
     * @return 
     */
    public static double distance(Point p1, Point p2) {
        return GameMath.distance(p1.x, p1.y, p2.x, p2.y);
    }
    
    /**
     * Возвращает угол между двумя точками относительно восточного направления
     * @param p1 первая точка
     * @param p2 вторая точка
     * @return угол между точками в градусах
     */
    public static int angle(Point p1, Point p2) {
        int angle = radiansToDegrees(Math.atan((p2.y - p1.y) / (double)(p2.x - p1.x)));
        if (p2.x < p1.x)
            angle += 180;
        return angle;
    }
    
    /**
     * Возвращает точку на том же расстоянии от центра, что и переданая,
     * но в противоположном от центра направлении
     * @param p точка, для которой надо определить противоположную
     * @param center центр, относительно которого будет строиться противоположная точка
     * @return противоположная точка
     */
    public static Point getOppositePoint(Point p, Point center) {
        int newX = p.x - 2 * (p.x - center.x);
        int newY = p.y - 2 * (p.y - center.y);
        return new Point(newX, newY);
    }
    
    public static double getAngleAtThreePoints(Point A, Point B, Point C) {
        double ab = GameMath.distance(A, B),
                bc = GameMath.distance(B, C),
                ac = GameMath.distance(A, C);
        double cos = Math.acos( (Math.pow(ac, 2) + Math.pow(ab, 2) - Math.pow(bc, 2)) / (2*ac*ab));
        return cos;
    }
    
    public static int pointToLine(Point A, Point B, Point O) {
        double k = (A.getY() - B.getY()) / (A.getX() - B.getX()),
                b = (B.getX() * A.getY() - A.getX()*B.getY())/(B.getX() - A.getX());
        int res = O.getY() < k*O.getX() + b ? 1 : -1;
        return B.getX() > A.getX() ? res : -res;
    }

    public static boolean distance(double distance) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
