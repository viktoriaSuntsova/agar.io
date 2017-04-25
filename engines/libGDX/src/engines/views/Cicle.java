/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engines.views;

/**
 *
 * @author homepc
 */
public class Cicle {

    /**
     * Координата X центра
     */
    public double x0;
    /**
     * Координата Y центра
     */    
    public double y0;
    /**
     * Радиус ширины
     */
    public double radius;
    
    /**
     * Конструирует новый эллипс
     * @param x0 X-координата центра
     * @param y0 Y-координата центра
     * @param r горизонтальный радиус эллипса
     */
    public Cicle(double x0, double y0, double r) {
        this.x0 = x0;
        this.y0 = y0;
        this.radius = r;
    }
    
}
